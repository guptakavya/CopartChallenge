package services;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import DAO.DBOperation;
import Beans.ProductBean;
//import beans.Books;
//import beans.PostBean;
import Beans.ProductsBeans;
import Beans.SearchBean;
//import beans.UserBean;

@Path("/searchvehicles")
public class searchVehicles {
	
	@Path("/search")
	@POST
	@Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
	public Response addNewUser(String data) 
	{
		
		boolean response = false;
		Gson gson = new Gson();
		//PostBean post = gson.fromJson(data, PostBean.class);
		ProductsBeans products = new ProductsBeans();
        //SearchBean search=new SearchBean();
        SearchBean search = gson.fromJson(data, SearchBean.class);
		
		//System.out.println("this is the value of search: " + search);
		
		//sql code to add userInformation to database goes here
		String make =search.getMake();
		String year =search.getYear();
		String model =search.getModel();
		String region =search.getRegion();
		//String[] searchArray=search.getSearchArray();
		
		
		URL url = new URL("http://inventory.copart.io/v1/list?brand="+make+"&country="+region);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		String jsonResult = "";
		while ((inputLine = in.readLine()) != null) {
		    jsonResult += inputLine;
		}
		in.close();
		//return jsonResult;
	
		//ArrayList<ArrayList<String>> postResult = DBOperation.searchBooks(searchArray);
		//System.out.println("The search result is: " + postResult);
		//search.setsearchResult(postResult);
		//System.out.println("index 0 is: " + searchResult.get(0).get(0));
		
		if(postResult != null){
			response = true;
			//post.setpostResult(postResult);
			search.setsearchResult(postResult);
			
			products.setValidationSearch(response);
			System.out.println("post result size " + postResult.size());
			
			for(int index=0;index < postResult.size();index++)
			{
				ProductBean product = new ProductBean();
				
				product.setIsbn(postResult.get(index).get(0));
				product.setTitle(postResult.get(index).get(1));
				product.setAuthor(postResult.get(index).get(2));
				product.setAvailability(postResult.get(index).get(3));
				/*product.setItemDesc(postResult.get(index).get(4));
				product.setItemCategory(postResult.get(index).get(5));
				product.setItemQuality(postResult.get(index).get(6));
				product.setAdd1(postResult.get(index).get(7));
				product.setAdd2(postResult.get(index).get(8));
				product.setCountry(postResult.get(index).get(9));
				product.setState(postResult.get(index).get(10));
				product.setCity(postResult.get(index).get(11));
				product.setEmailId(postResult.get(index).get(12));
				//product.setImage(postResult.get(index).get(12));
				//System.out.println(product.getUserName());
			*/
				products.addProducts(product);
				//System.out.println(products.getUSERNAME(index));
				//System.out.println("book isbn is: " + book.getIsbn());
				
				//System.out.println(searchResult.get(index).get(0));
		
			}
			
			//books.getBooks();
		}
		else
		{
			response = false;
			search.setValidation(response);
			
		}

		/*
		if(isSearchSuccessful){
			response = true;
			
			List<Book> books = new ArrayList<Book>();
			//books.add(book);
			Book book = new Book();
			book.setAuthor("J.K. Rowling");
			book.setInventory(2);
			book.setIsbn("12345");
			book.setPrice(12.99);
			book.setTitle("Harry Potter and the Philosopher's Stone");
			//books.add();
			
		}
		else
		{
			response = false;
		}
		*/
		
		Gson searchResultJson = new Gson();
		String responseData = searchResultJson.toJson(products);
		//System.out.println("value of string is: " + responseData);
		return Response.ok().entity(responseData).build();
	}
	
	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {
		//code here to see if userName exists		
		return username + "001";
	}

}
