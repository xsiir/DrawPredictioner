package pl.sienkiewicz.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.DTO.MatchDTO;
import pl.sienkiewicz.models.TeamInformations;

public interface PrinterService {

	/**
	 * 
	 * @param type rodzaj rozegranych meczy, sluzacy do filtrowania wynikow - tu:
	 *             TOTAL, AWAY, HOME
	 * @return 
	 */
	List<TeamInformations> getStandingsSortedByDrawAVG(String matchType, Integer resultLimit)
			throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException;

	/**
	 * 
	 * @param dateFrom data od ktorej maja zostac wyswietlone mecze
	 * @param dateFor  data do ktorej maja zostac wyswietlone mecze
	 * @param  
	 * @return 
	 * @throws JsonSyntaxException   gdy format JSONa jest wadliwy
	 * @throws UnirestException      gdy nie uda sie pobrac danych z API
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	List<MatchDTO> getFixturesWithHighProbabilityOfDraw(String dateFrom, String dateFor, Integer resultLimit)
			throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;

}
