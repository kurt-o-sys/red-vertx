package red.vertx.server.webservices;

import org.vertx.java.core.*;
import org.vertx.java.core.eventbus.*;
import org.vertx.java.core.http.*;
import org.vertx.java.core.json.*;
import org.vertx.java.platform.*;

public class WebServiceController
      extends Verticle {

   @Override
   public void start() {
      final HttpServer server = vertx.createHttpServer();
      final RouteMatcher routeMatcher = new RouteMatcher();

      final JsonArray routes = container.config().getArray("routes");

      for (Object route : routes) {
         final JsonObject r = (JsonObject) route;
         routeMatcher.get(r.getString("url"), request -> {
            vertx.eventBus().send(
                  r.getString("channel"),
                  "message from controller",
                  (Handler<Message<Object>>) reply -> {
                     request.response().headers().set("Content-Type", "text/plain");
                     request.response().end(reply.body() + " from " + r.getString("url"));
                  });

         });
      }
      server.requestHandler(routeMatcher).listen(8080);
   }
}
