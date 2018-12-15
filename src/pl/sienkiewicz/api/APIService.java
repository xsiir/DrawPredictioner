package pl.sienkiewicz.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface APIService {
	
	
	String callAPI(String leaugeCode) throws UnirestException;
	void getLeagueFixturesDetails() throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException;
	void printList();
	
}
