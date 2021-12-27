package edu.sabanciuniv.cs310.hw1;
import edu.sabanciuniv.cs310.hw1.Country;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class WorldJPAClass {

	public static ArrayList<Country> readFromFile(String filename)
	{
		ArrayList<Country> countries
		= new ArrayList<Country>();
		try 
		{
			FileReader reader = new FileReader(filename);
			BufferedReader bfr = new BufferedReader(reader);
			int idCount=0;
			while(true)
			{
				idCount++;
				String line = bfr.readLine();
				if (line == null)
				{
					break;
				}

				String[] arr = line.split(",");
				
				String countryName = arr[0];
				String continent = arr[1];
				String capitalCity = arr[2];
				int population = Integer.parseInt(arr[3]);

				Country c = new Country (0, countryName, capitalCity, continent, population);

				countries.add(c); 
			}
			reader.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("no have rights to read that file");
			e.printStackTrace();
		}
		return countries; }
	
	public static void writeIntoTable ( ArrayList<Country> countries ) {

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
			EntityManager entityManager = emf.createEntityManager();
				
			for (Country c : countries)
			{				
				entityManager.getTransaction().begin();			
				entityManager.persist(c);
				entityManager.getTransaction().commit();			
			}
	}
	
	public static Country getCountryByID (int countryID) {
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager entityManager = emf.createEntityManager();
			
		Country country = entityManager.find(Country.class, countryID);
			
			return country; 
		}
	
	public static void deleteCountryByID (int countryID) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager entityManager =emf.createEntityManager();
			
		  Country country = entityManager.find(Country.class, countryID);

		  entityManager.getTransaction().begin();
		  entityManager.remove(country);
		  entityManager.getTransaction().commit();
	}

	public static void updateCountryPopulationByID (int countryID, int population) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager entityManager =emf.createEntityManager();
			
		  Country country = entityManager.find(Country.class, countryID);

		  entityManager.getTransaction().begin();
		  country.setPopulation(population);
		  entityManager.getTransaction().commit();	
	}
}
