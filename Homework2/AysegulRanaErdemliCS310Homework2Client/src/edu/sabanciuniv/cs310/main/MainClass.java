package edu.sabanciuniv.cs310.main;

import java.net.URL;

public class MainClass {

	public static void main(String[] args) {
		try 
		{
		
			URL url1  =  new URL("http://localhost:8080/AysegulRanaErdemliCS310Homework2WebService/rest/ProductWebService/addNewProduct/Apple/5.0/3000");
			url1.openStream();
			URL url2  =  new URL("http://localhost:8080/AysegulRanaErdemliCS310Homework2WebService/rest/ProductWebService/deleteProduct/10");
			url2.openStream();
			URL url3  =  new URL("http://localhost:8080/AysegulRanaErdemliCS310Homework2WebService/rest/ProductWebService/updateProductStock/11/25.0/554");
			url3.openStream();
	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
