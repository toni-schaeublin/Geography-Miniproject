package Geography.abstractClasses;

import Geography.appClasses.Government;

public abstract class GovernedRegion {
	private double area;
	private double population;
	private Government government;

	public GovernedRegion(double area, double population, Government government) {
		this.area = area;
		this.population = population;
		this.government = government;

	}

	public double getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public Government getGovernment() {
		return government;
	}

	public void setGovernment(Government government) {
		this.government = government;
	}


}
