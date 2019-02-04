package pl.sienkiewicz.DTO;

import pl.sienkiewicz.models.TeamInformations;

public class MatchDTO {
	
	private String id;
	private TeamInformations homeTeam;
	private TeamInformations awayTeam;
	private String status;
	private String matchDay;
	private double probabilityInPercents;
	
	public MatchDTO(String Id, TeamInformations homeTeam, TeamInformations awayTeam, String matchDay, String status, Double probabilityInPercents) {
		super();
		this.id = Id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDay = matchDay;
		this.status = status;
		this.probabilityInPercents = probabilityInPercents;
	}
	public TeamInformations getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(TeamInformations homeTeam) {
		this.homeTeam = homeTeam;
	}
	public TeamInformations getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(TeamInformations awayTeam) {
		this.awayTeam = awayTeam;
	}
	public String getMatchDay() {
		return matchDay.substring(0, 10);
	}
	public void setMatchDay(String matchDay) {
		this.matchDay = matchDay;
	}
	public double getProbabilityInPercents() {
		return this.probabilityInPercents+0.5;
	}
	
	public  String toString() {
		return getMatchDay() + " " + homeTeam.getName() + " VS " + awayTeam.getName();
	}
	
	

}
