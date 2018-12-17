package pl.sienkiewicz.JSONModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class JSONMatchesResult {
	
	@SerializedName("matches")
	private List<Matches> matches;

	public List<Matches> getMatches() {
		return matches;
	}
	

}
