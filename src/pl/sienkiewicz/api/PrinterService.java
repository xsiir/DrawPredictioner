package pl.sienkiewicz.api;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface PrinterService {
	
	/**
	 * 
	 * @param type rodzaj rozegranych meczy, sluzacy do filtrowania wynikow - tu: TOTAL, AWAY, HOME
	 */
	void printTeamDetails(String matchType) throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException;
	
	/**
	 * 
	 * @param dateFrom data od ktorej maja zostac wyswietlone mecze
	 * @param dateFor data do ktorej maja zostac wyswietlone mecze
	 * @throws JsonSyntaxException gdy format JSONa jest wadliwy
	 * @throws UnirestException gdy nie uda sie pobrac danych z API
	 */
	void printFixtures(String dateFrom, String dateFor) throws JsonSyntaxException, UnirestException;

}
