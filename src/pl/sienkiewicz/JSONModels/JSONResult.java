package pl.sienkiewicz.JSONModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class JSONResult {

	@SerializedName("filters")
	private Filters filters;
	@SerializedName("competition")
	private Competition competition;
	@SerializedName("season")
	private Season season;
	@SerializedName("standings")
	private List<Standings> standings;
	
	
	public Filters getFilters() {
		return filters;
	}
	public Competition getCompetition() {
		return competition;
	}
	public Season getSeason() {
		return season;
	}
	public List<Standings> getStandings() {
		return standings;
	}
	
	
	
}
