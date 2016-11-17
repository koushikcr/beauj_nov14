package beauj.day03.rest;

import beauj.day03.business.TeamBean;
import beauj.day03.model.Team;
import static beauj.day03.model.Team_.teamId;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

public class FindTeamTask implements Runnable {

	private AsyncResponse response;
	private TeamBean teamBean;
	private String tid;

	public FindTeamTask(AsyncResponse response, TeamBean teamBean, String tid) {
		this.response = response;
		this.teamBean = teamBean;
		this.tid = tid;
	}

	@Override
	public void run() {
		System.out.println(">>> running in thread: findTeam");
		
		Team team = teamBean.findTeam(tid);

		if (null == team)
			response.resume(Response
					.status(Response.Status.NOT_FOUND)
					.build());

		response.resume(Response
				.ok(team.toString())
				.build());
	}

	
	
}
