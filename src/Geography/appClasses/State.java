package Geography.appClasses;

import Geography.abstractClasses.GovernedRegion;

public class State extends GovernedRegion {
	private String country;

	public State(String nameOfState, int area, int population, Government government, String country) {
		super(nameOfState, area, population, government);
		this.country = country;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
