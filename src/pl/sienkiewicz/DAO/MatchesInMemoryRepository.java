package pl.sienkiewicz.DAO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import pl.sienkiewicz.JSONModels.Matches;

public class MatchesInMemoryRepository {
	
	private List<Matches> matchesList = new ArrayList<>();
	
	
	public List<Matches> getMatchesList(){
		return matchesList;
	}
	
	public void addNewMatchToDataBase(Matches match) {
		matchesList.add(match);
	}

}
