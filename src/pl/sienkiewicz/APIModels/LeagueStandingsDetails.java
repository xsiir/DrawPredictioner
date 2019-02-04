package pl.sienkiewicz.APIModels;

import java.util.List;

import org.springframework.data.mongodb.core.aggregation.ComparisonOperators.Cmp;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.SerializedName;

@Document(collection = "standings")
public class LeagueStandingsDetails {

	@SerializedName("competition")
	private Competition competition;
	@SerializedName("season")
	private Season season;
	@SerializedName("standings")
	private List<Standings> standings;
	

	public Competition getCompetition() {
		return competition;
	}
	public Season getSeason() {
		return season;
	}
	public List<Standings> getStandings() {
		return standings;
	}
	
	public String toString() {
		return competition + ", " +  season + ", " + standings;
	}
	
	
}
