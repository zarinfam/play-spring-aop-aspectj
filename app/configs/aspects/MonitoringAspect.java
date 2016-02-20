package configs.aspects;

/**
 * Created by saeed on 12/27/15.
 */

import models.Monitoring;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import play.mvc.Controller;
import play.mvc.Result;
import services.MonitoringService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;

//@Aspect
//@Named
public class MonitoringAspect {

    @Inject
    private MonitoringService monitoringService;

    @Around("execution(* controllers.UserController.list*(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        String name = joinPoint.getSignature().getName();
        System.out.println("hijacked method : " + name);
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("hijacked arguments : " + args);

        String id = Controller.request().getQueryString("id");
        Monitoring request = monitoringService.create(new Monitoring(name + " - args " + args + " - params "+ id));

        try {
            Result result1 = (Result) joinPoint.proceed();

//            new EnumeratorHelper().log(result1.toScala().body(), monitoringService, new Monitoring(request.getId(), "param = " + Controller.request().getQueryString("id")+ " - " ));

        } catch (Exception e) {
            System.out.println("logAfterThrowing() is running!");
            System.out.println("hijacked : " + joinPoint.getSignature().getName());
            System.out.println("Exception : " + e);

            monitoringService.create(new Monitoring(request.getId(),"param = " + Controller.request().getQueryString("id")+ " - " + e.getMessage()));


            System.out.println("******");

        }


        System.out.println("end******");


    }


}