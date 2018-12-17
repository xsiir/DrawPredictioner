package pl.sienkiewicz.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.api.PrinterService;


@Controller
@RequestMapping("/")
public class MainController {
	
	private final PrinterService printerService;
	
	@Autowired
	public MainController(PrinterService printerService) {
		this.printerService = printerService;
	}

	
	@ResponseBody
	@RequestMapping("/getTeamList")
	public String getTeamList(@RequestParam(name = "type", required = false) String matchType){
		try {
		printerService.printTeamDetails(matchType);
		}catch(UnirestException e) {
			return "You reached your request limit. Wait 18 seconds.";
		}catch(IOException e) {
			return "You reached your request limit. Wait 18 seconds.";
		}
		return "Check console for team list!!";
	}
	
	@ResponseBody
	@RequestMapping("/getMatches")
	public String getMatches(@RequestParam(name = "dateFrom", required = false) String dateFrom,
			@RequestParam(name = "dateFor", required = false) String dateFor) throws JsonSyntaxException, UnirestException, FileNotFoundException, IOException {
		printerService.printFixtures(dateFrom, dateFor);
		return "Check console for the nearest matches!"; 
	}
	

}
