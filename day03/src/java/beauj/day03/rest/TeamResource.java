package beauj.day03.rest;

import beauj.day03.business.TeamBean;
import beauj.day03.model.Team;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/team")
public class TeamResource {

	@EJB private TeamBean teamBean;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTeams() {

		List<Team> teams = teamBean.findAllTeams();

		//[ ]
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (Team t: teams) {
			JsonObject jTeam = Json.createObjectBuilder()
					.add("teamId", t.getTeamId())
					.add("name", t.getName())
					.add("git", t.getGitRepo())
					.build();
			arrBuilder.add(jTeam);
		}

		return (Response
				.ok(arrBuilder.build())
				.build());
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
