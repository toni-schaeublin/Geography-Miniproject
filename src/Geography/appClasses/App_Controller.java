package Geography.appClasses;

import java.util.ArrayList;

import Geography.ServiceLocator;
import Geography.abstractClasses.Controller;
import Geography.commonClasses.Translator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
	private ArrayList<State> statesOfCountry = new ArrayList<>();
	private ArrayList<Country> countries = new ArrayList<>();
	private ArrayList<State> states = new ArrayList<>();
	private ArrayList<String> countryNames = new ArrayList<>();
	private ArrayList<String> stateNames = new ArrayList<>();
	private ObservableList<Country> oCList = FXCollections.observableList(countries);
	private ObservableList<State> oSList = FXCollections.observableList(states);

	// Texte für Labels
	private String lblNotNumeric;
	private String lblFillAllFields;
	private String lblAdded;
	private String lblExistsAllready;
	private String lblDeleted;
	private String noElement;

	public App_Controller(App_Model model, App_View view) {
		super(model, view);
		lblNotNumeric = view.getlblNotNumeric();
		lblFillAllFields = view.getlblFillAllFields();
		lblAdded = view.getlblAdded();
		lblExistsAllready = view.getlblExistsAllready();
		lblDeleted = view.getlblDeleted();
		noElement = view.getlblnoElement();
		lblNotNumeric = view.getlblNotNumeric();
		lblFillAllFields = view.getlblFillAllFields();
		lblAdded = view.getlblAdded();
		lblExistsAllready = view.getlblExistsAllready();
		lblDeleted = view.getlblDeleted();
		noElement = view.getlblnoElement();

		countries = model.getCountries();
		for (Country c : countries) {
			oCList.add(c);
		}
		states = model.getStates();
		for (State s : states) {
			oSList.add(s);
		}
		view.countryList.getItems().clear();
		for (Country c : countries) {
			view.countryList.getItems().add(c);
		}
		view.stateList.getItems().clear();
		for (State s : states) {
			view.stateList.getItems().add(s);
		}
		oCList.addListener(new ListChangeListener() {

			public void onChanged(ListChangeListener.Change change) {
				serviceLocator.getLogger().info("Detected a change in Countries! ");
				model.setCountries(countries);
				countryNames.clear();
				countryNames = model.getCountryNames();
				view.cmbCountries.getItems().clear();
				for (String c : countryNames) {
					view.cmbCountries.getItems().add(c);
				}
				view.cmbCountries.setValue("none");
				view.countryList.getItems().clear();
				for (Country c : countries)
					view.countryList.getItems().add(c);
			}
		});

		oSList.addListener(new ListChangeListener() {

			public void onChanged(ListChangeListener.Change change) {
				serviceLocator.getLogger().info("Detected a change in States! ");
				model.setStates(states);
				statesOfCountry = null;
				view.cmbStates.getItems().clear();
				statesOfCountry = model.getStatesOfCountry(nameOfCountry);
				for (State s : statesOfCountry) {
					view.cmbStates.getItems().add(s.getName());
				}
				view.stateList.getItems().clear();
				for (State s : states)
					view.stateList.getItems().add(s);

			}
		});

		view.txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateLbls();
			this.nameOfCountry = newValue;
			this.statesOfCountry = null;
			view.cmbStates.getItems().clear();
			if (this.nameOfCountry != "" & this.nameOfCountry.length() > 2) {
				view.cmbStates.getItems().clear();
				this.txtNameTest = true;
				this.statesOfCountry = model.getStatesOfCountry(this.nameOfCountry);
				for (State s : this.statesOfCountry) {
					view.cmbStates.getItems().add(s.getName());
				}

			} else {
				view.cmbStates.getItems().clear();
				this.txtNameTest = false;
			}
		});

		view.txtArea.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateLbls();
			if (isNumeric(newValue) && newValue != "") {
				this.txtAreaTest = true;
				this.countryArea = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.txtAreaTest = false;
				view.status.setText(lblNotNumeric);
			}
		});

		view.txtPopulation.textProperty().addListener((observable, oldValue, newValue) -> {
			this.updateLbls();
			if (isNumeric(newValue)) {
				this.populationTest = true;
				this.countryPopulation = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.populationTest = false;
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
			this.updateLbls();
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
			this.updateLbls();
			if (isNumeric(newValue) && newValue != "") {
				this.statePopulationTest = true;
				this.statePopulation = Integer.parseInt(newValue);
				view.status.setText("");
			} else {
				this.statePopulationTest = false;
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
			this.updateLbls();
			boolean countryChecker = false;
			this.countries = model.getCountries();
			// prüfen ob alle Felder ausgefüllt sind...
			if (txtNameTest && populationTest && txtAreaTest && governmentTest) {
				// prüfen ob das Land bereits existiert..
				for (Country c : this.countries) {
					if (c.getName().equalsIgnoreCase(view.txtName.getText())) {
						countryChecker = true;
					}
				}
				if (!countryChecker) {
					country = new Country(nameOfCountry, countryArea, countryPopulation, government);
					this.countries.add(country);
					this.oCList.add(country);
					this.states = model.getStates();
					for (State s : states) {
						if (s.getCountry().equalsIgnoreCase(nameOfCountry)) {
							statesOfCountry.add(s);
						}
					}
					// model.setCountries(countries);
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
			this.updateLbls();
			boolean checker = false;
			this.countries = model.getCountries();
			int count = 0;
			int result = 0;
			for (Country c : this.countries) {
				if (c.getName().equalsIgnoreCase(nameOfCountry)) {
					checker = true;
					result = count;
					count++;
				} else {
					count++;
				}
			}
			if (checker) {
				this.countries.remove(result);
				this.oCList.remove(result);
				String name = nameOfCountry;
				this.clearAllFields(e);
				view.status.setText(name + " " + lblDeleted);
			} else {
				view.status.setText(nameOfCountry + " " + noElement);
			}
			model.setCountries(countries);
		}
	}

	// Methode fügt einem bestehenden Land neue Attribute hinzu...
	public void refreshCountry(ActionEvent e) {
		this.updateLbls();
		if (e.getSource() == view.btnUpdateCountry) {

		}
	}

	// Method fügt einen Staat in die Liste hinzu
	public void addState(ActionEvent e) {
		this.updateLbls();
		if (e.getSource() == view.btnAddState) {
			boolean stateChecker = false;
			this.states = model.getStates();
			// prüfen ob alle Felder ausgefüllt sind...
			if (txtStateNameTest && statePopulationTest && stateAreaTest && stateGovernmentTest) {
				// prüfen ob der Staat bereits existiert..
				for (State s : this.states) {
					if (s.getName().equalsIgnoreCase(nameOfState)) {
						stateChecker = true;
					}
				}
				if (!stateChecker) {
					state = new State(nameOfState, stateArea, statePopulation, government, stateOfCountry);
					this.states.add(state);
					this.oSList.add(state);
					view.status.setText(nameOfState + " " + lblAdded);
				} else {
					view.status.setText(nameOfState + " " + lblExistsAllready);
				}
			} else {
				view.status.setText(lblFillAllFields);
			}
		}

	}

	// Methode löscht einen Staat aus der Liste
	public void deleteState(ActionEvent e) {
		this.updateLbls();
		if (e.getSource() == view.btnDeleteState) {
			boolean checker = false;
			this.states = model.getStates();
			int count = 0;
			int result = 0;
			for (State s : this.states) {
				if (s.getName().equalsIgnoreCase(nameOfState)) {
					checker = true;
					result = count;
					count++;
				} else {
					count++;
				}
			}
			if (checker) {
				this.states.remove(result);
				this.oSList.remove(result);
				String name = nameOfState;
				view.status.setText(name + " " + lblDeleted);
			} else {
				view.status.setText(nameOfState + " " + noElement);
			}
		}
	}

	// Methode fügt einem bestehenden Staat neue Attribute hinzu...
	public void refreshState(ActionEvent e) {
		this.updateLbls();

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
	public void updateLbls() {
		view.updateTexts();
		lblNotNumeric = view.getlblNotNumeric();
		lblFillAllFields = view.getlblFillAllFields();
		lblAdded = view.getlblAdded();
		lblExistsAllready = view.getlblExistsAllready();
		lblDeleted = view.getlblDeleted();
		noElement = view.getlblnoElement();
	}
}
