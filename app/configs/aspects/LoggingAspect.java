package configs.aspects;

/**
 * Created by saeed on 12/27/15.
 */

import daos.UserDao;
import models.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import services.UserService;

import javax.inject.Inject;
import javax.inject.Named;

@Aspect
@Named
public class LoggingAspect {

    private int count;

    @Inject
    private UserDao userDao;

    @After("execution(* daos.UserDao.add(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");

        userDao.persist(new User("log "+ ++count));
    }

}