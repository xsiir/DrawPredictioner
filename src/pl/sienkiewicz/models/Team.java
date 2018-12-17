package pl.sienkiewicz.models;

public class Team {

	private String name;
	private int id;
	private TeamFixtures fixtures;
	
	
	public Team(int id, String name) {
		this.id = id;
		this.name = name;
		this.fixtures = new TeamFixtures();
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TeamFixtures getFixtures() {
		return fixtures;
	}
	public void setFixtures(TeamFixtures fixtures) {
		this.fixtures = fixtures;
	}
	
	public void addNewFixture(String type, GameStats stats) {
		this.fixtures.putStats(type, stats);
	}
	
	public int getPlayedGamesByMatchType(String matchType) {
		return this.getFixtures().getStatsByMatchType(matchType).getPlayedGames();
	}
	
	public int getDrawsByMatchType(String matchType) {
		return this.getFixtures().getStatsByMatchType(matchType).getDraw();
	}
	
	public double getDrawsAVGByMatchType(String matchType) {
		return this.getFixtures().getStatsByMatchType(matchType).getDrawAVG();

	}
}
