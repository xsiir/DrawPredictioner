package pl.sienkiewicz.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface APIService {
	
	
	/**
	 * 
	 * @param leaugeCode kod ligi ktorej dane chcemy pobrac
	 * @return odpowiedz zapisana w formacie JSON jako String
	 * @throws UnirestException jesli nie uda sie poborac danych z API
	 */
	String callAPI(String leaugeCode) throws UnirestException;
	
	/**
	 * 
	 * 
	 * @throws JsonSyntaxException jesli format JSONA bedzie wadliwy
	 * @throws UnirestException jesli nie uda sie pobrac danych z API
	 * @throws FileNotFoundException jesli nie znajdzie pliku .properties
	 * @throws IOException jesli napotka blad z polaczeniem
	 */
	void getLeagueFixturesDetails() throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;
	
	/**
	 * 
	 * @param type rodzaj rozegranych meczy sluzacy do filtrowania wynikow - tu: TOTAL, AWAY, HOME
	 */
	void printList(String matchType);

	
}
