package beauj.day04.ws;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class ChatRoomList {

	private Map<String, Set<Session>> rooms = new HashMap<>();

	public Set<String> getRoomNames() {
		return (rooms.keySet());
	}

	public void add(String roomName, Session sess) {

		Set<Session> allSess = rooms.get(roomName);

		if (null == allSess) {
			allSess = new HashSet<>();
			rooms.put(roomName, allSess);
		}
		allSess.add(sess);
	}

	public Optional<Set<Session>> get(String roomName) {
		Set<Session> allSess = rooms.get(roomName);
		return (Optional.ofNullable(allSess));
	}
	
}
