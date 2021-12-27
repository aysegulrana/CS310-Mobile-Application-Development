package edu.sabanciuniv.cs310.hw1;

import java.util.ArrayList;

import edu.sabanciuniv.cs310.hw1.Country;

public class MainClass {

	public static void main(String[] args) 
	{
		ArrayList<Country> list= WorldJPAClass.readFromFile("world.txt");
		
		WorldJPAClass.writeIntoTable(list);
		
		Country c = WorldJPAClass.getCountryByID(1);
		System.out.println(c.getCapitalCity());
		
		WorldJPAClass.updateCountryPopulationByID(15, 1000000);
		WorldJPAClass.deleteCountryByID(2);
	}
}