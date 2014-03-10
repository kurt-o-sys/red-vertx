package be.qsys.vertx.streamliner;

import org.vertx.java.core.*;
import org.vertx.java.core.eventbus.*;
import org.vertx.java.platform.*;

public class WellKnown
      extends Verticle {

   public void start() {
      vertx.eventBus().registerHandler(
            "be.qsys.vertx.streamliner.redserver.wellknown",
            new Handler<Message<String>>() {
               @Override
               public void handle(Message<String> message) {
                  System.out.println("wellknown request recieved from" + message.body());
                  message.reply("replied message");
               }
            });
   }
}
