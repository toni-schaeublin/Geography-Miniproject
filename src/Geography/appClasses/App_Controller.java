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
	private String nameOfState;
	private int stateArea;
	private int statePopulation;
	private boolean stateAreaTest = false;
	private boolean statePopulationTest = false;
	private Government government;
	private String stateOfCountry;
	private boolean governmentTest = false;
	private Country country;
	private State state;
	private Boolean txtStateNameTest = false;
	private boolean stateGovernmentTest = false;
	private boolean stateOfCountrytTest = false;
	ArrayList<State> statesOfCountry = new ArrayList<>();
	ArrayList<Country> countries = new ArrayList<>();
	ArrayList<State> states = new ArrayList<>();
	ArrayList<String> countryNames = new ArrayList<>();
	ArrayList<String> stateNames = new ArrayList<>();
	// Texte für Labels
	private String lblNotNumeric;
	private String lblFillAllFields;
	private String lblAdded;
	private String lblExistsAllready;
	private String lblDeleted;
	private String noElement;

	public App_Controller(App_Model model, App_View view) {
		super(model, view);

		view.txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			this.nameOfCountry = newValue;
			this.statesOfCountry = null;
			view.cmbStates.getItems().clear();
			if (this.nameOfCountry != "" & this.nameOfCountry.length() > 2) {
				view.cmbStates.getItems().clear();
				this.txtNameTest = true;
				this.statesOfCountry = model.getStatesOfCountry(this.nameOfCountry);
				for (State s : this.statesOfCountry) {
					view.cmbStates.getItems().add(s.getNameOfState());
				}

			} else {
				view.cmbStates.getItems().clear();
				this.txtNameTest = false;
			}
		});

		view.txtArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue) && newValue != "") {
				this.txtAreaTest = true;
				this.countryArea = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.txtAreaTest = false;
				// muss noch mehrsprachig gemacht werden!
				view.status.setText(lblNotNumeric);
			}
		});

		view.txtPopulation.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue)) {
				this.populationTest = true;
				this.countryPopulation = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.populationTest = false;
				// muss noch mehrsprachig gemacht werden!
				view.status.setText(lblNotNumeric);
			}
		});

		view.cmbGovernment.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			this.government = newValue;
			view.updateTexts();
			this.governmentTest = true;
		});

		view.txtStateName.textProperty().addListener((observable, oldValue, newValue) -> {
			this.nameOfState = newValue;
			if (this.nameOfState != "" & this.nameOfState.length() > 2) {
				this.txtStateNameTest = true;
			} else {
				this.txtStateNameTest = false;
			}
		});

		view.txtStateArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue) && newValue != "") {
				this.stateAreaTest = true;
				this.stateArea = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.stateAreaTest = false;
				// muss noch mehrsprachig gemacht werden!
				view.status.setText(lblNotNumeric);
			}
		});

		view.txtStatePopulation.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue) && newValue != "") {
				this.statePopulationTest = true;
				this.statePopulation = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.statePopulationTest = false;
				// muss noch mehrsprachig gemacht werden!
				view.status.setText(lblNotNumeric);
			}
		});

		view.cmbStatesGovernment.getSelectionModel().selectedItemProperty()
				.addListener((options, oldValue, newValue) -> {
					this.government = newValue;
					this.stateGovernmentTest = true;
				});

		view.cmbCountries.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
			this.stateOfCountry = newValue;
			this.stateOfCountrytTest = true;
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
		view.btnDeleteCountry.setOnAction(this::deleteCountry);
		view.btnClearCountry.setOnAction(this::clearAllFields);
		view.btnUpdateCountry.setOnAction(this::refreshCountry);
		view.btnAddState.setOnAction(this::addState);
		view.btnDeleteState.setOnAction(this::deleteState);
		view.btnUpdateState.setOnAction(this::refreshState);
		view.btnClearState.setOnAction(this::clearAllFields);

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");
	}

	// Methode überprüft, ob die Eingabe value ein Integer ist und gibt dann true
	// zurück
	public static boolean isNumeric(String value) {

		try {
			int n = Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;

		}
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
					this.countryNames.clear();
					this.countryNames = model.getCountryNames();
					view.cmbCountries.getItems().clear();
					for (String c : this.countryNames) {
						view.cmbCountries.getItems().add(c);
					}
					view.cmbCountries.setValue("none");
					view.status.setText(nameOfCountry + " " + lblAdded);
				} else {
					view.status.setText(nameOfCountry + " " + lblExistsAllready);
				}
			} else {
				view.status.setText(lblFillAllFields);
			}
		}
	}

	// Methode löscht ein Land aus der Liste
	public void deleteCountry(ActionEvent e) {
		if (e.getSource() == view.btnDeleteCountry) {
			boolean checker = false;
			ArrayList<Country> countries = new ArrayList<>();
			countries = model.getCountries();
			int count = 0;
			int result = 0;
			for (Country c : countries) {
				if (c.getNameOfCountry().equalsIgnoreCase(nameOfCountry)) {
					checker = true;
					result = count;
					count++;
				} else {
					count++;
				}
			}
			if (checker) {
				countries.remove(result);
				view.cmbCountries.getItems().clear();
				this.countryNames.clear();
				this.countryNames = model.getCountryNames();
				for (String c : this.countryNames) {
					view.cmbCountries.getItems().add(c);
				}
				view.cmbCountries.setValue("none");
				view.status.setText(nameOfCountry + " " + lblDeleted);
			} else {
				view.status.setText(nameOfCountry + " " + noElement);
			}
			model.setCountries(countries);
		}
	}

	// Methode fügt einem bestehenden Land neue Attribute hinzu...
	public void refreshCountry(ActionEvent e) {
		if (e.getSource() == view.btnUpdateCountry) {

		}
	}

	// Method fügt einen Staat in die Liste hinzu
	public void addState(ActionEvent e) {

		if (e.getSource() == view.btnAddState) {
			boolean stateChecker = false;
			this.states = model.getStates();
			// prüfen ob alle Felder ausgefüllt sind...
			if (txtStateNameTest && statePopulationTest && stateAreaTest && stateGovernmentTest) {
				// prüfen ob der Staat bereits existiert..
				for (State s : this.states) {
					if (s.getNameOfState().equalsIgnoreCase(nameOfState)) {
						stateChecker = true;
					}
				}
				if (!stateChecker) {
					state = new State(stateArea, statePopulation, government, nameOfState, stateOfCountry);
					this.states.add(state);
					view.status.setText(nameOfState + " " + lblAdded);
				} else {
					view.status.setText(nameOfState + " " + lblExistsAllready);
				}
				model.setStates(states);
			} else {
				view.status.setText(lblFillAllFields);
			}
		}

	}

	// Methode löscht einen Staat aus der Liste
	public void deleteState(ActionEvent e) {
		if (e.getSource() == view.btnDeleteState) {
			boolean checker = false;
			ArrayList<State> states = new ArrayList<>();
			states = model.getStates();
			int count = 0;
			int result = 0;
			for (State s : states) {
				if (s.getNameOfState().equalsIgnoreCase(nameOfState)) {
					checker = true;
					result = count;
					count++;
				} else {
					count++;
				}
			}
			if (checker) {
				states.remove(result);
				view.status.setText(nameOfState + " " + lblDeleted);
			} else {
				view.status.setText(nameOfState + " " + noElement);
			}
			model.setStates(states);
		}
	}

	// Methode fügt einem bestehenden Staat neue Attribute hinzu...
	public void refreshState(ActionEvent e) {

	}

	// Methode löscht alle Eingabefelder
	public void clearAllFields(ActionEvent e) {
		view.txtName.clear();
		view.txtArea.clear();
		view.txtPopulation.clear();
		view.cmbGovernment.setValue(Government.none);
		view.txtStateName.clear();
		view.txtStateArea.clear();
		view.txtStatePopulation.clear();
		view.cmbStatesGovernment.setValue(Government.none);
		view.status.setText("");
	}

	// Hilfsmethoden um die Statuslbls mehrsprachig zu machen
	public void setLblNotNumeric(String text) {
		this.lblNotNumeric = text;
	}

	public void setLblFillAllFields(String text) {
		this.lblFillAllFields = text;
	}

	public void setLblAdded(String text) {
		this.lblAdded = text;
	}

	public void setLblExistsAllready(String text) {
		this.lblExistsAllready = text;
	}

	public void setLblDeleted(String text) {
		this.lblDeleted = text;
	}

	public void setNoElement(String text) {
		this.noElement = text;
	}

}
