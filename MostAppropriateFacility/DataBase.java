/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kavya Gupta
 */
//package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DataBase {

	private static final String mysqlurl = "jdbc:mysql://localhost:3306/copart";
	private static final String mysqluser = "root";
	private static final String mysqlpassword = "Raghav.0505";
	private static final String sqlcmd0="USE copart;";	// use the database statement
	private static ArrayList<Integer> zipcodes = new ArrayList<Integer>();
	
	public ArrayList<String> searchById(String userId) {
		//JSONObject resultJSON = new JSONObject();
		//ArrayList<ArrayList<String>> searchResult = null;
		ArrayList<String> facilities= null;
		Connection conn = null;
		String sqlcmd1 = "SELECT Facility FROM copart.users WHERE UserId="+ userId +";";
		//String sqlcmd1 = "SELECT F_ZipCode FROM copart.facilities ,copart.users WHERE ZipCode= zipCode AND Facility_Name= facility;";
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			//System.out.println("The sql statement is " + sqlcmd1);
			
			//searchResult = new ArrayList<ArrayList<String>>();
			facilities = new ArrayList<String>();
			while (result1.next()) {
				
				facilities.add(result1.getString("Facility"));				
				
			}
			//System.out.println("The search product result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		}
		
		return facilities;
	}
	
	public static ArrayList<Integer> searchByFacility(String  facility) {
		
		//JSONObject resultJSON = new JSONObject();
		//ArrayList<ArrayList<String>> searchResult = null;
		ArrayList<String> facilities= null;
		Connection conn = null;
		
		String sqlcmd1 = "SELECT F_ZipCode FROM copart.facilities WHERE facilities ="+ facility +";";
		
		//String sqlcmd1 = "SELECT F_ZipCode FROM copart.facilities ,copart.users WHERE ZipCode= zipCode AND Facility_Name= facility;";
		try {
			// connect to database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			Statement stmt1 = conn.createStatement();
			
			stmt1.executeQuery(sqlcmd0);	// use the database
			ResultSet result1 = stmt1.executeQuery(sqlcmd1);	// get the result
			//System.out.println("The sql statement is " + sqlcmd1);
			
			//searchResult = new ArrayList<ArrayList<String>>();
			
			while (result1.next()) {
				
				zipcodes.add(Integer.parseInt(result1.getString("F_ZipCode")));				
				
			}
			//System.out.println("The search product result is got successfully.");
					
			result1.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Error occurred during communicating with database.");
			e.printStackTrace();
		
		}
		
		return zipcodes;
	}

}
