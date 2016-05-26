package cz.cuni.mff.ms.brodecva.ws.adverts.enhanced.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Crime {
	private double CrimeRate;
	private int Found;
	private int Solved;
	
	@JsonProperty("CrimeRate")
	public void setCrimeRate(double crimeRate) {
		this.CrimeRate = crimeRate;
	}
	
	@JsonProperty("Found")
	public void setFound(int found) {
		this.Found = found;
	}
	
	@JsonProperty("CrimeRate")
	public double getCrimeRate() {
		return this.CrimeRate;
	}
	
	@JsonProperty("Found")
	public int getFound() {
		return this.Found;
	}
	
	@JsonProperty("Solved")
	public int getSolved() {
		return this.Solved;
	}
	
	@JsonProperty("Solved")
	public void setSolved(int solved) {
		this.Solved = solved;
	}
}
