package Geography.appClasses;

import java.util.Locale;
import java.util.logging.Logger;

import Geography.ServiceLocator;
import Geography.abstractClasses.View;
import Geography.commonClasses.Translator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {
	Menu menuFile;
	Menu menuFileLanguage;
	Menu menuFileSave;
	Menu menuFileSaveAs;
	Menu menuHelp;
	GridPane countryPane;
	Label countryLblCountry;
	Label countryLblArea;
	Label countryLblPopulation;
	Label countryLblGovernment;
	Label countryLblStates;
	TextField txtName;
	TextField txtArea;
	TextField txtPopulation;
	Button btnAddCountry;
	Button btnDeleteCountry;
	Button btnClearCountry;
	Button btnUpdateCountry;
	GridPane countryControlBtnPane;

	Label lblNumber;
	Button btnClick;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
	}

	@Override
	protected Scene create_GUI() {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		Logger logger = sl.getLogger();
		MenuBar menuBar = new MenuBar();
		menuFile = new Menu();
		menuFileLanguage = new Menu();
		menuFileSave = new Menu();
		menuFileSaveAs = new Menu();
		menuFile.getItems().addAll(menuFileLanguage, menuFileSave, menuFileSaveAs);
		for (Locale locale : sl.getLocales()) {
			MenuItem language = new MenuItem(locale.getLanguage());
			menuFileLanguage.getItems().add(language);
			language.setOnAction(event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
				sl.setTranslator(new Translator(locale.getLanguage()));
				updateTexts();
			});
		}
		VBox root = new VBox();
		menuHelp = new Menu();

		menuBar.getMenus().addAll(menuFile, menuHelp);
		// Create HBox for menus
		// HBox menuBox = new HBox();
		// menuBox.getChildren().add(menuBar);
		root.getChildren().add(menuBar);

		// Create GridPane for CountryPane and add Objects
		countryPane = new GridPane();
		countryLblCountry = new Label();
		countryLblArea = new Label();
		countryLblPopulation = new Label();
		countryLblGovernment = new Label();
		countryLblStates = new Label();
		txtName = new TextField();
		txtArea = new TextField();
		txtPopulation = new TextField();

		root.getChildren().add(countryPane);
		countryPane.add(countryLblCountry, 0, 0);
		countryPane.add(txtName, 1, 0);
		countryPane.add(countryLblArea, 0, 1);
		countryPane.add(txtArea, 1, 1);
		countryPane.add(countryLblPopulation, 0, 2);
		countryPane.add(txtPopulation, 1, 2);
		countryPane.add(countryLblGovernment, 0, 3);
		// countryPane.add(cmbGovernment, 1, 3);
		countryPane.add(countryLblStates, 0, 4);
		// countryPane.add(cmbStates, 1, 4);

		// Create GridPane for Country-Control-Buttons and add Objects
		countryControlBtnPane = new GridPane();
		btnAddCountry = new Button();
		btnDeleteCountry = new Button();
		btnClearCountry = new Button();
		btnUpdateCountry = new Button();
		root.getChildren().add(countryControlBtnPane);
		countryControlBtnPane.add(btnAddCountry, 0, 0);
		countryControlBtnPane.add(btnDeleteCountry, 1, 0);
		countryControlBtnPane.add(btnUpdateCountry, 2, 0);
		countryControlBtnPane.add(btnClearCountry, 3, 0);

		// Create GridPane for Country-Buttons
		GridPane countryBtnPane = new GridPane();

		// Create GridPane for StatePane
		GridPane statePane = new GridPane();

		// Create GridPane for State-Buttons
		GridPane stateBtnPane = new GridPane();

		// Create HBox for menus
		HBox statusBox = new HBox();

		updateTexts();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();

		// The menu entries
		menuFile.setText(t.getString("program.menu.file"));
		menuFileLanguage.setText(t.getString("program.menu.file.language"));
		menuFileSave.setText(t.getString("program.menu.file.save"));
		menuFileSaveAs.setText(t.getString("program.menu.file.saveAs"));
		menuHelp.setText(t.getString("program.menu.help"));
		// Country Labels
		countryLblCountry.setText(t.getString("program.country.lbl.country"));
		countryLblArea.setText(t.getString("program.country.lbl.area"));
		countryLblPopulation.setText(t.getString("program.country.lbl.population"));
		countryLblGovernment.setText(t.getString("program.country.lbl.government"));
		countryLblStates.setText(t.getString("program.country.lbl.states"));


		// Country controls
		btnAddCountry.setText(t.getString("country.button.add"));
		btnDeleteCountry.setText(t.getString("country.button.delete"));
		btnUpdateCountry.setText(t.getString("country.button.refresh"));
		btnClearCountry.setText(t.getString("country.button.clear"));

		stage.setTitle(t.getString("program.name"));
	}
}