package alex.falendish.service.impl;

import alex.falendish.dao.UserDAO;
import alex.falendish.model.User;
import alex.falendish.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDAO dao;

    public UserServiceImpl(UserDAO dao){
        this.dao = dao;
    }

    public UserServiceImpl() {

    }

    @Override
    public boolean checkUserPresence(User user) {
      Optional<User> u =  dao.findByUsername(user.getUsername());
      return u.isPresent();
    }

    @Override
    public int calculateUserRides(Long userId) {
        return 0;
    }
}
