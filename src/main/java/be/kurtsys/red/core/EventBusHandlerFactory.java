package be.kurtsys.red.core;

import org.vertx.java.core.*;
import org.vertx.java.core.eventbus.*;

public class EventBusHandlerFactory {

	private static SearchdirHandler searchdirHandler = new SearchdirHandler();

	public static Handler<Message> forSearchDir() {
		return searchdirHandler;
	}
}
