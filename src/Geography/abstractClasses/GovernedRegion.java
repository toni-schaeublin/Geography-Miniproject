package Geography.abstractClasses;

import Geography.appClasses.Government;

public abstract class GovernedRegion {
	private int area;
	private int population;
	private Government government;

	public GovernedRegion(int area, int population, Government government) {
		this.area = area;
		this.population = population;
		this.government = government;

	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getPopulation() {
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
