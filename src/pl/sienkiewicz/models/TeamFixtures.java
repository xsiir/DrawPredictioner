package pl.sienkiewicz.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class TeamFixtures {
	
	private Map<String, GameStats> fixtures = new LinkedHashMap<>();
	
	
	/**
	 * 
	 * @param type rodzaj rozgrywki - tu: TOTAL, HOME, AWAY
	 * @return obiekt z rozegranymi meczami, suma remisow i srednia remisow w stosunku do meczy podanego typu
	 */
	public GameStats getStatsByMatchType(String type) {
		return fixtures.get(type);
	}
	
	/**
	 * 
	 *@param type rodzaj rozgrywki - tu: TOTAL, HOME, AWAY
	 *@param stats rozegrane mecze, remisy i srednia remisow
	 * 
	 */
	public void putStats(String type, GameStats stats) {
		this.fixtures.put(type, stats);
	}

	

}
