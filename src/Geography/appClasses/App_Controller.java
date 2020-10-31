package Geography.appClasses;

import java.util.ArrayList;

import Geography.ServiceLocator;
import Geography.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */


public class App_Controller extends Controller<App_Model, App_View>{
	ServiceLocator serviceLocator;
	
	private boolean txtNameTest = false;
	private int countryArea;
	private boolean populationTest = false;
	private int countryPopulation;
	private boolean txtAreaTest = false;
	private String nameOfCountry;
	private Government government;
	private String txtGovernment;
	Country country;

	public App_Controller(App_Model model, App_View view) {
		super(model, view);
		
		view.txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != "") {
				txtNameTest = true;
				nameOfCountry = newValue;
				
			}
		});
		
		view.txtArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue)) {
				txtAreaTest = true;
				countryArea=Integer.parseInt(newValue);
			}
		});

		view.txtPopulation.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue)) {
				populationTest = true;
				countryPopulation = Integer.parseInt(newValue);
			}
		});
		
		view.cmbGovernment.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           government=newValue;
	    }
	    ); 
		
		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});
		//Button soll in Action 
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
		if(e.getSource() == view.btnAddCountry) {
		country = new Country(countryArea, countryPopulation, government, nameOfCountry);
		model.addCountryToGlobalList(country);
		System.out.println(country.getNameOfCountry());
	
		}
		
		
	}
	
			
	
}
