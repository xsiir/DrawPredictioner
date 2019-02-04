package pl.sienkiewicz.APIModels;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Matches {

	@SerializedName("id")
	private String id;
	@SerializedName("utcDate")
	private String utcDate;
	@SerializedName("status")
	private String status;
	@SerializedName("homeTeam")
	private Team homeTeam;
	@SerializedName("awayTeam")
	private Team awayTeam;
	
	public String getId() {
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
	public String getStatus() {
		return status;
	}

	

}
