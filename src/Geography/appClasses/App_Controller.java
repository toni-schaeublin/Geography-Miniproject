package Geography.appClasses;

import java.util.ArrayList;

import Geography.ServiceLocator;
import Geography.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */

public class App_Controller extends Controller<App_Model, App_View> {
	ServiceLocator serviceLocator;

	private boolean txtNameTest = false;
	private int countryArea;
	private boolean populationTest = false;
	private int countryPopulation;
	private boolean txtAreaTest = false;
	private String nameOfCountry;
	private Government government;
	private Boolean governmentTest = false;
	private Country country;
	private State state;
	ArrayList<State> statesOfCountry = new ArrayList<>();
	ArrayList<Country> countries = new ArrayList<>();
	ArrayList<State> states = new ArrayList<>();

	public App_Controller(App_Model model, App_View view) {
		super(model, view);

		view.txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			this.nameOfCountry = newValue;
			statesOfCountry = null;
			view.cmbStates.getItems().clear();
			if (nameOfCountry != "" & nameOfCountry.length() > 2) {
				view.cmbStates.getItems().clear();
				txtNameTest = true;
				statesOfCountry = model.getStatesOfCountry(nameOfCountry);
				for (State s : statesOfCountry) {
					view.cmbStates.getItems().add(s.getNameOfState());
				}

			} else {
				view.cmbStates.getItems().clear();
				txtNameTest = false;
			}
		});

		view.txtArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue) && newValue != "") {
				txtAreaTest = true;
				countryArea = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				txtAreaTest = false;
				// muss noch mehrsprachig gemacht werden!
				view.status.setText("Bitte eine gültige Zahl eingeben");
			}
		});

		view.txtPopulation.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue)) {
				populationTest = true;
				countryPopulation = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				populationTest = false;
				// muss noch mehrsprachig gemacht werden!
				view.status.setText("Bitte eine gültige Zahl eingeben");
			}
		});

		view.cmbGovernment.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			government = newValue;
			view.updateTexts();
			governmentTest = true;
		});

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});
		// Buttonclicks mit Methodenreferenzen
		view.btnAddCountry.setOnAction(this::addCountry);

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");
	}

	// Methode überprüft, ob die Eingabe value ein Integer ist und gibt dann true
	// zurück
	public static boolean isNumeric(String value) {

		try {
			int number = Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;

		}
	}

	// Methode löscht ein Land aus der Liste
	public void deleteCountry(String countryName) {
		
	}

	// Methode löscht einen Staat aus der Liste
	public void deleteState(String stateName) {

	}

	// Methode fügt einem bestehenden Land neue Attribute hinzu...
	public void refreshCountry(String countryName) {

	}

	// Methode fügt einem bestehenden Staat neue Attribute hinzu...
	public void refreshState(String stateName) {
	}

	// Methode löscht alle Eingabefelder
	public void clearAllFields() {

	}

	/*
	 * Fügt der ArrayList countries ein Land hinzu, wenn der Button addCountry
	 * gedrückt wird. Anschliessend wird die ArrayList countryArray im model
	 * aktualisiert
	 */
	public void addCountry(ActionEvent e) {
		if (e.getSource() == view.btnAddCountry) {
			boolean countryChecker = false;
			this.countries = model.getCountries();
			// prüfen ob alle Felder ausgefüllt sind...
			if (txtNameTest && populationTest && txtAreaTest && governmentTest) {
				// prüfen ob das Land bereits existiert..
				for (Country c : this.countries) {
					if (c.getNameOfCountry().equalsIgnoreCase(view.txtName.getText())) {
						countryChecker = true;
					}
				}
				if (!countryChecker) {
					country = new Country(countryArea, countryPopulation, government, nameOfCountry);
					this.countries.add(country);
					this.states = model.getStates();
					for (State s : states) {
						if (s.getCountry().equalsIgnoreCase(nameOfCountry)) {
							statesOfCountry.add(s);
						}
					}
					model.setCountries(countries);
					view.status.setText("Land hinzugefügt!");
				} else {
					view.status.setText("Land bereits vorhanden!");
				}
			} else {
				view.status.setText("Bitte alle Felder korrekt ausfüllen!");
			}
		}
	}
}
