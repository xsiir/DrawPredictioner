
package pl.sienkiewicz.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.google.gson.annotations.SerializedName;

import pl.sienkiewicz.APIModels.Competition;
import pl.sienkiewicz.APIModels.LeagueStandingsDetails;
import pl.sienkiewicz.APIModels.Season;
import pl.sienkiewicz.APIModels.Table;

@Repository
public class StandingsRepository {

	/**
	 * 
	 * Built-in implementation
	 */
	@Autowired
	private MongoOperations mongoOperations;

	public void saveLeaugeFromAPI(LeagueStandingsDetails leagueDetails) {
		mongoOperations.save(leagueDetails);
	}

	public void dropStandingsCollection() {
		mongoOperations.dropCollection(LeagueStandingsDetails.class);
	}

	public List<UnwindedStandings> getStandings(String matchType) {
		
		Aggregation aggregation;
				
		/**
		 * 
		 * If match type is correct then process aggregation with passed type
		 * else get all fixtures
		 */
		if(isMatchTypeCorrect(matchType)){
			aggregation = Aggregation.newAggregation(
					Aggregation.unwind("standings"),
					Aggregation.match(Criteria.where("standings.type").is(matchType.toUpperCase())),
					Aggregation.unwind("standings.table")
					);			
			}else {
				aggregation = Aggregation.newAggregation(
						Aggregation.unwind("standings"),
						Aggregation.unwind("standings.table")
						);			
			}
		
		
		AggregationResults<UnwindedStandings> aggregationResultList = mongoOperations.aggregate(aggregation,
				"standings", UnwindedStandings.class);

		return aggregationResultList.getMappedResults();

	}

	public List<UnwindedStandings> getSingleLeague(String leagueCode){
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("competition.code").is(leagueCode)),
				Aggregation.unwind("standings"),
				Aggregation.match(Criteria.where("standings.type").is("TOTAL")),
				Aggregation.unwind("standings.table")
				);
				
		AggregationResults<UnwindedStandings> aggregationResult = mongoOperations.aggregate(aggregation, "standings", UnwindedStandings.class);
		System.out.println(aggregationResult.getMappedResults().size());
		
		return aggregationResult.getMappedResults();
	}
	
	private boolean isMatchTypeCorrect(String matchTypeFromParametr) {
		matchTypeFromParametr = matchTypeFromParametr.toUpperCase();
		for(MatchType type : MatchType.values()) {
			System.out.println(type.name() + " ----- "+ matchTypeFromParametr);
			if(type.name().equals(matchTypeFromParametr)) {
				System.out.println("TRUE");
				return true;
			}
		}
	return false;
}
	
	public class HighestScoring {
		@SerializedName("standings")
		private UStandings standings;
		@SerializedName("goalA")
		private Integer goalA;
		@SerializedName("goalF")
		private Integer goalF;
		private double avg;
		public UStandings getStandings() {
			return standings;
		}
		public void setStandings(UStandings standings) {
			this.standings = standings;
		}
		public Integer getGoalA() {
			return goalA;
		}
		public void setGoalA(Integer goalA) {
			this.goalA = goalA;
		}
		public Integer getGoalF() {
			return goalF;
		}
		public void setGoalF(Integer goalF) {
			this.goalF = goalF;
		}
		public double getAvg() {
			return avg;
		}
		public void setAvg(double avg) {
			this.avg = avg;
		}
		
		
	}

public class UnwindedStandings {
	@SerializedName("competition")
	private Competition competition;
	@SerializedName("season")
	private Season season;
	@SerializedName("standings")
	private UStandings standings;

	public UnwindedStandings(Competition competition, Season season, UStandings standings) {
		this.competition = competition;
		this.season = season;
		this.standings = standings;
	}

	public Competition getCompetition() {
		return competition;
	}

	public Season getSeason() {
		return season;
	}

	public UStandings getStandings() {
		return standings;
	}

	public String toString() {
		return competition + ", " + season + ", " + standings;
	}

}

public class UStandings {
	@SerializedName("stage")
	private String stage;
	@SerializedName("type")
	private String type;
	@SerializedName("group")
	private String group;
	@SerializedName("table")
	private Table table;

	public UStandings(String stage, String type, String group, Table table) {
		this.stage = stage;
		this.type = type;
		this.group = group;
		this.table = table;
	}

	public String getStage() {
		return stage;
	}

	public String getMatchType() {
		return type;
	}

	public String getGroup() {
		return group;
	}

	public Table getTable() {
		return table;
	}

	public String toString() {
		return stage + ", " + type + ", " + group + ", " + table;
	}

	}


}
