package Geography.appClasses;

import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;
import Geography.ServiceLocator;
import Geography.abstractClasses.View;
import Geography.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
	// Variablen für Menu
	Menu menuFile;
	Menu menuFileLanguage;
	MenuItem menuFileSave;
	MenuItem menuFileSaveAs;
	Menu menuHelp;
	// Variablen für countryPane
	GridPane countryPane;
	Label countryLblCountry;
	Label countryLblArea;
	Label countryLblPopulation;
	Label countryLblGovernment;
	Label countryLblStates;
	TextField txtName;
	TextField txtArea;
	TextField txtPopulation;
	ComboBox<Government> cmbGovernment;
	ComboBox<String> cmbStates;
	// Buttons für countryControlPane
	GridPane countryControlBtnPane;
	public Button btnAddCountry;
	public Button btnDeleteCountry;
	public Button btnClearCountry;
	public Button btnUpdateCountry;
	// Variablen für statePane
	GridPane statePane;
	Label lblStateName;
	Label lblStatePopulation;
	Label lblStateArea;
	Label lblStateGovernment;
	Label lblBelongsToCountry;
	TextField txtStateName;
	TextField txtStateArea;
	TextField txtStatePopulation;
	ComboBox<Government> cmbStatesGovernment;
	ComboBox<String> cmbCountries;
	// Buttons für stateControl
	GridPane stateControlBtnPane;
	Button btnAddState;
	Button btnDeleteState;
	Button btnClearState;
	Button btnUpdateState;
	// Label für Status
	Label status;
	// Strings für Lbl status
	private String lblNotNumeric;
	private String lblFillAllFields;
	private String lblAdded;
	private String lblExistsAllready;
	private String lblDeleted;
	private String noElement;
	// Strings für Tabelle
	private String cName;
	private String cArea;
	private String cPopulation;
	private String cGovernment;
	private String sName;
	private String sArea;
	private String sPopulation;
	private String sGovernment;
	private String sOfCountry;
	TableView<Country> countryTable;
	TableView<State> stateTable;

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
		menuFileSave = new MenuItem();
		menuFileSaveAs = new MenuItem();
		menuFile.getItems().addAll(menuFileLanguage, menuFileSave, menuFileSaveAs);
		for (Locale locale : sl.getLocales()) {
			MenuItem language = new MenuItem(locale.getLanguage());
			menuFileLanguage.getItems().add(language);
			language.setOnAction(event -> {
				sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
				sl.setTranslator(new Translator(locale.getLanguage()));
				this.updateTexts();
			});
		}

		// Wurzel ist eine VBox. In dieser wird ein Menubar, GridPanes für Country und
		// State sowie eine Statusleiste abgelegt.
		VBox root = new VBox();
		HBox country = new HBox();
		HBox state = new HBox();

		menuHelp = new Menu();
		menuBar.getMenus().addAll(menuFile, menuHelp);
		root.getChildren().add(menuBar);

		root.getChildren().add(country);
		country.getChildren().add(getCountryPane());
		country.getChildren().add(getCountryTable());
		root.getChildren().add(getCountryControlBtnPane());
		state.getChildren().add(getStatePane());
		state.getChildren().add(getStateTable());
		root.getChildren().add(state);
		root.getChildren().add(getStateControlBtnPane());

		// Create HBox for Status
		HBox statusBox = new HBox();
		status = new Label();
		statusBox.getChildren().add(status);
		root.getChildren().add(statusBox);
		status.setId("statusLbl");
		status.setText("Status");
		this.updateTexts();
		this.updateTableText();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
		return scene;
	}

	protected void updateTexts() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		stage.setTitle(t.getString("program.name"));
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

		// State Labels
		lblStateName.setText(t.getString("program.state.lbl.state"));
		lblStateArea.setText(t.getString("program.state.lbl.area"));
		lblStatePopulation.setText(t.getString("program.state.lbl.population"));
		lblStateGovernment.setText(t.getString("program.state.lbl.government"));
		lblBelongsToCountry.setText(t.getString("program.state.lbl.country"));

		// State controls
		btnAddState.setText(t.getString("state.button.add"));
		btnDeleteState.setText(t.getString("state.button.delete"));
		btnUpdateState.setText(t.getString("state.button.refresh"));
		btnClearState.setText(t.getString("state.button.clear"));

		// Labels
		lblNotNumeric = t.getString("status.lbl.notNumeric");
		lblFillAllFields = t.getString("status.lbl.fillAllFields");
		lblAdded = t.getString("status.lbl.added");
		lblExistsAllready = t.getString("status.lbl.existsAllready");
		lblDeleted = t.getString("status.lbl.deleted");
		noElement = t.getString("status.lbl.noElement");

	}

	protected void updateTableText() {
		Translator t = ServiceLocator.getServiceLocator().getTranslator();
		// Spaltenbeschriftung für Tabelle
		this.cName = t.getString("program.country.lbl.country");
		this.cArea = t.getString("program.country.lbl.area");
		this.cPopulation = t.getString("program.country.lbl.population");
		this.cGovernment = t.getString("program.country.lbl.government");
		// Spaltenbeschriftung für Tabelle
		this.sName = t.getString("program.state.lbl.state");
		this.sArea = t.getString("program.state.lbl.area");
		this.sPopulation = t.getString("program.state.lbl.population");
		this.sGovernment = t.getString("program.state.lbl.government");
		this.sOfCountry = t.getString("program.state.lbl.country");

	}

	private Pane getCountryPane() {
		countryPane = new GridPane();
		countryPane.setId("countryPane");
		countryLblCountry = new Label();
		countryLblArea = new Label();
		countryLblPopulation = new Label();
		countryLblGovernment = new Label();
		countryLblStates = new Label();
		txtName = new TextField();
		txtArea = new TextField();
		txtPopulation = new TextField();
		cmbGovernment = new ComboBox<>();
		cmbGovernment.getItems().addAll(Government.values());
		cmbGovernment.setValue(Government.none);
		cmbStates = new ComboBox<>();
		countryPane.add(countryLblCountry, 0, 0);
		countryPane.add(txtName, 1, 0);
		countryPane.add(countryLblArea, 0, 1);
		countryPane.add(txtArea, 1, 1);
		countryPane.add(countryLblPopulation, 0, 2);
		countryPane.add(txtPopulation, 1, 2);
		countryPane.add(countryLblGovernment, 0, 3);
		countryPane.add(cmbGovernment, 1, 3);
		countryPane.add(countryLblStates, 0, 4);
		countryPane.add(cmbStates, 1, 4);

		return countryPane;
	}

	private Pane getCountryControlBtnPane() {
		countryControlBtnPane = new GridPane();
		countryControlBtnPane.setId("countryControlsBox");
		btnAddCountry = new Button();
		btnDeleteCountry = new Button();
		btnClearCountry = new Button();
		btnUpdateCountry = new Button();
		countryControlBtnPane.add(btnAddCountry, 0, 0);
		countryControlBtnPane.add(btnDeleteCountry, 1, 0);
		countryControlBtnPane.add(btnUpdateCountry, 2, 0);
		countryControlBtnPane.add(btnClearCountry, 3, 0);
		return countryControlBtnPane;
	}

	private Pane getStatePane() {
		statePane = new GridPane();
		statePane.setId("statePane");
		lblStateName = new Label();
		lblStateArea = new Label();
		lblStatePopulation = new Label();
		lblStateGovernment = new Label();
		lblBelongsToCountry = new Label();
		txtStateName = new TextField();
		txtStateArea = new TextField();
		txtStatePopulation = new TextField();
		cmbStatesGovernment = new ComboBox<>();
		cmbStatesGovernment.getItems().addAll(Government.values());
		cmbStatesGovernment.setValue(Government.none);
		cmbCountries = new ComboBox<>();
		statePane.add(lblStateName, 0, 0);
		statePane.add(txtStateName, 1, 0);
		statePane.add(lblStateArea, 0, 1);
		statePane.add(txtStateArea, 1, 1);
		statePane.add(lblStatePopulation, 0, 2);
		statePane.add(txtStatePopulation, 1, 2);
		statePane.add(lblStateGovernment, 0, 3);
		statePane.add(lblBelongsToCountry, 0, 4);
		statePane.add(cmbStatesGovernment, 1, 3);
		statePane.add(cmbCountries, 1, 4);
		return statePane;
	}

	private Pane getStateControlBtnPane() {
		stateControlBtnPane = new GridPane();
		stateControlBtnPane.setId("stateControlsBox");
		btnAddState = new Button();
		btnDeleteState = new Button();
		btnClearState = new Button();
		btnUpdateState = new Button();
		stateControlBtnPane.add(btnAddState, 0, 0);
		stateControlBtnPane.add(btnDeleteState, 1, 0);
		stateControlBtnPane.add(btnUpdateState, 2, 0);
		stateControlBtnPane.add(btnClearState, 3, 0);
		return stateControlBtnPane;
	}

	private TableView<Country> getCountryTable() {
		this.updateTableText();

		countryTable = new TableView<Country>();
		countryTable.setId("table");
		TableColumn<Country, String> nameCol = new TableColumn<Country, String>(this.cName);
		TableColumn<Country, String> areaCol = new TableColumn<Country, String>(this.sArea);
		TableColumn<Country, String> population = new TableColumn<Country, String>(this.sPopulation);
		TableColumn<Country, Government> governmentCol = new TableColumn<Country, Government>(this.sGovernment);
		countryTable.getColumns().addAll(nameCol, areaCol, population, governmentCol);
		return countryTable;
	}

	private TableView<State> getStateTable() {
		this.updateTableText();
		stateTable = new TableView<State>();
		stateTable.setId("table");
		TableColumn<State, String> nameCol = new TableColumn<State, String>(this.sName);
		TableColumn<State, String> areaCol = new TableColumn<State, String>(this.sArea);
		TableColumn<State, String> population = new TableColumn<State, String>(this.sPopulation);
		TableColumn<State, Government> governmentCol = new TableColumn<State, Government>(this.sGovernment);
		TableColumn<State, String> ofCountryCol = new TableColumn<State, String>(this.sOfCountry);
		stateTable.getColumns().addAll(nameCol, areaCol, population, governmentCol, ofCountryCol);
		return stateTable;
	}

	public void initializeComboBoxes() {
		ArrayList<String> countryNames = new ArrayList<>();
		countryNames = model.getCountryNames();
		cmbCountries.getItems().addAll(countryNames);
	}

	public String getlblNotNumeric() {
		return this.lblNotNumeric;
	}

	public String getlblAdded() {
		return this.lblAdded;
	}

	public String getlblFillAllFields() {
		return this.lblFillAllFields;
	}

	public String getlblExistsAllready() {
		return this.lblExistsAllready;
	}

	public String getlblDeleted() {
		return this.lblDeleted;
	}

	public String getlblnoElement() {
		return this.noElement;
	}

}