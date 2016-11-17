package beauj.day04.ws;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

@WebServlet("/spam")
public class BroadcastServlet extends HttpServlet {

	@Inject private ChatRoomList roomList;

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String spamMsg = UUID.randomUUID().toString();

		for (String n: roomList.getRoomNames()) {
			System.out.println(">> spamming " + n);
			Optional<Set<Session>> opt = roomList.get(n);
			PushMessage task = new PushMessage(spamMsg, opt.get());
			service.submit(task);
		}

		resp.setStatus(200);
	}

	

	
}
