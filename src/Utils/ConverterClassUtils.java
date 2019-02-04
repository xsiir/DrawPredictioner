package Utils;

import java.util.ArrayList;
import java.util.List;

import pl.sienkiewicz.APIModels.Table;
import pl.sienkiewicz.DTO.TeamDTO;
import pl.sienkiewicz.models.TeamInformations;
import pl.sienkiewicz.mongodb.StandingsRepository.UnwindedStandings;

public class ConverterClassUtils {
	
	public static List<TeamDTO> TableToTeamDTOList(List<Table> table) {
		ArrayList<TeamDTO> teamDTOList = new ArrayList<>();
		for(Table row : table) {
			TeamDTO team = new TeamDTO("", row.getTeam().getName(), row.getTeam().getId(),
					row.getPlayedGames(), row.getDraw());
			teamDTOList.add(team);
		}
		
		return teamDTOList;
	}

	public static List<TeamInformations> convertStandingsTableToTeamDetails(List<UnwindedStandings> tableWithTeams) {
		List<TeamInformations> teamDetailsList = new ArrayList<>();
		for(UnwindedStandings standings : tableWithTeams) {
			Table row = standings.getStandings().getTable();
			TeamInformations team = new TeamInformations(row.getTeam().getId(), row.getTeam().getName(), row.getDraw(), row.getPlayedGames(), standings.getStandings().getMatchType());
			teamDetailsList.add(team);
		}

		return teamDetailsList;
	}
}
