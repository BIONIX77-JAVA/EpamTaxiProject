package alex.falendish.dao;

import alex.falendish.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    User create(User user);

    User update(Long userId, User user);

    void delete(Long userId);

    List<User> findAllUsers();
}