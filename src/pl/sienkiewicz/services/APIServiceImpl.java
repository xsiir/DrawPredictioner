package pl.sienkiewicz.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
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
import pl.sienkiewicz.JSONModels.JSONStandingsResult;
import pl.sienkiewicz.JSONModels.Matches;
import pl.sienkiewicz.JSONModels.Standings;
import pl.sienkiewicz.JSONModels.Table;
import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.models.Team;

@Service
public class APIServiceImpl implements APIService {

	private static final String _KEY = "09655cb8d0e34f6fb81f2800e5eb12d5";
	private TeamInMemoryRepository teamRepository = new TeamInMemoryRepository();
	private MatchesInMemoryRepository matchesRepository = new MatchesInMemoryRepository();

	@Override
	public String callAPIForStandings(String code) throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("http://api.football-data.org/v2/competitions/" + code + "/standings").header("X-Auth-Token", _KEY)
				.asString();

		return response.getBody();
	}

	@Override
	public String callAPIForNextMatches(String dateFrom, String dateFor) throws UnirestException {
		HttpResponse<String> responseBody = Unirest
				.get("http://api.football-data.org/v2/competitions/PL/matches?status=SCHEDULED&dateFrom="+dateFrom+"&dateTo="+dateFor)
				.header("X-Auth-Token", _KEY).asString();

		return responseBody.getBody();
	}

	@Override
	public void addMatchesToRepository(String dateFrom, String dateFor) throws JsonSyntaxException, UnirestException {
		JSONMatchesResult result = new Gson().fromJson(callAPIForNextMatches(dateFrom, dateFor), JSONMatchesResult.class);
		for (Matches match : result.getMatches()) {
			matchesRepository.addNewMatchToDataBase(match);
		}
	}
	
	@Override
	public void addStandingsToBase() throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream("..\\Projekty\\DrawPredictioner\\src\\resources\\leagues.properties"));
		Enumeration e = p.propertyNames();
		for (; e.hasMoreElements();) {
			JSONStandingsResult result = new Gson().fromJson(callAPIForStandings(e.nextElement().toString()),
					JSONStandingsResult.class);
			addTeam(result);
		}
	}

	private void addTeam(JSONStandingsResult result) {
		for (Standings standing : result.getStandings()) {
			for (Table row : standing.getTable()) {
				TeamDTO team = new TeamDTO(standing.getMatchType(), row.getTeam().getName(), row.getTeam().getId(),
						row.getPlayedGames(), row.getDraw());
				teamRepository.addTeamFixtureToDataBase(team);
			}
		}
	}

}
