import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.F;
import play.libs.Json;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;

import static play.test.Helpers.*;

/**
 * Created by Saeed Zarinfam on 7/6/14.
 */
public class TestUtils {

    public static Result routeWithOnError(FakeRequest fakeRequest, Global globalSettings){
        F.Promise<Result> promise = F.Promise.promise(() -> route(fakeRequest));

        F.Function<Throwable, F.Promise<Result>> f = throwable -> globalSettings.onError(null, throwable);

        return promise.recoverWith(f).get(10000);
    }

}
