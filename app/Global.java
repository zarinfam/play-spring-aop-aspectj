import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import configs.AppConfig;
import configs.DataConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.libs.F;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.SQLException;

/**
 * Created by saeed on 9/March/15 AD.
 */
public class Global extends GlobalSettings {

    protected ApplicationContext applicationContext;
    protected ObjectMapper objectMapper;

    @Override
    public void onStart(Application app) {
        Json.setObjectMapper(createObjectMapper());
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataConfig.class);
    }


    @Override
    public final <A> A getControllerInstance(Class<A> clazz) {
        return applicationContext.getBean(clazz);
    }

    protected ObjectMapper createObjectMapper() {
        //resolves lazy loading to json exception -->>
        ObjectMapper mapper = new ObjectMapper();
        Hibernate4Module hibernate4Module = new Hibernate4Module();
        // fill only ID field for lazy sub models (do not initialize them
        hibernate4Module.configure(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
//        hibernate4Module.configure(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION, false);
        mapper.registerModule(hibernate4Module);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        //resolves lazy loading to json exception <<--

        //ignore null fields for marshalling
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper = mapper;
        return mapper;
    }

}
