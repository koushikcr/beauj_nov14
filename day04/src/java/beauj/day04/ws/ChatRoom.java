package beauj.day04.ws;

import java.util.Optional;
import java.util.Set;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Dependent
@ServerEndpoint("/chat/{name}")
public class ChatRoom {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	@Inject private ChatRoomList roomList;
	private String roomName;

	@OnOpen
	public void open(Session session, @PathParam("name") String roomName) {
		System.out.println(">>> id: " + session.getId());
		System.out.println(">>> room name: " + roomName);

		this.roomName = roomName;

		roomList.add(roomName, session);
	}

	@OnMessage
	public void message(Session session, String msg) {
		Optional<Set<Session>> opt = roomList.get(roomName);

		PushMessage task = new PushMessage(msg, opt.get());
		service.submit(task);
	}
	
}
