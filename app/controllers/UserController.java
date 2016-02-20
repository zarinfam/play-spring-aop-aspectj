package controllers;

import models.Post;
import play.libs.Json;
import play.mvc.*;
import services.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Named
@With(LogAction.class)
public class UserController {

    @Inject
    private UserService userService;

    public Result listUsers() {
        System.out.println("------listUsers----------"+Controller.request().getQueryString("id"));
        userService.testTransaction();
        System.out.println("return result");
        return Results.ok(Json.parse("{\"firstName\":\"Foo\", \"lastName\":\"Bar\", \"age\":13}"));
    }


    public Result listMonitoring() throws InterruptedException {
        Thread.sleep(5000l);
        System.out.println("------listMonitoring----------"+Controller.request().getQueryString("id"));
        userService.testMonitoring();
        return Results.badRequest();
    }


}
