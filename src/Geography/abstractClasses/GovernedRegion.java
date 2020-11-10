package Geography.abstractClasses;

import Geography.appClasses.Government;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class GovernedRegion {
	private SimpleStringProperty name;
	private SimpleIntegerProperty area;
	private SimpleIntegerProperty population;
	private SimpleStringProperty government;

	public GovernedRegion(String name, int area, int population, Government government) {
		this.name=new SimpleStringProperty(name);
		this.area=new SimpleIntegerProperty(area);
		this.population=new SimpleIntegerProperty(population);
		this.government=new SimpleStringProperty(government.toString());


	}
	
	public String getName() {
		return this.name.get();
	}
	
	public void setName(String name) {
		this.name.set(name);;
	}

	public int getArea() {
		return this.area.get();
	}

	public void setArea(int area) {
		this.area.set(area);;
	}

	public int getPopulation() {
		return this.population.get();
	}

	public void setPopulation(int population) {
		this.population.set(population);
	}

	public String getGovernment() {
		return this.government.get();
	}

	public void setGovernment(Government government) {
		this.government.set(government.toString());
	}

}
