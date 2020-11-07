package Geography.appClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import Geography.ServiceLocator;
import Geography.abstractClasses.Model;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Model extends Model {
	ServiceLocator serviceLocator;
	private int value;
	private ArrayList<State> statesArray = new ArrayList<>();
	ArrayList<State> statesOfCountryArray = new ArrayList<>();
	private ArrayList<Country> countryArray = new ArrayList<>();
	private ArrayList<String> countryNames = new ArrayList<>();
	private static String STATES_FILE = ("states.txt");
	private static String COUNTRIES_FILE = ("countries.txt");
	private static String SEPARATOR = (";");

	// private ArrayList<String>stateNames = new ArrayList<>();

	public App_Model() {
		value = 0;

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application model initialized");
	}

	public int getValue() {
		return value;
	}

	public int incrementValue() {
		value++;
		serviceLocator.getLogger().info("Application model: value incremented to " + value);
		return value;
	}

	// Methode gibt eine ArrayList mit allen Staaten zurück
	public ArrayList<State> getStates() {
		return statesArray;
	}

	/*
	 * Methode gibt eine ArrayList mit allen Staaten die zu einem bestimmten Land
	 * gehören zurück
	 */
	public ArrayList<State> getStatesOfCountry(String country) {
		statesOfCountryArray.clear();
		for (State s : statesArray) {
			if (s.getCountry().equalsIgnoreCase(country)) {
				statesOfCountryArray.add(s);
			}
		}
		return statesOfCountryArray;
	}

	// Methode weist der ArrayList statesArray eine ArrayList mit Staaten zu
	public void setStates(ArrayList<State> states) {
		this.statesArray = states;
	}

	// public ArrayList<String> getStateNames(){
	// this.stateNames.clear();
	// for(State s : statesArray) {
	// this.stateNames.add(s.getNameOfState());
	// }
	// return this.stateNames;
	// }
	// Methode gibt eine ArrayList mit allen Ländern zurück
	public ArrayList<Country> getCountries() {
		return countryArray;
	}

	// Methode weist der ArrayList countryArray eine ArrayList mit Countries zu
	public void setCountries(ArrayList<Country> countries) {
		this.countryArray = countries;
	}

	public ArrayList<String> getCountryNames() {
		this.countryNames.clear();
		// this.countryNames.add("none");
		for (Country c : countryArray) {
			this.countryNames.add(c.getNameOfCountry());
		}
		return this.countryNames;
	}

	public void saveFile() {

	}

	public void loadDefaultStatesFile() {

		File stateFile = new File(STATES_FILE);
		try (Reader inReader = new FileReader(stateFile)) {
			BufferedReader in = new BufferedReader(inReader);
			statesArray.clear();
			String line = in.readLine();
			while (line != null) {
				String[] attributes = line.split(SEPARATOR);
				int area = Integer.parseInt(attributes[0]);
				int population = Integer.parseInt(attributes[1]);
				String government = attributes[2];
				String nameOfState = attributes[3];
				String country = attributes[4];
				State state = new State(area, population, Government.valueOf(government), nameOfState, country);
				statesArray.add(state);
				line = in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadDefaultCountryFile() {
		File countryFile = new File(COUNTRIES_FILE);
		try (Reader inReader = new FileReader(countryFile)) {
			BufferedReader in = new BufferedReader(inReader);
			countryArray.clear();
			String line = in.readLine();
			while (line != null) {
				String[] attributes = line.split(SEPARATOR);
				int area = Integer.parseInt(attributes[0]);
				int population = Integer.parseInt(attributes[1]);
				String government = attributes[2];
				String nameOfCountry = attributes[3];
				Country country = new Country(area, population, Government.valueOf(government), nameOfCountry);
				countryArray.add(country);
				line = in.readLine();
				for(State s:statesArray) {
					if (s.getCountry().equalsIgnoreCase(nameOfCountry)){
						country.addStateToCountry(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFileAs() {

	}

	public void initializeGlobalLists() {

		loadDefaultStatesFile();
		loadDefaultCountryFile();

		Country country = new Country(0, 0, Government.none, "none");
		countryArray.add(country);
	}
}
