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
      HttpServer server = vertx.createHttpServer();
      RouteMatcher routeMatcher = new RouteMatcher();

      final JsonArray routes = container.config().getArray("routes");

      for (Object route : routes) {
         final JsonObject r = (JsonObject) route;
         routeMatcher.get(r.getString("url"), new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest request) {
               vertx.eventBus().send(
                     r.getString("channel"),
                     "message from controller",
                     new Handler<Message<String>>() {
                        @Override
                        public void handle(Message<String> reply) {
                           request.response().headers().set("Content-Type", "text/plain");
                           request.response().end(reply.body() + " from " + r.getString("url"));
                        }
                     });

            }
         });
      }
      server.requestHandler(routeMatcher).listen(8080);
   }
}
