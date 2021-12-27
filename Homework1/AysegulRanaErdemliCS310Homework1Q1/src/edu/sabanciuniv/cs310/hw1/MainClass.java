package edu.sabanciuniv.cs310.hw1;

import java.util.ArrayList;
import edu.sabanciuniv.cs310.hw1.Country;
import edu.sabanciuniv.cs310.hw1.WorldJDBCClass;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Country> list= WorldJDBCClass.readFromFile("world.txt");
		
		WorldJDBCClass.writeIntoTable(list);
		
		Country c = WorldJDBCClass.getCountryByID(1);
		System.out.println(c.getCapitalCity());
		
		WorldJDBCClass.updateCountryPopulationByID(3, 1000000);
		WorldJDBCClass.deleteCountryByID(1);
		
		
	}
}
