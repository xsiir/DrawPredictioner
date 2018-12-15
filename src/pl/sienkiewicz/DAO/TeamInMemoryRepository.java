package pl.sienkiewicz.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.sienkiewicz.DTO.TeamDTO;
import pl.sienkiewicz.models.GameStats;
import pl.sienkiewicz.models.Team;

@Component
public class TeamInMemoryRepository {

	private List<Team> teamList = new ArrayList<Team>();

	public List<Team> getTeamList() {
		return teamList;
	}

	public void addTeamToDataBase(TeamDTO team) {
		if (!isAlreadyInList(team)) {
			Team newTeam = new Team(team.getName());
			GameStats stats = new GameStats(team.getPlayedGames(), team.getDraw());
			newTeam.getFixtures().putStats(team.getType(), stats);
			teamList.add(newTeam);
		} else {
			for (Team tempTeam : teamList) {
				if (tempTeam.getName().equalsIgnoreCase(team.getName())) {
					GameStats stats = new GameStats(team.getPlayedGames(), team.getDraw());
					tempTeam.getFixtures().putStats(team.getType(), stats);
				}

			}
		}
	}

	private boolean isAlreadyInList(TeamDTO team) {
		for (Team tempTeam : teamList) {
			if (tempTeam.getName().equalsIgnoreCase(team.getName()))
				return true;
		}
		return false;
	}

}