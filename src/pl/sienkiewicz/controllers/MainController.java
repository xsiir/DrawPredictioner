package pl.sienkiewicz.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mashape.unirest.http.exceptions.UnirestException;

import pl.sienkiewicz.api.APIService;


@Controller
@RequestMapping("/")
public class MainController {
	
	private final APIService apiService;
	
	@Autowired
	public MainController(APIService apiService) {
		this.apiService = apiService;
	}

	
	@ResponseBody
	@RequestMapping("/test")
	public String check() throws UnirestException, IOException {
		apiService.getLeagueFixturesDetails();
		return "Done!";
	}

	@RequestMapping("/getList")
	@ResponseBody
	public String getList(@RequestParam("type") String matchType) {
		apiService.printList(matchType);
		return "Check console!";
	}
	

}
