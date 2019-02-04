package pl.sienkiewicz.models;

public class TeamInformations {
	
	private String name;
	private int id;
	private GameStats gameStats;
	
	
	public TeamInformations(int id, String name, int draws, int playedMatches, String gameType) {
		this.id = id;
		this.name = name;
		this.gameStats = new GameStats(playedMatches, draws, gameType);
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
	public GameStats getStatistics() {
		return gameStats;
	}
	public void setStatistics(GameStats gameStats) {
		this.gameStats = gameStats;
	}
	
}
