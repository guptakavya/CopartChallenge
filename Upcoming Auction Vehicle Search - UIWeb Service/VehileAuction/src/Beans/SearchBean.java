package Beans;

import java.util.ArrayList;

public class SearchBean {
	private String year;
	private String model;
	private String make;
	private String region;
	private ArrayList<ArrayList<String>> searchResult;
	private String searchCard;
	private boolean isValidSearch;
	private ArrayList<ArrayList<String>> searchResult2;
	private String[] searchArray;
	
	private boolean isPaidFine;
	private boolean isUnpaidFine;
	public SearchBean(){
		year="";
		model="";
		make="";
		region="";
		searchResult = null;
		searchResult2= null;
		isValidSearch = false;
		searchCard="";
		isPaidFine=false;
		isUnpaidFine=false;
		searchArray=null;
	}

	public void setYear(String year){
		this.year=year;
		//System.out.println("this.search: " + this.search);
	}
	public void setModel(String model){
		this.model=model;
	}
	public void setMake(String make){
		this.make=make;
	}
	public void setRegion(String region){
		this.region=region;
	}
	public void setUnpaidFine(boolean isUnpaidFine){
		this.isUnpaidFine=isUnpaidFine;
	}
	public boolean getPaidFine(){
		return isPaidFine;
	}
	public boolean getUnpaidFine(){
		return isUnpaidFine;
	}
	public void setSearch3(String search3) {
		
	}
	public String getYear(){
		return year;
	}
	
	public String getModel(){
		return model;
	}
	public String getMake(){
		return make;
	}
	
	public String getRegion() {
		return region;
	}
	public String[] getSearchArray(){
		return searchArray;
	}
	
	public void setSearchArray(String[] searchArray){
		this.searchArray=searchArray;
	}
	
	public void setsearchResult(ArrayList<ArrayList<String>> searchResult) {
		this.searchResult = searchResult;
	}
	public void setsearchResult2(ArrayList<ArrayList<String>> searchResult2) {
		this.searchResult2 = searchResult2;
	}
	public ArrayList<ArrayList<String>> getsearchResult() {
		return searchResult;
	}
	public ArrayList<ArrayList<String>> getsearchResult2() {
		return searchResult2;
	}
	public void setValidation(boolean isValidSearch)
	{
		this.isValidSearch = isValidSearch;
	}
	public boolean isValidSearch()
	{
		return isValidSearch;
	}
}
