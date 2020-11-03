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
	ArrayList<State> statesOfCountry = new ArrayList<>();
	ArrayList<Country> countries = new ArrayList<>();

	public App_Controller(App_Model model, App_View view) {
		super(model, view);

		view.txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			view.cmbStates.getItems().clear();
			if (newValue != "" & newValue.length() > 2) {
				view.cmbStates.getItems().clear();
				txtNameTest = true;
				statesOfCountry = model.getStatesOfCountry(newValue);
				for (State s : statesOfCountry) {
					view.cmbStates.getItems().add(s.getNameOfState());
				}

			} else {
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

		view.btnAddCountry.setOnAction(this::addCountry);

		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller initialized");
	}

	public static boolean isNumeric(String value) {

		try {
			int number = Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;

		}
	}

//Fügt der globalCountryList ein Land hinzu, wenn der Button addCountry gedrückt wird
	public void addCountry(ActionEvent e) {
		if (e.getSource() == view.btnAddCountry) {
			boolean countryChecker = false;
			countries = model.getCountries();
			if (txtNameTest && populationTest && txtAreaTest && governmentTest) {
				for (Country c : countries) {
					if (c.getNameOfCountry().equalsIgnoreCase(view.txtName.getText())) {
						countryChecker = true;
					}
				}
				if (countryChecker) {
					view.status.setText("Dieses Land existiert bereits");
				} else {
					model.addCountry(countryArea, countryPopulation, government, nameOfCountry);
					view.status.setText("Land hinzugefügt");
				}
			} else {
				view.status.setText("Bitte alle Felder korrekt ausfüllen!");
			}
		}
	}
}
