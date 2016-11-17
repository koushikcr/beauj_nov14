package beauj.day03.rest;

import beauj.day03.business.TeamBean;
import beauj.day03.model.Team;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("/team")
public class TeamResource {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	@EJB private TeamBean teamBean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTeams(@Context UriInfo ui) {

		List<Team> teams = teamBean.findAllTeams();
		
		UriBuilder builder = ui.getBaseUriBuilder(); // http://.../api
		builder = builder.path(TeamResource.class); // http://.../api/team

		try {
			builder = builder.path(TeamResource.class.getMethod("getTeamAsText", String.class)); 
		} catch (NoSuchMethodException | SecurityException ex) { }

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (Team t: teams) {
			URI uri = builder.clone().build(t.getTeamId());
			//{ "name": "fred", "url": "http:/...." }
			JsonObject teamInfo = Json.createObjectBuilder()
					.add("name", t.getName())
					.add("url:", uri.toString())
					.build();
			arrBuilder.add(teamInfo);
		}

		//[ ]
		/*
			JsonObject jTeam = Json.createObjectBuilder()
					.add("teamId", t.getTeamId())
					.add("name", t.getName())
					.add("git", t.getGitRepo())
					.build();
			arrBuilder.add(jTeam);
		}
*/

		return (Response
				.ok(arrBuilder.build())
				.build());
	}

	@GET
	@Path("{tid}")
	@Produces(MediaType.APPLICATION_JSON) //application/xml
	public void getAsJson(@PathParam("tid")String tid,
			@Suspended AsyncResponse asyncResponse) {

		FindTeamTask task = new FindTeamTask(asyncResponse, teamBean, tid);

		service.schedule(task, 5, TimeUnit.SECONDS);

		System.out.println(">>> exiting getAsJson");
	}


	@GET
	@Path("{tid}")
	@Produces(MediaType.TEXT_PLAIN) //application/xml
	public Response getTeamAsText(@PathParam("tid")String teamId) {
		Team team = teamBean.findTeam(teamId);

		if (null == team)
			return (Response
					.status(Response.Status.NOT_FOUND)
					.build());

		return (Response
				.ok(team.toString())
				.build());
	}

	@GET
	@Path("{tid}")
	@Produces(MediaType.APPLICATION_XML) //application/xml
	public Response getTeam(@PathParam("tid")String teamId) {

		Team team = teamBean.findTeam(teamId);

		if (null == team)
			return (Response
					.status(Response.Status.NOT_FOUND)
					.build());

		return (Response
				.ok(team)
				.build());
	}
	
}
