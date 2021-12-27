package edu.sabanciuniv.cs310.rs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JDBCManager {

	public static boolean save(Product p1) 
	{
		try 
		{	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root","123456");
			PreparedStatement ps =  con.prepareStatement("insert into product (productName,productPrice,productStock) values (?,?,?)");
			
			ps.setString(1, p1.getProductName());
			ps.setDouble(2, p1.getProductPrice());
			ps.setInt(3, p1.getProductStock());
			int result = ps.executeUpdate();
			
			if(result==1)
			{
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Product> getAll() {
		ArrayList<Product> prods = new ArrayList<Product>();
		try 
		{
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root","123456");
			PreparedStatement ps =  con.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				String pn = rs.getString("productName");
				double pp = rs.getDouble("productPrice");
				int pst = rs.getInt("productStock");
				int pid = rs.getInt("productID");
				
				Product p = new Product(pn, pp, pst);
				p.setProductID(pid);
				
				prods.add(p);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return prods;
	}
	
	public static boolean deleteProduct (int productID) {
		
		boolean found=false;
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "123456");
			PreparedStatement ps =  connection.prepareStatement("select * from product", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs =	ps.executeQuery();

			while(rs.next() && !found)
			{
				int id = rs.getInt("productID"); 
				
				if (productID==id) {
					rs.deleteRow(); //we delete the row we found from given id
					found=true;
					return found;}
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	
public static boolean updateProductStock (int productID, double updatePrice, int updateStock) {
		
		boolean found=false;
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "123456");
			PreparedStatement ps =  connection.prepareStatement("select * from product", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet rs =	ps.executeQuery();

			while(rs.next() && !found)
			{
				int id = rs.getInt("productID"); 
				
				if (productID==id) { //we check the rows until we find the wanted product
					found=true;
					rs.updateDouble(3, updatePrice); 
					rs.updateRow();
					rs.updateInt(4, updateStock);
					rs.updateRow(); 
					return found;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}

}
