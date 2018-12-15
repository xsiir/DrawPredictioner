package pl.sienkiewicz.JSONModels;

import com.google.gson.annotations.SerializedName;

public class Competition {
	
	@SerializedName("id")
	private int id;
	@SerializedName("area")
	private Area area;
	@SerializedName("name")
	private String name;
	@SerializedName("code")
	private String code;
	@SerializedName("plan")
	private String plan;
	@SerializedName("lastUpdated")
	private String lastUpdated;
	public int getId() {
		return id;
	}
	public Area getArea() {
		return area;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getPlan() {
		return plan;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	
	

}
