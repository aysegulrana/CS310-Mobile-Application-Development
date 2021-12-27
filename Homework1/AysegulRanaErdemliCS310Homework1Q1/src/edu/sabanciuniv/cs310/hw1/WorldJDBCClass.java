package edu.sabanciuniv.cs310.hw1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WorldJDBCClass {

	public static ArrayList<Country> readFromFile(String filename)
	{
		ArrayList<Country> countries = new ArrayList<Country>();
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
				
				Country c = new Country (idCount, countryName, capitalCity, continent, population);
				
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
		try
		{	
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310hw1?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "123456");		
			for (Country c : countries)
			{	
				PreparedStatement ps =  connection.prepareStatement("insert into country (countryName, capitalCity, continent, population) values (?,?,?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				ps.setString(1, c.getCountryName());
				ps.setString(2, c.getCapitalCity());
				ps.setString(3, c.getContinent());
				ps.setInt(4, c.getPopulation());
				
				ps.execute();
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Country getCountryByID (int countryID) {
		
		boolean found=false;
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310hw1?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "123456");
			PreparedStatement ps =  connection.prepareStatement("select * from country");

			ResultSet rs =	ps.executeQuery();

			while(rs.next() && !found)
			{
				int id = rs.getInt("countryID"); 
				
				if (countryID==id)
					found=true;
			}
			rs.previous();
			Country c = new Country(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5));	
			
			return c; 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void deleteCountryByID (int countryID) {
		
		boolean found=false;
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310hw1?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "123456");
			PreparedStatement ps =  connection.prepareStatement("select * from country", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs =	ps.executeQuery();

			while(rs.next() && !found)
			{
				int id = rs.getInt("countryID"); 
				
				if (countryID==id) {
					rs.deleteRow();
					found=true;}
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCountryPopulationByID (int countryID, int population) {
		
		boolean found=false;
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310hw1?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "123456");
			PreparedStatement ps =  connection.prepareStatement("select * from country", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs =	ps.executeQuery();

			while(rs.next() && !found)
			{
				int id = rs.getInt("countryID"); 
				
				if (countryID==id) {
					found=true;
					rs.updateInt(5, population);
					rs.updateRow();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
