package beauj.day03.business;

import beauj.day03.model.Team;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TeamBean {

	@PersistenceContext private EntityManager em;

	public List<String> findAllTeamNames() {
		TypedQuery<String> query = 
				em.createNamedQuery("String.findAllNames", String.class);
		return (query.getResultList());
	}

	public List<Team> findAllTeams() {
		TypedQuery<Team> query =
				em.createNamedQuery("Team.findAll", Team.class);
		return (query.getResultList());
	}

	public Team findTeam(String teamId) {
		TypedQuery<Team> query = 
				em.createNamedQuery("Team.findByTeamId", Team.class);
		query.setParameter("tid", teamId);
		List<Team> result = query.getResultList();
		if (result.size() <= 0)
			return (null);
		return (result.get(0));
		//return (em.find(Team.class, teamId));
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
