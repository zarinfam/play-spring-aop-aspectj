package services;


import models.User;

import java.util.List;

/**
 * Created by saeed on 12/24/15.
 */
public interface UserService {
    List<User> findAllUser();

    void create(User user);
    void testTransaction();

    void testMonitoring() throws InterruptedException;
}
