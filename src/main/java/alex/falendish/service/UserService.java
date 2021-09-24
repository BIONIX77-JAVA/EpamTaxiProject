package alex.falendish.service;

import alex.falendish.model.User;

public interface UserService {

    boolean checkUserPresence (User user);
    int calculateUserRides(Long userId);
}
