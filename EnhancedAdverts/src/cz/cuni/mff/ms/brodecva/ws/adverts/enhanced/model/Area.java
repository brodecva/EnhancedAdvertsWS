package cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Area {
	private String Code;
	private String Name;
	private int AreaLevel;
	
	@JsonProperty("Code")
	public String getCode() {
		return this.Code;
	}
	
	@JsonProperty("Code")
	public void setCode(String code) {
		this.Code = code;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return this.Name;
	}
	
	@JsonProperty("Name")
	public void setName(String name) {
		this.Name = name;
	}
	
	@JsonProperty("AreaLevel")
	public int getAreaLevel() {
		return this.AreaLevel;
	}
	
	@JsonProperty("AreaLevel")
	public void setAreaLevel(int areaLevel) {
		this.AreaLevel = areaLevel;
	}
}
