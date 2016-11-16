package beauj.day03.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="team")
@NamedQueries({
	@NamedQuery(
			name="Team.findAll",
			query = "select t from Team t"
	),
	@NamedQuery(
			name="String.findAllNames",
			query = "select t.name from Team t"
	),
	@NamedQuery(
			name="Team.findByTeamId",
			query = "select t from Team t where t.teamId = :tid"
	)
})
public class Team {

	@Id
	@Column(name="team_id")
	private String teamId;
	
	private String name;

	@Column(name="git_repo")
	private String gitRepo;

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGitRepo() {
		return gitRepo;
	}
	public void setGitRepo(String gitRepo) {
		this.gitRepo = gitRepo;
	}

	@Override
	public String toString() {
		return "Team{" + "teamId=" + teamId + ", name=" + name + ", gitRepo=" + gitRepo + '}';
	}

}
