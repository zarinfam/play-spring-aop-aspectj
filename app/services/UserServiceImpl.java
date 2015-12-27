package services;

import daos.UserDao;
import models.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by saeed on 12/24/15.
 */

@Named
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;


    @Override
    public List<User> findAllUser() {
        return userDao.getAll();
    }

    @Override
    public void create(User user) {
        userDao.persist(user);
    }
}
