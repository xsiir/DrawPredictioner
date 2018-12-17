package pl.sienkiewicz.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import pl.sienkiewicz.DTO.TeamDTO;
import pl.sienkiewicz.models.GameStats;
import pl.sienkiewicz.models.Team;

@Repository
public class TeamInMemoryRepository {

	private static List<Team> drawStatsList = new ArrayList<Team>();

	public List<Team> getDrawStatsList() {
		return drawStatsList;
	}

	public void addTeamFixtureToDataBase(TeamDTO team) {
		if (!isAlreadyInList(team)) {
			Team newTeam = new Team(team.getName());
			drawStatsList.add(newTeam);
		}
		for (Team tempTeam : drawStatsList) {
			if (tempTeam.getName().equalsIgnoreCase(team.getName())) {
				GameStats stats = new GameStats(team.getPlayedGames(), team.getDraw());
				tempTeam.addNewFixture(team.getType(), stats);
			}
		}
	}

	private boolean isAlreadyInList(TeamDTO team) {
		for (Team tempTeam : drawStatsList) {
			if (tempTeam.getName().equalsIgnoreCase(team.getName()))
				return true;
		}
		return false;
	}
}
