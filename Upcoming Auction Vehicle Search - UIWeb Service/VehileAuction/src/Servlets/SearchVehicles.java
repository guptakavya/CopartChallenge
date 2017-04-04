package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import Beans.SearchBean;
import Beans.ProductBean;
import Beans.ProductsBeans;

import java.util.ArrayList;

/**
 * Servlet implementation class ViewPostsServlet
 */
@WebServlet("/SearchVehicles")
public class SearchVehicles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchVehicles() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
				response.setContentType("text/json");
			
				
				String year = request.getParameter("year");
				String model = request.getParameter("model");
				String make = request.getParameter("make");
				String region =request.getParameter("region");
				

				ProductsBeans products = new ProductsBeans();
				SearchBean search = new SearchBean();
				
				search.setYear(year);
				search.setModel(model);
				search.setMake(make);
				search.setRegion(region);
				
				
				
				Boolean status = false;
				try {

					Client client = Client.create();
					WebResource webResource = client.resource("http://localhost:9443/VehicleAuction/rest/searchvehicles/search");

					Gson userJson = new Gson();
					String data = userJson.toJson(search);

					
					ClientResponse restResponse = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, data);

					if (restResponse.getStatus() != 200) {
						throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
					}

					Gson gson = new Gson();
					ProductsBeans searchResult = gson.fromJson(restResponse.getEntity(String.class), ProductsBeans.class);

					System.out.println("servlet printing now: ");
					// searchResult.getBooks();

					products = searchResult;

					// String statusString = restResponse.getEntity(String.class);
					// status = Boolean.parseBoolean(statusString);
					status = products.isValidSearch();
					System.out.println("servlet status: " + status);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (status) {
					System.out.println("status is good!");
					HttpSession session = request.getSession();
					session.setAttribute("PRODUCTS", products);
					RequestDispatcher rd = request.getRequestDispatcher("SearchVehicles.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("MainPage.jsp");
					rd.forward(request, response);
				}

				// now send request to service

		
	}
	/**
	 * @see HttpServlet#doPOST(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	// String user="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
