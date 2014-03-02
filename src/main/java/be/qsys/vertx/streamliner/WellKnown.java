package be.qsys.vertx.streamliner;

import org.vertx.java.core.*;
import org.vertx.java.core.http.*;
import org.vertx.java.platform.*;

/**
 * Created with IntelliJ IDEA. User: kurt Date: 11/12/13 Time: 17:18 To change this template use File | Settings | File
 * Templates.
 */
public class WellKnown
      extends Verticle {

   public void start() {
      vertx.createHttpServer().requestHandler(
            new Handler<HttpServerRequest>() {
               @Override
               public void handle(HttpServerRequest request) {
                  System.out.println("got request");
                  request.response().end("iets leutigs");
               }
            }).listen(8080);
   }
}
