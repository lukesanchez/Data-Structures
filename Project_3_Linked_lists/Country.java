/**
* This Country Class contains the constructor for a country object. 
* A country object can has the fields of name, capital, population, gdp, cases, deaths. 
* During the Country construction the fields of gdpPerCapita, C19CFR, and numCountriesCreated is updated. 
* The class also includes the getter/setter methods for each of the country attributes. 
* Also included is the compare method to compare country names. 
* Two different toString methods are also used to format the printing of country objects. 
* toString() is used while printing multiple countries to a list. 
* while the toSingularString() is used when printing a single country's multiple attributes. 
*
* @author Luke Sanchez
* @version Oct. 25, 2020
*/

public class Country {
	
	  private String name;
	  private String capital;
	  private long population;
	  private double gdp;
	  private double gdpPerCapita; 
	  private int cases; 
	  private int deaths; 
	  private double c19CFR;
	  private static int numCountriesCreated = 0;
	  
	  
	public Country(String name, String capital, long population, double gdp, int cases, int deaths) {
		this.name = name; 
		this.capital = capital;
		this.population = population; 
		this.gdp = gdp; 
		this.gdpPerCapita = gdp / population;
		this.cases = cases; 
		this.deaths = deaths;
		if (deaths <= 0) 
			this.c19CFR = 0; 
		else 
			this.c19CFR = deaths / Double.valueOf(cases);
		numCountriesCreated++; 
	}
	

	  public String getName() {
		  return this.name;
	  }
	  
	  public String getCapital() {
		  return this.capital;
	  }
	  
	  public long getPopulation() {
		  return this.population;
	  }
	  
	  public double getGDP() {
		  return this.gdp;
	  }
	  
	  public int getCases() {
		  return this.cases;
	  }
	  
	  public int getDeaths() {
		  return this.deaths;
	  }
	  
	  public double getGDPPerCapita() {
		  return this.gdpPerCapita;
	  }
	  
	  public double getC19CFR() {
		  return this.c19CFR; 
	  }
	  
	  public static int getNumCountries() {
		    return numCountriesCreated;
	  }
	  

	public void setName(String newName) {
		  this.name = newName; 
	  }
	  
	  public void setCapital(String newCapital) {
		  this.capital = newCapital; 
	  }
	  
	  public void setPopulation(long newPopulation) {
		  this.population = newPopulation;
	  }
	  
	  public void setGDP(double newGDP) {
		  this.gdp = newGDP;
	  }
	  
	  public void setCases(int newCases) {
		  this.cases = newCases; 
	  }
	  
	  public void setDeaths(int newDeaths) {
		  this.deaths = newDeaths;
	  }
	  
	  public void setGDPPerCapita(double newGDPPerCapita) {
		  this.gdpPerCapita = newGDPPerCapita;
	  }
	  
	  public void setC19CFR(double newC19CFR) {
		  this.c19CFR = newC19CFR;
	  }
	  
	  public int compare(Country country) {
		return this.getName().compareTo(country.getName());
	  }
	  
	  @Override
	  public String toString() {
		    String str = String.format("%-32s | %-24s | %-16d | %-16e | %-10d | %-8d", name, capital, population, gdp, cases, deaths);
		    return str;
	  }
	  
	  public String toSingularString() {
		    String str = String.format("Name: \t\t %s \nCapital: \t %s \nPopulation: \t %d \nGDP: \t\t %e \nCOVID-19 Cases:  %d \nCOVID-19 Deaths: %d", name, capital, population, gdp, cases, deaths);
		    return str;
	  }
}// end Country Class
