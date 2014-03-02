package be.qsys.vertx.streamliner.redserver;

import com.jetdrone.vertx.yoke.*;
import com.jetdrone.vertx.yoke.middleware.*;
import org.vertx.java.core.*;
import org.vertx.java.platform.*;

public class RequestController
      extends Verticle {

   @Override
   public void start() {
      Yoke yoke = new Yoke(vertx);
      yoke.use(new Handler<YokeRequest>() {
         @Override
         public void handle(YokeRequest request) {
            request.response().end("Hello World!");
         }
      });
      yoke.listen(8080);
   }
}
