package pl.sienkiewicz.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.DAO.TeamInMemoryRepository;
import pl.sienkiewicz.DTO.TeamDTO;
import pl.sienkiewicz.JSONModels.JSONResult;
import pl.sienkiewicz.JSONModels.Standings;
import pl.sienkiewicz.JSONModels.Table;
import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.models.GameStats;
import pl.sienkiewicz.models.Team;

@Service
public class APIServiceImpl implements APIService {

	private static final String _KEY = "09655cb8d0e34f6fb81f2800e5eb12d5";
	private TeamInMemoryRepository repository = new TeamInMemoryRepository();

	@Override
	public String callAPI(String code) throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("http://api.football-data.org/v2/competitions/" + code + "/standings").header("X-Auth-Token", _KEY)
				.asString();

		return response.getBody();
	}

//	@Override
//	public void getLeagueFixturesDetails(List<String> leauges) throws JsonSyntaxException, UnirestException {
//		for(String leaugeCode : leauges) {
//		JSONResult result = new Gson().fromJson(callAPI(leaugeCode), JSONResult.class);
//		addResultFromJSONToList(result);
//		}
//	}

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
			for (Table position : standing.getTable()) {
				TeamDTO team = new TeamDTO(standing.getType(), position.getTeam().getName(), position.getTeam().getId(),
						position.getPlayedGames(), position.getDraw());
				repository.addTeamToDataBase(team);
			}
		}
	}

	public void printList() {
		for (Team team : repository.getTeamList()) {
			GameStats stats = team.getFixtures().getStatsByMatchType("TOTAL");
			System.out.println(team.getName() + " " + " GAMES: " + stats.getPlayedGames() + " DRAWS: " + stats.getDraw());
		}
	}

}
