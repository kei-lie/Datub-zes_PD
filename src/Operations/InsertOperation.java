package Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertOperation {
	static Scanner scan = new Scanner(System.in);
	public void insert (Connection con, String table) {
		try {
			switch(table) {
			case "city" -> insertCity(con);
			case "actor" -> insertActor(con);
			case "customer" -> insertCustomer(con);
			case "film" -> insertFilm(con);
			default -> System.out.println("Neatbalstīta tabula: "+table);
			}
		}catch(SQLException e){
			System.out.println("INSERT kļūda: " + e.getMessage());
		}
	}
	
	private void insertCity(Connection con) throws SQLException {
		//Trūkst ievades datu pārbaude
		System.out.println("Ievadi pilsētas nosaukumu:");
		String name = scan.nextLine();
		System.out.println("Ievadi valsts ID:");
		int countryID = scan.nextInt();
		
		scan.nextLine();
		
		String sql = "INSERT INTO city (city, country_id) VALUES (?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setInt(2, countryID);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā ievietotas: "+rows+" rindas");
		}
	}
	
	private void insertActor(Connection con) throws SQLException {
		//Trūkst ievades datu pārbaude
		System.out.println("Ievadi aktiera vārdu:");
		String name = scan.nextLine();
		System.out.println("Ievadi aktiera uzvārdu:");
		String lastname = scan.nextLine();
		
		String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, lastname);
			int rows = ps.executeUpdate();
			System.out.println("ACTOR tabulā ievietotas: "+rows+" rindas");
		}
	}
	
	private void insertCustomer(Connection con) throws SQLException {
		//Trūkst ievades datu pārbaude
		System.out.println("Ievadi veikala ID:");
		int store_id = scan.nextInt();
		scan.nextLine();
		System.out.println("Ievadi klienta vārdu:");
		String name = scan.nextLine();
		System.out.println("Ievadi klienta uzvārdu:");
		String lastname = scan.nextLine();
		System.out.println("Ievadi e-pastu:");
		String email = scan.nextLine();
		System.out.println("Ievadi adreses ID:");
		int adress_id = scan.nextInt();
		scan.nextLine();
		System.out.println("Vai ir aktīvs? (1 = Jā, 0 = Nē)");
		int active = scan.nextInt();
		scan.nextLine();
		
		
		String sql = "INSERT INTO customer (store_id, first_name, last_name, email, address_id, active) VALUES (?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, store_id);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setString(4, email);
			ps.setInt(5, adress_id);
			ps.setInt(6, active);
			int rows = ps.executeUpdate();
			System.out.println("CUSTOMER tabulā ievietotas: "+rows+" rindas");
		}
	}
	
	private void insertFilm(Connection con) throws SQLException {
		//Trūkst ievades datu pārbaude
		System.out.println("Ievadi filmas nosaukumu:");
		String title = scan.nextLine();
		System.out.println("Ievadi filmas aprakstu:");
		String desc = scan.nextLine();
		System.out.println("Norādi iznākšanas gadu:");
		int release = scan.nextInt();
		scan.nextLine();
		System.out.println("Ievadi valodas ID:");
		int language = scan.nextInt();
		scan.nextLine();
		System.out.println("Ievadi īrēšanas garumu:");
		int rental = scan.nextInt();
		scan.nextLine();
		 
		System.out.println("Ievadi filmas ilgumu minūtēs:");
		int length = scan.nextInt();
		scan.nextLine();
		System.out.println("Cik izmaksās aizstāšana?");
		double replace = scan.nextDouble();
		System.out.println("Ievadi filmas vecuma ierobežojumu:");
		String rating = scan.nextLine();
		System.out.println("Ievadi īpašās detaļas:");
		String specialFeat = scan.nextLine();
		
		
		String sql = "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, "
				+ "GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, code);
			ps.setString(2, name);
			ps.setString(3, continent);
			ps.setString(4, region);
			ps.setInt(5, surfaceArea);
			ps.setInt(6, indepYear);
			ps.setInt(7, population);
			ps.setInt(8, lifeExp);
			ps.setInt(9, GNP);
			ps.setInt(10, GNPOld);
			ps.setString(11, LocalName);
			ps.setString(12, GovernForm);
			ps.setString(13, headState);
			ps.setString(14, capital);
			ps.setInt(15, code2);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā ievietotas: "+rows+" rindas");
		}
	}
}
