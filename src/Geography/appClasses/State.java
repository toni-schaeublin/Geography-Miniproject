package Geography.appClasses;

import Geography.abstractClasses.GovernedRegion;
import javafx.beans.property.SimpleStringProperty;

public class State extends GovernedRegion {
	private SimpleStringProperty country;

	public State(String nameOfState, int area, int population, Government government, String country) {
		super(nameOfState, area, population, government);
		this.country = new SimpleStringProperty(country);
	}

	public String getCountry() {
		return this.country.get();
	}

	public void setCountry(String country) {
		this.country.set(country);
	}
	public String toString() {
		return (super.getName()+",     Area: "+ Integer.toString(super.getArea())+" km^2,"+"     Population: " 
	+Integer.toString(super.getPopulation())+",     "+super.getGovernment());
	}

}
