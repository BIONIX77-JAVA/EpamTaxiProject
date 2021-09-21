package alex.falendish.dao.impl;

import alex.falendish.configuration.ConnectionPool;
import alex.falendish.configuration.CustomConnectionPool;
import alex.falendish.dao.VehicleDAO;
import alex.falendish.model.Vehicle;
import alex.falendish.utils.VehicleCategoryType;
import alex.falendish.utils.VehicleStatus;
import alex.falendish.utils.VehicleType;

import java.sql.*;
import java.util.*;

public class JdbcVehicleDAO implements VehicleDAO {

    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    @Override
    public Vehicle create(Vehicle vehicle) {
        Connection connection = connectionPool.getConnection();
        String sql = "insert into vehicles (brand, model, registration_number, color, vehicle_type, category_type, status) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vehicle.getBrand());
            ps.setString(2, vehicle.getModel());
            ps.setString(3, vehicle.getRegistrationNumber());
            ps.setString(4, vehicle.getColor());
            ps.setString(5, vehicle.getVehicleType().name());
            ps.setString(6, vehicle.getCategoryType().name());
            ps.setString(7, vehicle.getVehicleStatus().name());

            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Creating vehicle failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                vehicle.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating vehicle failed, no ID obtained.");
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage());
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Vehicle> findAvailableVehicle(VehicleCategoryType categoryType, VehicleType type) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT V.id, V.brand, V.model, V.registration_number, V.color, V.vehicle_type, V.category_type, V.status, V.created\n" +
                "FROM vehicles V\n" +
                "WHERE V.category_type = ? AND vehicle_type = ?\n" +
                "LIMIT 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoryType.name());
            preparedStatement.setString(2, type.name());
            ResultSet rs = preparedStatement.executeQuery();
            Vehicle vehicle = null;
            if (rs.next()) {
                vehicle = mapToVehicle(rs);
            }
            return Optional.ofNullable(vehicle);
        } catch (SQLException e) {
            return Optional.empty();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void updateStatusByIds(VehicleStatus status, Collection<Long> reservedVehicleIds) {
        if (reservedVehicleIds.isEmpty()) {
            return;
        }
        String sql = "UPDATE vehicles SET status = ? WHERE id IN (?)";
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            Array array = ps.getConnection().createArrayOf("BIGINT", reservedVehicleIds.toArray());
            ps.setString(1, status.name());
            ps.setArray(2, array);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Vehicle> findAllByIds(Collection<Long> vehicleIds) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT V.id, V.brand, V.model, V.registration_number, V.color, V.vehicle_type, V.category_type, V.status, V.created\n" +
                "FROM vehicles V\n" +
                "WHERE V.id IN (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Array array = preparedStatement.getConnection().createArrayOf("BIGINT", vehicleIds.toArray());
            preparedStatement.setArray(1, array);
            Collection<Vehicle> vehicles = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }
            return vehicles;
        } catch (SQLException e) {
            return Collections.emptyList();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void updateStatusByBookingId(VehicleStatus status, Long bookingId) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE vehicles SET status = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status.name());
            ps.setLong(2, bookingId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private Vehicle mapToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(rs.getLong("id"));
        vehicle.setBrand(rs.getString("brand"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setRegistrationNumber(rs.getString("registration_number"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setVehicleType(VehicleType.valueOf(rs.getString("vehicle_type")));
        vehicle.setCategoryType(VehicleCategoryType.valueOf(rs.getString("category_type")));
        vehicle.setVehicleStatus(VehicleStatus.valueOf(rs.getString("status")));
        Timestamp created = rs.getTimestamp("created");
        vehicle.setCreated(Objects.isNull(created) ? null : created.toLocalDateTime());
        return vehicle;
    }
}
