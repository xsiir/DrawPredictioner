package pl.sienkiewicz.APIModels;

import com.google.gson.annotations.SerializedName;

public class Table {

	@SerializedName("position")
	private int position;
	@SerializedName("team")
	private Team team;
	@SerializedName("playedGames")
	private int playedGames;
	@SerializedName("won")
	private int won;
	@SerializedName("draw")
	private int draw;
	@SerializedName("lost")
	private int lost;
	@SerializedName("points")
	private int points;
	@SerializedName("goalsFor")
	private int goalsFor;
	@SerializedName("goalsAgainst")
	private int goalsAgaints;
	@SerializedName("goalsDifference")
	private int goalsDifference;
	public int getPosition() {
		return position;
	}
	public Team getTeam() {
		return team;
	}
	public int getPlayedGames() {
		return playedGames;
	}
	public int getWon() {
		return won;
	}
	
	public int getDraw() {
		return draw;
	}
	public int getLost() {
		return lost;
	}
	public int getPoints() {
		return points;
	}
	public int getGoalsFor() {
		return goalsFor;
	}
	public int getGoalsAgaints() {
		return goalsAgaints;
	}
	public int getGoalsDifference() {
		return goalsDifference;
	}
	
	
	
}
