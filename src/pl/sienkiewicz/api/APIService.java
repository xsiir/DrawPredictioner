package pl.sienkiewicz.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface APIService {

	/**
	 *  Odpytuje API o szczegó³y konkretnej ligi podanej w paratrze.
	 * @param leaugeCode kod ligi ktorej dane chcemy pobrac
	 * @return odpowiedz zapisana w formacie JSON zwrocna jako String
	 * @throws UnirestException jesli nie uda sie poborac danych z API
	 */
	String  callAPIForLeagueTable(String leaugeCode) throws UnirestException;

	
	
	/**
	 * 
	 * Odpytuje API o mecze z lig których s¹ zapisane w pliku properties
	 * @throws JsonSyntaxException   jesli format JSONA bedzie wadliwy
	 * @throws UnirestException      jesli nie uda sie pobrac danych z API
	 * @throws FileNotFoundException jesli nie znajdzie pliku .properties
	 * @throws IOException           jesli napotka blad z polaczeniem
	 */
	void callAPIForLeagueMatchesFromProperties() throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;

	
	
	
	/**
	 * 
	 * @param dateFrom poczatkowa data pobranych rozgrywek
	 * @param dateFor  date koncowa data pobranych rozgrywek
	 * @return odpowiedz zapisana w formacie JSON zwrocona jako String
	 * @throws UnirestException jesli nie uda sie pobrac danych z API
	 */
	String callAPIForNextMatchesInLeague(String code) throws UnirestException;

	
	
	void addMatchesToRepository(String dateFrom, String dateFor)
			throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;

	void updateDataBase() throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException;


}
