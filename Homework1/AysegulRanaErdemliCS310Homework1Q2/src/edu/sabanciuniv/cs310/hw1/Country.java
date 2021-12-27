package edu.sabanciuniv.cs310.hw1;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Country {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int countryID;
	private String countryName;
	private String capitalCity;
	private String continent;
	private int population;
		public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(int countryID, String countryName, String capitalCity, String continent, int population) {
		super();
		this.countryID=countryID;
		this.countryName = countryName;
		this.capitalCity = capitalCity;
		this.continent = continent;
		this.population = population;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCapitalCity() {
		return capitalCity;
	}
	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getCountryID() {
		return countryID;
	}
	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

}
