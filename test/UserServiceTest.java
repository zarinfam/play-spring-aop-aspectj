import configs.AppConfig;
import models.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TestTransaction;
import services.UserService;

import javax.inject.Inject;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(classes={AppConfig.class, DataConfigTest.class})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Inject
    private UserService userService;

    @Test
    public void createTask_fail() {
        userService.testTransaction();

        List<User> allUser = userService.findAllUser();

        allUser.stream().forEach(u -> System.out.println(u.getUserName()));

        assertThat(allUser.size()).isEqualTo(0);
    }



}