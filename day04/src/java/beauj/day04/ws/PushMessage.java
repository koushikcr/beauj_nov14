package beauj.day04.ws;

import java.util.Collection;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Session;

public class PushMessage implements Runnable {

	private Collection<Session> sessions;
	private String message;

	public PushMessage(String msg, Collection<Session> sess) {
		sessions = sess;
		message = msg;

	}

	@Override
	public void run() {

		System.out.println(">>> pushing message");

		JsonObject data = Json.createObjectBuilder()
				.add("timestamp", (new Date()).toString())
				.add("message", message)
				.build();

		for (Session s: sessions) {
			try {
				s.getBasicRemote().sendText(data.toString());
			} catch (Exception ex) {
				try { s.close(); } catch (Exception x) { }
			}
		}
	}

	
	
}
