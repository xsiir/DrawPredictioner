package pl.sienkiewicz.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Fixtures {
	
	private Map<String, GameStats> fixtures = new LinkedHashMap<>();
	
	public GameStats getStatsByMatchType(String type) {
		return fixtures.get(type);
	}

	public void putStats(String type, GameStats stats) {
		this.fixtures.put(type, stats);
	}

	

}
