package pl.sienkiewicz.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface APIService {

	/**
	 * 
	 * @param leaugeCode kod ligi ktorej dane chcemy pobrac
	 * @return odpowiedz zapisana w formacie JSON zwrocna jako String
	 * @throws UnirestException jesli nie uda sie poborac danych z API
	 */
	String callAPIForStandings(String leaugeCode) throws UnirestException;

	/**
	 * 
	 * 
	 * @throws JsonSyntaxException   jesli format JSONA bedzie wadliwy
	 * @throws UnirestException      jesli nie uda sie pobrac danych z API
	 * @throws FileNotFoundException jesli nie znajdzie pliku .properties
	 * @throws IOException           jesli napotka blad z polaczeniem
	 */
	void addStandingsToBase() throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;

	/**
	 * 
	 * @param dateFrom poczatkowa data pobranych rozgrywek
	 * @param dateFor  date koncowa data pobranych rozgrywek
	 * @return odpowiedz zapisana w formacie JSON zwrocona jako String
	 * @throws UnirestException jesli nie uda sie pobrac danych z API
	 */
	String callAPIForNextMatches(String code, String dateFrom, String dateFor) throws UnirestException;

	void addMatchesToRepository(String dateFrom, String dateFor)
			throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;

}
