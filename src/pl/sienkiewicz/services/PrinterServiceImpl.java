package pl.sienkiewicz.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

import Utils.ConverterClassUtils;
import pl.sienkiewicz.APIModels.Matches;
import pl.sienkiewicz.APIModels.Team;
import pl.sienkiewicz.DTO.MatchDTO;
import pl.sienkiewicz.api.PrinterService;
import pl.sienkiewicz.models.TeamInformations;
import pl.sienkiewicz.mongodb.FixturesRepository;
import pl.sienkiewicz.mongodb.StandingsRepository;
import pl.sienkiewicz.mongodb.StandingsRepository.UnwindedStandings;

@Service
public class PrinterServiceImpl implements PrinterService {

	@Autowired
	private StandingsRepository repo;

	@Autowired
	private FixturesRepository fixturesRepository;

	@Override
	public List<TeamInformations> getStandingsSortedByDrawAVG(String matchType, Integer resultLimit)
			throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException {

		matchType = (matchType == null) ? "" : matchType;

		List<UnwindedStandings> unwindedStandingsTable = repo.getStandings(matchType);
		List<TeamInformations> teamList = ConverterClassUtils
				.convertStandingsTableToTeamDetails(unwindedStandingsTable);
		Collections.sort(teamList, getDrawComparator());

		return teamList.subList(0, resultLimit);
	}

	@Override
	public List<MatchDTO> getFixturesWithHighProbabilityOfDraw(String dateFrom, String dateFor, Integer resultLimit)
			throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException {
		
		List<TeamInformations> homeTable = ConverterClassUtils
				.convertStandingsTableToTeamDetails(repo.getStandings("HOME"));
		List<TeamInformations> awayTable = ConverterClassUtils
				.convertStandingsTableToTeamDetails(repo.getStandings("AWAY"));
		List<Matches> matches = fixturesRepository.getAllMatches();
		List<MatchDTO> resultList = new ArrayList<>();

		try {
			for (Matches match : matches) {

				TeamInformations homeTeam = getTeamStats(homeTable, match.getHomeTeam());
				TeamInformations awayTeam = getTeamStats(awayTable, match.getAwayTeam());

				double probability = calculateDrawProbability(homeTeam, awayTeam);
				if ((probability > 45) && match.getStatus().equals("SCHEDULED"))
					resultList.add(new MatchDTO(match.getId(), homeTeam, awayTeam, match.getUtcDate(), match.getStatus(), probability));
			}
		} catch (NullPointerException e) {}
		return resultList.subList(0, resultLimit);
	}
	
	private double calculateDrawProbability(TeamInformations homeTeam, TeamInformations awayTeam) {
		double homeDrawsAVG = homeTeam.getStatistics().getDrawAVG();
		double awayDrawsAVG = awayTeam.getStatistics().getDrawAVG();
		return (homeDrawsAVG + awayDrawsAVG) / 2;
	}

	private TeamInformations getTeamStats(List<TeamInformations> table, Team team) {
		for (TeamInformations tablePosition : table) {
			if (team.getName().equalsIgnoreCase(tablePosition.getName())) {
				return tablePosition;
			}
		}
		return null;
	}

	private Comparator<TeamInformations> getDrawComparator() {
		Comparator<TeamInformations> comparator = new Comparator<TeamInformations>() {
			@Override
			public int compare(TeamInformations o1, TeamInformations o2) {
				return (int) (o2.getStatistics().getDrawAVG() - o1.getStatistics().getDrawAVG());
			}
		};
		return comparator;
	}

}
