package pl.sienkiewicz.models;

public class GameStats {

	private String gameType;
	private int playedGames;
	private int draw;
	private double drawAVG;

	public GameStats(int playedGames, int draw, String gameType) {
		this.playedGames = playedGames;
		this.draw = draw;
		if (draw == 0 || playedGames == 0) {
			this.drawAVG = 0;
		} else
			this.drawAVG = Math.round(((double) this.draw / (double) this.playedGames * 100));
		this.gameType = gameType;
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

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	
	
}
