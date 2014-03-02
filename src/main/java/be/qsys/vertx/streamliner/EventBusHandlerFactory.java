package be.qsys.vertx.streamliner;

import org.vertx.java.core.*;
import org.vertx.java.core.eventbus.*;
import org.vertx.java.core.json.JsonObject;

public class EventBusHandlerFactory {

	private static SearchdirHandler searchdirHandler = new SearchdirHandler();

	public static Handler<Message<JsonObject>> forSearchDir() {
		return searchdirHandler;
	}
}
