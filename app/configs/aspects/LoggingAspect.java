package configs.aspects;

/**
 * Created by saeed on 12/27/15.
 */

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.inject.Named;

@Aspect
@Named
public class LoggingAspect {

    @Before("execution(* services.UserService.findAllUser(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

}