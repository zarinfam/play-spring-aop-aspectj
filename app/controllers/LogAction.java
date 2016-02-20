package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import configs.aspects.EnumeratorHelper;
import models.Monitoring;
import org.springframework.context.annotation.Scope;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import scala.concurrent.Future;
import services.MonitoringService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;
import java.util.Random;

/**
 * Created by saeed on 1/4/16.
 */

@Named
@Scope("prototype")
public class LogAction extends play.mvc.Action.Simple {

    @Inject
    private MonitoringService monitoringService;

    public F.Promise<Result> call(Http.Context ctx) {
        String name = ctx.request().uri();
        String id = ctx.request().getQueryString("id");
        String body = ctx.request().body().asJson().toString();

        F.Promise<Monitoring> monitoringRequestPromise = log(new Monitoring(name + " - body " + body + " - params " + id));

        F.Promise<Result> result = null;

        try {
             result = delegate.call(ctx);
        }catch (Throwable e){
            System.out.println("----------after delegate call exception");
            result = onError(e);
        }

        final F.Promise<Result> finalResultPromise = result;
        monitoringRequestPromise.onRedeem(monitoring -> {
            finalResultPromise.onRedeem(result1 -> {
                F.Promise<byte[]> requestBodyPromise = F.Promise.wrap(new EnumeratorHelper().getEnumeratorFuture(result1.toScala().body()));

                requestBodyPromise.onRedeem(bodyByte -> monitoringService.create(new Monitoring(monitoring.getId(),
                            "param = " + Controller.request().getQueryString("id") + " - "+new String(bodyByte, "UTF-8"))));
            });
        });

        monitoringRequestPromise.recover((t) ->{
            t.printStackTrace();
            return null;
        });

        return result;
    }

    private F.Promise<Monitoring> log(Monitoring monitoring) {
        return F.Promise.promise(() -> monitoringService.create(monitoring));
    }

    public F.Promise<Result> onError( Throwable cause) {
        String title = "بروز خطا در سیستم";
        String description = cause.getMessage();
        int httpStatusCode = Http.Status.INTERNAL_SERVER_ERROR;


        ObjectNode jsonMessage = Json.newObject().put("title", title)
                .put("description", description)
                .put("errorType", cause.getClass().getName());

        return F.Promise.<Result>pure(Results.status(httpStatusCode, jsonMessage));

    }

}
