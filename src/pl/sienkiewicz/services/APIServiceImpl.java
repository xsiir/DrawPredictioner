package pl.sienkiewicz.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.DAO.MatchesInMemoryRepository;
import pl.sienkiewicz.DAO.TeamInMemoryRepository;
import pl.sienkiewicz.DTO.TeamDTO;
import pl.sienkiewicz.JSONModels.JSONMatchesResult;
import pl.sienkiewicz.JSONModels.JSONResult;
import pl.sienkiewicz.JSONModels.Matches;
import pl.sienkiewicz.JSONModels.Standings;
import pl.sienkiewicz.JSONModels.Table;
import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.models.Team;

@Service
public class APIServiceImpl implements APIService {

	private static final String _KEY = "09655cb8d0e34f6fb81f2800e5eb12d5";
	private TeamInMemoryRepository TeamRepository = new TeamInMemoryRepository();
	private MatchesInMemoryRepository matchesRepository = new MatchesInMemoryRepository();

	@Override
	public String callAPI(String code) throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("http://api.football-data.org/v2/competitions/" + code + "/standings").header("X-Auth-Token", _KEY)
				.asString();

		return response.getBody();
	}

	@Override
	public void getLeagueFixturesDetails()
			throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream("..\\Projekty\\DrawPredictioner\\src\\resources\\leagues.properties"));
		Enumeration e = p.propertyNames();
		for (; e.hasMoreElements();) {
			JSONResult result = new Gson().fromJson(callAPI(e.nextElement().toString()), JSONResult.class);
			addResultFromJSONToList(result);
		}
	}

	private void addResultFromJSONToList(JSONResult result) {
		for (Standings standing : result.getStandings()) {
			for (Table row : standing.getTable()) {
				TeamDTO team = new TeamDTO(standing.getMatchType(), row.getTeam().getName(), row.getTeam().getId(),
						row.getPlayedGames(), row.getDraw());
				TeamRepository.addTeamFixtureToDataBase(team);
			}
		}
	}

	@Override
	public void printList(String matchType) {

		Collections.sort(TeamRepository.getdrawStatsList(), getDrawComparator(matchType));
		for (Team team : TeamRepository.getdrawStatsList()) {
			System.out.println(matchType + ": " 
					+ team.getName() + " PlayedGames: "
					+ team.getPlayedGamesByMatchType(matchType) + " DRAWS: "
					+ team.getDrawsByMatchType(matchType) + " AVG: "
					+ team.getDrawsAVGByMatchType(matchType));
		}
	}
	
	private Comparator<Team> getDrawComparator(String matchType){
		Comparator<Team> comparator = new Comparator<Team>() {
			@Override
			public int compare(Team o1, Team o2) {
				return (int) (o2.getDrawsAVGByMatchType(matchType) - o1.getDrawsAVGByMatchType(matchType));
			}
		};
		return comparator;
	} 
	
	public String callAPIForMatches() throws UnirestException {
		HttpResponse<String> responseBody = Unirest.get("http://api.football-data.org/v2/competitions/PL/matches?status=SCHEDULED")
			.header("X-Auth-Token", _KEY).asString();
		
		return responseBody.getBody();
	}
	
	public void addMatchToRepository() throws JsonSyntaxException, UnirestException {
		JSONMatchesResult result = new Gson().fromJson(callAPIForMatches(), JSONMatchesResult.class);
		for(Matches match : result.getMatches()) {
			matchesRepository.addNewMatchToDataBase(match);
		}
	}
	
	public void printMatchesList() {
		for(Matches match : matchesRepository.getMatchesList()) {
			System.out.println(match.getHomeTeam().getName() + " VS " + match.getAwayTeam().getName());
		}
	}
	
}
