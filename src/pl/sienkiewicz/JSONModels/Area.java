package pl.sienkiewicz.JSONModels;

import com.google.gson.annotations.SerializedName;

public class Area {
	
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	
	public int getId() {return id;}
	public String getName() {return name;}

}
