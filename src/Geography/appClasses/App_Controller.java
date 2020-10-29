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

	public App_Controller(App_Model model, App_View view) {
		super(model, view);

		view.txtName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != "") {
				txtNameTest = true;
			}
		});

		view.txtArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue)) {
				txtAreaTest = true;
			}
		});

		view.txtPopulation.textProperty().addListener((observable, oldValue, newValue) -> {
			if (isNumeric(newValue)) {
				txtPopulationTest = true;
			}
		});

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
			}
		});

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
}
