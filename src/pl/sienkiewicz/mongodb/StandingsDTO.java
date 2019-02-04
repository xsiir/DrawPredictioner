package pl.sienkiewicz.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import pl.sienkiewicz.APIModels.LeagueStandingsDetails;

@Document(collection = "standings")
public class StandingsDTO {

	public List<LeagueStandingsDetails> standings;
	
	public StandingsDTO() {
		standings = new ArrayList<>();
	}
	
	public List<LeagueStandingsDetails> getStandings(){
		return standings;
	}
	
}
