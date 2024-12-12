package com.hostel;

public class Hostel {
	
	String HostelName;
	String HostelArea ;
	int NoOfRooms;
	int Vacancy_count;
	float PricePerPerson;
	
	Hostel(String HostelName,String HostelArea ,int NoOfRooms,int Vacancy_count,float PricePerPerson){
		
		this.HostelName=HostelName;
		 this.HostelArea= HostelArea;
		 this.NoOfRooms=NoOfRooms;
		 this.Vacancy_count=Vacancy_count;
	    this.PricePerPerson=PricePerPerson;
		
		
	}
	
	public float getPricePerPerson() {
		return PricePerPerson;
	}
	public void setPricePerPerson(float price) {
		PricePerPerson = price;
	}
	public String getHostelName() {
		return HostelName;
	}
	public void setHostelName(String hostelName) {
		HostelName = hostelName;
	}
	public String getHostelArea() {
		return HostelArea;
	}
	public void setHostelArea(String hostelArea) {
		HostelArea = hostelArea;
	}
	public int getNoOfRooms() {
		return NoOfRooms;
	}
	public void setNoOfRooms(int noOfRooms) {
		NoOfRooms = noOfRooms;
	}
	public int getVacancy_count() {
		return Vacancy_count;
	}
	public void setVacancy_count(int vacancy_count) {
		Vacancy_count = vacancy_count;
	}
	void displayDetails() {
		System.out.println("Name of Hostel:"+HostelName);
		System.out.println("Area of Hostel:"+HostelArea);
		System.out.println("Number of Rooms:"+NoOfRooms);
		System.out.println("Number of Vacancies:"+Vacancy_count);
		System.out.println("Price per person for Month:"+PricePerPerson);
	}
	
	

}
