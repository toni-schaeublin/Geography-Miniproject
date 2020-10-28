package Geography.appClasses;

import java.util.ArrayList;

public class Countries {
	private ArrayList<Country> countries;
	private String countryName;

	public Countries() {
		this.countries = new ArrayList<Country>();
	}

	public void addCountry(Country country) {
		this.countries.add(country);
	}

	public Country getCountry(int index) {
		return countries.get(index);
	}

	public int getSize() {
		int size = this.countries.size();
		return size;

	}

//Entfernt ein Land aus der Liste countries sofern dieses vorhanden ist
	public String removeCountry(String countryName) {
		this.countryName = countryName;
		String notification = "";
		for (Country e : countries) {
			if (e.getNameOfCountry().equalsIgnoreCase(this.countryName)) {
				countries.remove(e);
				notification = "Country removed";

			} else {
				notification = "No Country " + countryName + " found";
			}
		}
		return notification;
	}
}
