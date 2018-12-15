package pl.sienkiewicz.JSONModels;

import com.google.gson.annotations.SerializedName;

public class Team {
	
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("crestUrl")
	private String crestUrl;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getCrestUrl() {
		return crestUrl;
	}
	
	
}
