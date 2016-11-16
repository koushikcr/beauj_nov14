package beauj.day03.view;

import beauj.day03.business.TeamBean;
import beauj.day03.model.Team;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class TeamView {

	@EJB private TeamBean teamBean;

	private Team team = new Team();
	private String teamId;

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public void clear() {
		team = new Team();
		teamId = null;
	}

	public void delete() {
		teamBean.deleteTeam(team);
		clear();
	}

	public void query() {
		team = teamBean.findTeam(teamId);
		System.out.println(">>>team = " + team);
		if (null == team) {
			FacesMessage msg = new FacesMessage("Team not found");
			FacesContext.getCurrentInstance()
					.addMessage(null, msg);
			return;
		}
	}

	public void update() {
		team = teamBean.updateTeam(team);
	}

	public void add() {
		team.setTeamId(UUID.randomUUID().toString().substring(0, 8));
		teamBean.createTeam(team);
	}
	
}
