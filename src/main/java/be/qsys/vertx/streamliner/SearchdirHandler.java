package be.qsys.vertx.streamliner;

import org.vertx.java.core.*;
import org.vertx.java.core.eventbus.*;
import org.vertx.java.core.json.JsonObject;

import java.io.*;
import java.net.*;

public class SearchdirHandler
		implements Handler<Message<JsonObject>> {

	@Override
	public void handle(Message<JsonObject> message) {
		message.reply("searching...");
		try {
			URL url = new URL("http://zothub.com/dirsearch");
			URLConnection connection = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				System.out.println(inputLine);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
