package edu.sabanciuniv.cs310.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("ProductWebService") //this is our url
public class ProductWebService {

	@GET
	@Path("addNewProduct/{n}/{p}/{s}") //this is url for this method.
	public boolean addNewProduct (@PathParam("n") String productName, 
								@PathParam("p") double productPrice,
								@PathParam("s") int productStock) {
		
		Product prod = new Product(productName,productPrice, productStock);
		boolean result =  JDBCManager.save(prod);
		return  result;
		
	}
	
	@GET
	@Path("deleteProduct/{i}")	
	public boolean deleteProduct(@PathParam("i") int productID){
		boolean result=JDBCManager.deleteProduct((productID));
		return result;
	}
	
	@GET
	@Path("updateProductStock/{i}/{p}/{s}")
	public boolean updateProductStock(@PathParam("i") int productID,
							  @PathParam("p") double productPrice,
							  @PathParam("s") int productStock){

		boolean result=JDBCManager.updateProductStock(productID, productPrice, productStock);
		return result;
		
	}	
}
