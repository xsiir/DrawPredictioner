package pl.sienkiewicz.APIModels;

import com.google.gson.annotations.SerializedName;

public class Team {
	
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}	
	
	
}
