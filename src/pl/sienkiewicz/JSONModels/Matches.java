package pl.sienkiewicz.JSONModels;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Matches {

	@SerializedName("ID")
	private Integer id;
	@SerializedName("utcDate")
	private String utcDate;
	@SerializedName("homeTeam")
	private Team homeTeam;
	@SerializedName("awayTeam")
	private Team awayTeam;
	public Integer getId() {
		return id;
	}
	public String getUtcDate() {
		return utcDate;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}

	

}
