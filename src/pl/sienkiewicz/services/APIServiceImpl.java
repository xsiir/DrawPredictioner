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

import pl.sienkiewicz.APIModels.JSONMatchesResult;
import pl.sienkiewicz.APIModels.LeagueStandingsDetails;
import pl.sienkiewicz.APIModels.Matches;
import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.mongodb.FixturesRepository;
import pl.sienkiewicz.mongodb.StandingsRepository;

@Service
public class APIServiceImpl implements APIService {

	private final FixturesRepository fixturesRepository;
	private final StandingsRepository standingsRepository;
	
	
	public APIServiceImpl(FixturesRepository fixturesRepository, StandingsRepository standingsRepository) {
		this.fixturesRepository = fixturesRepository;
		this.standingsRepository = standingsRepository;
	}


	private static final String _KEY = "09655cb8d0e34f6fb81f2800e5eb12d5";
	

	@Override
	public String callAPIForLeagueTable(String code) throws UnirestException {
		HttpResponse<String> response = Unirest
				.get("http://api.football-data.org/v2/competitions/" + code + "/standings").header("X-Auth-Token", _KEY)
				.asString();

		return response.getBody();
	}

	@Override
	public String callAPIForNextMatchesInLeague(String code) throws UnirestException {
		HttpResponse<String> responseBody = Unirest
				.get("http://api.football-data.org/v2/competitions/" + code + "/matches?status=SCHEDULED")
				.header("X-Auth-Token", _KEY).asString();
		
		return responseBody.getBody();
	}

	@Override
	public void addMatchesToRepository(String dateFrom, String dateFor) throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException {
		Enumeration leagueProperties = getPropertiesEnumeration();
		for (; leagueProperties.hasMoreElements();) {
			JSONMatchesResult result = new Gson().fromJson(callAPIForNextMatchesInLeague(leagueProperties.nextElement().toString()), JSONMatchesResult.class);
			try{for (Matches match : result.getMatches()) {
				fixturesRepository.saveFixture(match);
			}
		}catch(NullPointerException e) {System.out.println("bladdddd");}};
	}
	
	@Override
	public void callAPIForLeagueMatchesFromProperties() throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException {
		Enumeration leagueProperties = getPropertiesEnumeration();
		for (; leagueProperties.hasMoreElements();) {
			LeagueStandingsDetails result = new Gson().fromJson(callAPIForLeagueTable(leagueProperties.nextElement().toString()),
					LeagueStandingsDetails.class);
			standingsRepository.saveLeaugeFromAPI(result);
		}
	}
	
	private Enumeration<?> getPropertiesEnumeration() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream("..\\Projekty\\DrawPredictioner\\src\\resources\\leagues.properties"));
		return p.propertyNames();
	}
	
	
	public  void  updateDataBase() throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException {
		standingsRepository.dropStandingsCollection();
		fixturesRepository.dropFixturesCollections();

		addMatchesToRepository("", "");
		callAPIForLeagueMatchesFromProperties();
	}

}
