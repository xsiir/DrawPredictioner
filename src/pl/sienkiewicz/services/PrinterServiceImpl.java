package pl.sienkiewicz.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.DAO.MatchesInMemoryRepository;
import pl.sienkiewicz.DAO.TeamInMemoryRepository;
import pl.sienkiewicz.JSONModels.Matches;
import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.api.PrinterService;
import pl.sienkiewicz.models.Team;

@Service
public class PrinterServiceImpl implements PrinterService {

	private TeamInMemoryRepository teamRepository = new TeamInMemoryRepository();
	private MatchesInMemoryRepository matchesRepository = new MatchesInMemoryRepository();

	@Autowired
	private APIService apiService;

	@Autowired
	public PrinterServiceImpl(APIService apiService) {
		this.apiService = apiService;
	}

	@Override
	public void printTeamDetails(String matchType) throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException {
		apiService.addStandingsToBase();
		Collections.sort(teamRepository.getDrawStatsList(), getDrawComparator(matchType));
		for (Team team : teamRepository.getDrawStatsList()) {
			System.out.println(matchType + ": " + team.getName() + " PlayedGames: "
					+ team.getPlayedGamesByMatchType(matchType) + " DRAWS: " + team.getDrawsByMatchType(matchType)
					+ " AVG: " + team.getDrawsAVGByMatchType(matchType));
		}

	}

	@Override
	public void printFixtures(String dateFrom, String dateFor) throws JsonSyntaxException, UnirestException {
		if(dateFrom == null) { dateFrom = LocalDate.now().toString();}
		if(dateFor == null) { dateFor = LocalDate.now().toString();}
		apiService.addMatchesToRepository(dateFrom, dateFor);
		for (Matches match : matchesRepository.getMatchesList()) {
			System.out.println(match.getHomeTeam().getName() + " VS " + match.getAwayTeam().getName());
	}
}

	private Comparator<Team> getDrawComparator(String matchType) {
		Comparator<Team> comparator = new Comparator<Team>() {
			@Override
			public int compare(Team o1, Team o2) {
				return (int) (o2.getDrawsAVGByMatchType(matchType) - o1.getDrawsAVGByMatchType(matchType));
			}
		};
		return comparator;
	}

}
