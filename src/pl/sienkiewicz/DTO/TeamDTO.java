package pl.sienkiewicz.DTO;

public class TeamDTO {
	
	private String matchType;
	private String name;
	private int teamId;
	private int playedGames;
	private int draw;
	private double drawAVG;
	
	public TeamDTO() {}
	
	public TeamDTO(String type, String name, int teamId, int playedGames, int draw) {
		super();
		this.matchType = type;
		this.name = name;
		this.teamId = teamId;
		this.playedGames = playedGames;
		this.draw = draw;
	}
	
	public String getType() {
		return matchType;
	}
	
	public void setType(String type) {
		this.matchType = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getTeamId() {
		return this.teamId;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public double getDrawAVG() {
		return drawAVG;
	}
	
	public String toString() {
		return this.getType() + ": (" + this.getTeamId() + ")" +  this.getName() + ", PlayedGames: " + this.getPlayedGames() + ", DRAWS: " + this.getDraw() + " AVG: " + this.getDrawAVG();
	} 
	
}
