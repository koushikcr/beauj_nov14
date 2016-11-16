package beauj.day03.business;

import beauj.day03.model.Team;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TeamBean {

	@PersistenceContext private EntityManager em;

	public Team findTeam(String teamId) {
		return (em.find(Team.class, teamId));
	}

	public void deleteTeam(Team team) {
		//team is detached
		team = updateTeam(team);
		//team - managed
		em.remove(team);
		//team - deleted
	}

	//Team is detached
	public Team updateTeam(Team team) {
		//team - detached
		team = em.merge(team);
		//team - managed
		return (team);
	}

	//Team is detached
	public Team createTeam(Team team) {
		//team - new
		em.persist(team);
		//team - managed
		return (team);
	}
	
}
