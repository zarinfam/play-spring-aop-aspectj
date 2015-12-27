import configs.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.libs.Json;

/**
 * Created by saeed on 12/24/15.
 */
public class GlobalTest extends Global {

    @Override
    public void onStart(Application app) {
        Json.setObjectMapper(createObjectMapper());
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataConfigTest.class);
    }

}
