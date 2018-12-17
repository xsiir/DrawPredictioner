package pl.sienkiewicz.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import pl.sienkiewicz.JSONModels.Matches;

@Repository
public class MatchesInMemoryRepository {
	
	private static List<Matches> matchesList = new ArrayList<>();
	
	
	public List<Matches> getMatchesList(){
		return matchesList;
	}
	
	public void addNewMatchToDataBase(Matches match) {
		matchesList.add(match);
	}

}
