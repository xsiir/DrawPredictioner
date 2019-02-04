package pl.sienkiewicz.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.api.APIService;
import pl.sienkiewicz.api.PrinterService;
import pl.sienkiewicz.mongodb.FixturesRepository;
import pl.sienkiewicz.mongodb.StandingsRepository;
import pl.sienkiewicz.mongodb.StandingsRepository.UnwindedStandings;

@Controller
@RequestMapping("/")
public class MainController {

	private final PrinterService printerService;
	private final APIService apiService;
	private final StandingsRepository tableRepositorium;
		

	@Autowired
	public MainController(PrinterService printerService, APIService apiService, StandingsRepository repo) {
		this.printerService = printerService;
		this.apiService = apiService;
		this.tableRepositorium = repo;
	}
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/teamTypes")
	public ModelAndView getTeamList(@RequestParam(name = "matchType", required = false) String matchType,
			@RequestParam(name = "limit", required = false, defaultValue = "5") String resultLimit)
			throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException {
		ModelAndView modelAndView = new ModelAndView("teams");
		modelAndView.addObject("teamList", printerService.getStandingsSortedByDrawAVG(matchType, Integer.parseInt(resultLimit)));
		modelAndView.addObject("matchType", matchType);
		return modelAndView;
	}
	
	
	@RequestMapping("/matchTypes")
	public ModelAndView getMatchTypes(@RequestParam(name = "limit", required = false, defaultValue = "5")Integer limit) throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException {
		ModelAndView modelAndView = new ModelAndView("matches");
		modelAndView.addObject("matchList", printerService.getFixturesWithHighProbabilityOfDraw("", "", limit));
		return modelAndView;
	}

	@RequestMapping("/update")
	public ModelAndView updateDataBase() throws JsonSyntaxException, FileNotFoundException, UnirestException, IOException {
		apiService.updateDataBase();
		return new ModelAndView("updated");
	}

	@RequestMapping("/singleLeague")
	public ModelAndView getSingleLeague(@RequestParam(name = "league", required = false) String league) {
		ModelAndView modelAndView = new ModelAndView("league");
		modelAndView.addObject("leagueTable", tableRepositorium.getSingleLeague(league));
		List<UnwindedStandings> x = tableRepositorium.getSingleLeague(league);
		return modelAndView;
	}

}