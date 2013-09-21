package be.kurtsys.vertx.streamliner;

import org.vertx.java.core.*;
import org.vertx.java.core.http.*;
import org.vertx.java.core.json.*;
import org.vertx.java.platform.*;

public class Red
		extends Verticle {

	public void start() {
		HttpServer server = vertx.createHttpServer().requestHandler(getRouteMatcher());
		configEventBuses(server);
		server.listen(8010);
	}

	private RouteMatcher getRouteMatcher() {
		RouteMatcher rm = new RouteMatcher();

		rm.get(".*/vertxbus.js", new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				req.response().sendFile("web/vertxbus.js");
			}
		});
		rm.get("/", new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				req.response().sendFile("web/index.html");
			}
		});
		return rm;
	}

	private void configEventBuses(HttpServer server) {
		JsonObject config = new JsonObject().putString("prefix", "/red-eventbus");
		JsonArray permitted = new JsonArray();
		permitted.add(new JsonObject());
		vertx.createSockJSServer(server).bridge(
				config,
				permitted,
				permitted);

		vertx.eventBus().registerHandler("searchdir", EventBusHandlerFactory.forSearchDir());
	}
}
