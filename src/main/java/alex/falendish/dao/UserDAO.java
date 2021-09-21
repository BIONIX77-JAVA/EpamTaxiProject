package alex.falendish.dao;

import alex.falendish.model.User;
import alex.falendish.utils.Role;

import java.util.Optional;

public interface UserDAO {

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    User create(User user);

    Role getRoleByLoginPassword(final String login, final String password);

    User update(Long userId, User user);

    void delete(Long userId);

    boolean userIsExist(String username, String password);

    Role getUserByLoginPassword(String username, String password);
}