package pl.sienkiewicz.models;

public class GameStats {
	
	private int playedGames;
	private int draw;
	private double drawAVG;
	
	public GameStats(int playedGames, int draw) {
		this.playedGames = playedGames;
		this.draw = draw;
		if(draw==0 || playedGames == 0) {this.drawAVG = 0;}
		else this.drawAVG = Math.round(((double)this.draw/(double)this.playedGames*100));
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
	
	

}
