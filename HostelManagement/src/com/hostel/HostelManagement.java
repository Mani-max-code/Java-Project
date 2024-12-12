package com.hostel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HostelManagement {

	public void registerHostel() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Hostel Name:");
	    String hostelName = sc.nextLine();
	    System.out.println("Enter Hostel Area:");
	    String hostelArea = sc.nextLine();
	    System.out.println("Enter Number of Rooms:");
	    int noOfRooms = sc.nextInt();
	    System.out.println("Enter Number of Vacancies:");
	    int vacancyCount = sc.nextInt();
	    System.out.println("Enter Price per Person per Month:");
	    float pricePerPerson = sc.nextFloat();

	    // Create Hostel object
	    Hostel hostel = new Hostel(hostelName, hostelArea, noOfRooms, vacancyCount, pricePerPerson);

	    String sql = "INSERT INTO Hostel (HostelName, HostelArea, NoOfRooms, VacancyCount, PricePerPerson) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DBUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, hostel.getHostelName());
	        stmt.setString(2, hostel.getHostelArea());
	        stmt.setInt(3, hostel.getNoOfRooms());
	        stmt.setInt(4, hostel.getVacancy_count());
	        stmt.setFloat(5, hostel.getPricePerPerson());
	        int rowsInserted = stmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("Hostel registered successfully.");
	        } else {
	            System.out.println("Failed to register hostel.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error registering hostel: " + e.getMessage());
	    }
	}


	public void editPrice() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter hostel in which price should change:");
	    String name = sc.nextLine();
	    System.out.println("Enter the new price for month:");
	    float price = sc.nextFloat();

	    String selectSql = "SELECT * FROM Hostel WHERE HostelName = ?";
	    String updateSql = "UPDATE Hostel SET PricePerPerson = ? WHERE HostelName = ?";
	    try (Connection conn = DBUtils.getConnection();
	         PreparedStatement selectStmt = conn.prepareStatement(selectSql);
	         PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
	        
	        // Fetch the existing hostel details
	        selectStmt.setString(1, name);
	        try (ResultSet rs = selectStmt.executeQuery()) {
	            if (rs.next()) {
	                // Create a Hostel object with the fetched data
	                Hostel hostel = new Hostel(
	                    rs.getString("HostelName"),
	                    rs.getString("HostelArea"),
	                    rs.getInt("NoOfRooms"),
	                    rs.getInt("VacancyCount"),
	                    rs.getFloat("PricePerPerson")
	                );

	                // Update the price in the Hostel object
	                hostel.setPricePerPerson(price);

	                // Update the database
	                updateStmt.setFloat(1, hostel.getPricePerPerson());
	                updateStmt.setString(2, hostel.getHostelName());
	                int rowsUpdated = updateStmt.executeUpdate();
	                if (rowsUpdated > 0) {
	                    System.out.println("Price updated successfully for " + hostel.getHostelName());
	                    hostel.displayDetails(); // Display updated details
	                } else {
	                    System.out.println("Failed to update the price.");
	                }
	            } else {
	                System.out.println("Hostel not found.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating price: " + e.getMessage());
	    }
	}


	public void updateVacancies() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter hostel in which vacancies should change:");
	    String name = sc.nextLine();
	    System.out.println("Enter the new number of vacancies:");
	    int vacancies = sc.nextInt();

	    String selectSql = "SELECT * FROM Hostel WHERE HostelName = ?";
	    String updateSql = "UPDATE Hostel SET VacancyCount = ? WHERE HostelName = ?";
	    try (Connection conn = DBUtils.getConnection();
	         PreparedStatement selectStmt = conn.prepareStatement(selectSql);
	         PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
	        
	        // Fetch the existing hostel details
	        selectStmt.setString(1, name);
	        try (ResultSet rs = selectStmt.executeQuery()) {
	            if (rs.next()) {
	                // Create a Hostel object with the fetched data
	                Hostel hostel = new Hostel(
	                    rs.getString("HostelName"),
	                    rs.getString("HostelArea"),
	                    rs.getInt("NoOfRooms"),
	                    rs.getInt("VacancyCount"),
	                    rs.getFloat("PricePerPerson")
	                );

	                // Update the vacancies in the Hostel object
	                hostel.setVacancy_count(vacancies);

	                // Update the database
	                updateStmt.setInt(1, hostel.getVacancy_count());
	                updateStmt.setString(2, hostel.getHostelName());
	                int rowsUpdated = updateStmt.executeUpdate();
	                if (rowsUpdated > 0) {
	                    System.out.println("Vacancies updated successfully for " + hostel.getHostelName());
	                    hostel.displayDetails(); // Display updated details
	                } else {
	                    System.out.println("Failed to update the vacancies.");
	                }
	            } else {
	                System.out.println("Hostel not found.");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating vacancies: " + e.getMessage());
	    }
	}


    public void areaHostel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter area to search for hostels:");
        String area = sc.nextLine();

        String sql = "SELECT * FROM Hostel WHERE HostelArea = ?";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, area);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    Hostel hostel = new Hostel(
                        rs.getString("HostelName"),
                        rs.getString("HostelArea"),
                        rs.getInt("NoOfRooms"),
                        rs.getInt("VacancyCount"),
                        rs.getFloat("PricePerPerson")
                    );
                    hostel.displayDetails();
                    System.out.println("--------------------------------------------------");
                    found = true;
                }
                if (!found) {
                    System.out.println("No hostels found in " + area);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving hostels: " + e.getMessage());
        }
    }


    public void showHostels() {
        String sql = "SELECT * FROM Hostel";
        try (Connection conn = DBUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            boolean found = false;
            while (rs.next()) {
                Hostel hostel = new Hostel(
                    rs.getString("HostelName"),
                    rs.getString("HostelArea"),
                    rs.getInt("NoOfRooms"),
                    rs.getInt("VacancyCount"),
                    rs.getFloat("PricePerPerson")
                );
                hostel.displayDetails();
                System.out.println("--------------------------------------------------");
                found = true;
            }
            if (!found) {
                System.out.println("No hostels found.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving hostels: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        HostelManagement hm = new HostelManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nHostel Management System");
            System.out.println("1. Register Hostel (Admin Only)");
            System.out.println("2. Edit Price (Admin Only)");
            System.out.println("3. Update Vacancies (Admin Only)");
            System.out.println("4. View All Hostels (User)");
            System.out.println("5. Search Hostels by Area (User)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter role (admin/user): ");
                    String role = sc.nextLine().toLowerCase();
                    if (role.equals("admin")) {
                        hm.registerHostel();
                    } else {
                        System.out.println("Only admins can register hostels.");
                    }
                    break;
                case 2:
                    System.out.print("Enter role (admin/user): ");
                    role = sc.nextLine().toLowerCase();
                    if (role.equals("admin")) {
                        hm.editPrice();
                    } else {
                        System.out.println("Only admins can edit prices.");
                    }
                    break;
                case 3:
                    System.out.print("Enter role (admin/user): ");
                    role = sc.nextLine().toLowerCase();
                    if (role.equals("admin")) {
                        hm.updateVacancies();
                    } else {
                        System.out.println("Only admins can update vacancies.");
                    }
                    break;
                case 4:
                    hm.showHostels();
                    break;
                case 5:
                    hm.areaHostel();
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

