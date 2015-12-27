package daos;

import core.dao.GenericDaoImpl;
import models.User;

import javax.inject.Named;

/**
 * Created by saeed on 12/24/15.
 */

@Named
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao{

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
