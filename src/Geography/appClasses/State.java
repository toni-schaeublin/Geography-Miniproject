package Geography.appClasses;

import Geography.abstractClasses.GovernedRegion;

public class State extends GovernedRegion {
	private String country;
	private String nameOfState;

	public State(double area, double population, Government government, String nameOfState, String country) {
		super(area, population, government);
		this.nameOfState= nameOfState;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNameOfState() {
		return nameOfState;
	}

	public void setNameOfState(String nameOfState) {
		this.nameOfState = nameOfState;
	}

}
