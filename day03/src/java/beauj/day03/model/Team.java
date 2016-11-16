package beauj.day03.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="team")
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

}
