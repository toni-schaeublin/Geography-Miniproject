package Geography.appClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import Geography.ServiceLocator;
import Geography.abstractClasses.Model;
import javafx.collections.ObservableList;

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
	private Country country;
	private State state;
	private ArrayList<State> statesArray = new ArrayList<>();
	ArrayList<State> statesOfCountryArray = new ArrayList<>();
	private ArrayList<Country> countryArray = new ArrayList<>();
	private ArrayList<String> countryNames = new ArrayList<>();
	private static String STATES_FILE = ("states.txt");
	private static String COUNTRIES_FILE = ("countries.txt");
	private static String USER_STATES_FILE = ("user_states.txt");
	private static String USER_COUNTRIES_FILE = ("user_countries.txt");
	private static String SEPARATOR = (";");
	private ObservableList<State> states;
	private ObservableList<Country> countries;

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
		this.countryNames.add("none");
		for (Country c : countryArray) {
			this.countryNames.add(c.getName());
		}
		return this.countryNames;
	}

	public void loadDefaultStatesFile() {

		File stateFile = new File(STATES_FILE);
		try (Reader inReader = new FileReader(stateFile)) {
			BufferedReader in = new BufferedReader(inReader);
			statesArray.clear();
			String line = in.readLine();
			while (line != null) {
				String[] attributes = line.split(SEPARATOR);
				String nameOfState = attributes[0];
				int area = Integer.parseInt(attributes[1]);
				int population = Integer.parseInt(attributes[2]);
				String government = attributes[3];
				String country = attributes[4];
				State state = new State(nameOfState, area, population, Government.valueOf(government), country);
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
			this.countryArray.clear();
			String line = in.readLine();
			while (line != null) {
				String[] attributes = line.split(SEPARATOR);
				String nameOfCountry = attributes[0];
				int area = Integer.parseInt(attributes[1]);
				int population = Integer.parseInt(attributes[2]);
				String government = attributes[3];
				Country country = new Country(nameOfCountry, area, population, Government.valueOf(government));
				countryArray.add(country);
				line = in.readLine();
				for (State s : this.statesArray) {
					if (s.getCountry().equalsIgnoreCase(nameOfCountry)) {
						country.addStateToCountry(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadUserCountryFile() {
		File countryFile = new File(USER_COUNTRIES_FILE);
		try (Reader inReader = new FileReader(countryFile)) {
			BufferedReader in = new BufferedReader(inReader);
			this.countryArray.clear();
			String line = in.readLine();
			while (line != null) {
				String[] attributes = line.split(SEPARATOR);
				String nameOfCountry = attributes[0];
				int area = Integer.parseInt(attributes[1]);
				int population = Integer.parseInt(attributes[2]);
				String government = attributes[3];
				Country country = new Country(nameOfCountry, area, population, Government.valueOf(government));
				countryArray.add(country);
				line = in.readLine();
				for (State s : this.statesArray) {
					if (s.getCountry().equalsIgnoreCase(nameOfCountry)) {
						country.addStateToCountry(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadUserStatesFile() {

		File stateFile = new File(USER_STATES_FILE);
		try (Reader inReader = new FileReader(stateFile)) {
			BufferedReader in = new BufferedReader(inReader);
			statesArray.clear();
			String line = in.readLine();
			while (line != null) {
				String[] attributes = line.split(SEPARATOR);
				String nameOfState = attributes[0];
				int area = Integer.parseInt(attributes[1]);
				int population = Integer.parseInt(attributes[2]);
				String government = attributes[3];
				String country = attributes[4];
				State state = new State(nameOfState, area, population, Government.valueOf(government), country);
				statesArray.add(state);
				line = in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFiles() {
		File stateFile = new File(USER_STATES_FILE);
		File countryFile = new File(USER_COUNTRIES_FILE);
		try (Writer writer = new FileWriter(stateFile)) {
			if (statesArray != null) {
				for (State s : statesArray) {
					String name = s.getName();
					String area = Integer.toString(s.getArea());
					String population = Integer.toString(s.getPopulation());
					String government = s.getGovernment();
					String country = s.getCountry();
					String line = name + ";" + area + ";" + population + ";" + government + ";" + country;
					writer.write(line);
					writer.write(System.lineSeparator());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try (Writer countryWriter = new FileWriter(countryFile)) {
			if (countryArray != null) {
				for (Country c : countryArray) {
					String name = c.getName();
					String area = Integer.toString(c.getArea());
					String population = Integer.toString(c.getPopulation());
					String government = c.getGovernment();
					String line = name + ";" + area + ";" + population + ";" + government;
					countryWriter.write(line);
					countryWriter.write(System.lineSeparator());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializeGlobalLists() {
		loadDefaultStatesFile();
		loadDefaultCountryFile();
	}

	public Country getCountry() {
		return country;
	}

	public State getState() {
		return state;
	}
}
