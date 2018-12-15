package pl.sienkiewicz.JSONModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Standings {

	@SerializedName("stage")
	private String stage;
	@SerializedName("type")
	private String type;
	@SerializedName("group")
	private String group;
	@SerializedName("table")
	private List<Table> table;
	public String getStage() {
		return stage;
	}
	public String getMatchType() {
		return type;
	}
	public String getGroup() {
		return group;
	}
	public List<Table> getTable() {
		return table;
	}
	

}
