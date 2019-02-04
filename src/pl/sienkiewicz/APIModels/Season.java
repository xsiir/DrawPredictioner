package pl.sienkiewicz.APIModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Season {
	
	@SerializedName("id")
	private int id;
	@SerializedName("startDate")
	private String startDate;
	@SerializedName("endDate")
	private String endDate;
	@SerializedName("currentMatchday")
	private int currentMatchday;
	@SerializedName("availableStages")
	private List<String> availableStages;
	public int getId() {
		return id;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public int getCurrentMatchday() {
		return currentMatchday;
	}
	public List<String> getAvailableStages() {
		return availableStages;
	}
	
	

}
