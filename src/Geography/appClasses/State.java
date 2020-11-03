package Geography.appClasses;

import Geography.abstractClasses.GovernedRegion;

public class State extends GovernedRegion {
	private String country;
	private String nameOfState;

	public State(int area, int population, Government government, String nameOfState, String country) {
		super(area, population, government);
		this.nameOfState= nameOfState;
		this.country = country;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNameOfState() {
		return this.nameOfState;
	}

	public void setNameOfState(String nameOfState) {
		this.nameOfState = nameOfState;
	}

}
