package alex.falendish.dao.impl;

import alex.falendish.configuration.ConnectionPool;
import alex.falendish.configuration.CustomConnectionPool;
import alex.falendish.dao.UserDAO;
import alex.falendish.model.User;
import alex.falendish.utils.Role;
import alex.falendish.utils.UserStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static alex.falendish.utils.CommonUtils.isBlank;

public class JdbcUserDAO implements UserDAO {

    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();


    private List<User> users;

    public JdbcUserDAO() {
        this.users = Arrays.asList(
                new User(987654567l, "nikita@gmail.com", "098908", "Nikita", "Todo", Collections.singleton(Role.USER), UserStatus.ACTIVE, false, LocalDateTime.now()),
                new User(987655457l, "TOM@gmail.com", "FIGHT8", "Tomi", "Rear", Collections.singleton(Role.USER), UserStatus.ACTIVE, false, LocalDateTime.now())
        );
    }

    @Override
    public Optional<User> findById(Long userId) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT U.id, U.username, U.password, U.first_name, U.first_name, U.status, U.locked, U.created,\n" +
                "       (SELECT string_agg(UR.role_name, ',') FROM USER_ROLES UR WHERE UR.user_id = U.id) as roles\n" +
                "FROM USERS U\n" +
                "WHERE U.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = mapToUser(rs);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT U.id, U.username, U.password, U.first_name, U.first_name, U.status, U.locked, U.created,\n" +
                "       (SELECT string_agg(UR.role_name, ',') FROM USER_ROLES UR WHERE UR.user_id = U.id) as roles\n" +
                "FROM USERS U\n" +
                "WHERE U.username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()) {
                user = mapToUser(rs);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public User create(User user) {
        User newUser = insertNewUser(user);
        insertUserRoles(newUser);
        return newUser;
    }

    @Override
    public User update(Long userId, User user) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE USERS SET username = ?, password = ?, first_name = ?, last_name = ?, status = ?, locked = ?\n" +
                "WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getStatus().name());
            ps.setBoolean(6, user.isLocked());
            ps.setLong(7, userId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return user;
            }
            throw new SQLException("Failed to update user by id:" + userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long userId) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM USERS WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    private Set<Role> parseUserRoles(String value) {
        if (isBlank(value)) {
            return Collections.emptySet();
        }
        return Arrays.stream(value.split(","))
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

    private User mapToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("first_name"));
        user.setStatus(UserStatus.valueOf(rs.getString("status")));
        user.setLocked(rs.getBoolean("locked"));
        Timestamp created = rs.getTimestamp("created");
        user.setCreated(Objects.isNull(created) ? null : created.toLocalDateTime());
        user.setRoles(parseUserRoles(rs.getString("roles")));
        return user;
    }

    private void insertUserRoles(User user) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO USER_ROLES (user_id, role_name) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Role role : user.getRoles()) {
                ps.setLong(1, user.getId());
                ps.setString(2, role.name());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Inserting user roles failed");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private User insertNewUser(User user) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO USERS (username, password, first_name, last_name, locked, status)\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setBoolean(5, user.isLocked());
            ps.setString(6, user.getStatus().name());

            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed");
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}