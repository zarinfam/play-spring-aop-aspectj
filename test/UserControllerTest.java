import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import controllers.UserController;
import daos.MonitoringDao;
import daos.UserDao;
import models.Monitoring;
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

        TestUtils.routeWithOnError(fakeRequest(GET, "/test"), globalTest);

        TestUtils.routeWithOnError(fakeRequest(GET, "/users"), globalTest);

        assertThat(globalTest.applicationContext.getBean(UserService.class).findAllUser().size()).isEqualTo(0);

        List<Monitoring> monitorings = globalTest.applicationContext.getBean(MonitoringDao.class).getAll();

        monitorings.stream().forEach(m -> System.out.println(m.getId()+"-"+m.getRequestId()+"-"+m.getRawData()));

        assertThat(monitorings.size()).isEqualTo(4);
    }

}
