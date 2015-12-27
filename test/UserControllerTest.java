import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import controllers.UserController;
import daos.UserDao;
import models.Post;
import models.User;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;
import services.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class UserControllerTest {

    @Test
    public void listPosts_getAllPostValues_ReturnList() throws IOException {
        GlobalTest globalTest = new GlobalTest();

        start(fakeApplication(globalTest));

        globalTest.applicationContext.getBean(UserService.class).create(new User("Saeed"));
        globalTest.applicationContext.getBean(UserService.class).create(new User("Hamid"));

        Result result = route(fakeRequest(GET, "/users"));
        assertThat(result).isNotNull();
        assertThat(status(result)).isEqualTo(OK);

        List<User> users = new ObjectMapper().readValue(contentAsString(result),
                TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));

        assertThat(users.size()).isEqualTo(2);
    }

}
