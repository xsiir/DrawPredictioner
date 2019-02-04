package pl.sienkiewicz.APIModels;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.annotations.SerializedName;

@Document(collection = "fixtures")
public class JSONMatchesResult {
	
	@SerializedName("matches")
	private List<Matches> matches;

	public List<Matches> getMatches() {
		return matches;
	}
	

}
