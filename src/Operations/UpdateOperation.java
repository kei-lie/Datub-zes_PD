package Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateOperation {
static Scanner scan = new Scanner(System.in);
	
	public void update(Connection con, String table) {
		try {
			switch(table){
			case "city" -> updateCity(con);
			case "actor" -> updateActor(con);
			case "customer" -> updateCustomer(con);
			case "film" -> updateFilm(con);
			default -> System.out.println("Neatbalstīta tabulla: "+table);
			}
			
		}catch(SQLException e) {
			System.out.println("UPDATE kļūda: " + e.getMessage());
		}
	}
	
	private void updateCity(Connection con) throws SQLException{
		System.out.println("Kuru pilsētu labot? Norādi ID:");
		int ID = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Ievadi pilsētas nosaukumu:");
		String name = scan.nextLine();
		System.out.println("Ievadi valsts ID:");
		int countryID = scan.nextInt();
		
		scan.nextLine();
		
		String sql = "UPDATE city SET city = ?, country_id = ? WHERE city_id = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setInt(2, countryID);
			ps.setInt(3, ID);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā atjaunots: "+rows+" rindas");
		}
	}
	
	private void updateActor(Connection con) throws SQLException{
		System.out.println("Kuru aktieri labot? Norādi ID:");
		int ID = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Ievadi aktiera vārdu:");
		String name = scan.nextLine();
		System.out.println("Ievadi aktiera uzvārdu:");
		String lastname = scan.nextLine();
		
		String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, lastname);
			ps.setInt(3, ID);
			int rows = ps.executeUpdate();
			System.out.println("ACTOR tabulā atjaunots: "+rows+" rindas");
		}
	}
	
	private void updateCustomer(Connection con) throws SQLException{
		System.out.println("Kuru klientu labot? Norādi ID:");
		int ID = scan.nextInt();
		scan.nextLine();
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
		
		String sql = "UPDATE customer SET store_id = ?, first_name = ?, last_name = ?, email = ?, address_id = ?, active = ? WHERE customer_id = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, store_id);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setString(4, email);
			ps.setInt(5, adress_id);
			ps.setInt(6, active);
			ps.setInt(7, ID);
			int rows = ps.executeUpdate();
			System.out.println("CUSTOMER tabulā atjaunots: "+rows+" rindas");
		}
	}
	
	private void updateFilm(Connection con) throws SQLException{
		System.out.println("Kuru filmu labot? Norādi ID:");
		int ID = scan.nextInt();
		scan.nextLine();
		
		// Izmet erroru ja ievada kaut ko lielāku par 1, bez vērtības neņem jo nav 'default'
				int language = 1;
		
		System.out.println("Ievadi filmas nosaukumu:");
		String title = scan.nextLine();
		System.out.println("Ievadi filmas aprakstu:");
		String desc = scan.nextLine();
		System.out.println("Norādi iznākšanas gadu:");
		int release = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Ievadi īrēšanas ilgumu:");
		int rental = scan.nextInt();
		scan.nextLine();

		System.out.println("Cik izmaksās īrēšana?");
		double rentRate = scan.nextDouble();
		 
		System.out.println("Ievadi filmas ilgumu minūtēs:");
		int length = scan.nextInt();
		scan.nextLine();
		System.out.println("Cik izmaksās aizstāšana?");
		double replace = scan.nextDouble();
		scan.nextLine();
		System.out.println("Ievadi filmas vecuma ierobežojumu:");
		String rating = scan.nextLine();
		System.out.println("Ievadi īpašās detaļas:");
		String specialFeat = scan.nextLine();
		
		String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, "
				+ "rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? WHERE film_id = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, title);
			ps.setString(2, desc);
			ps.setInt(3, release);
			ps.setInt(4, language);
			ps.setInt(5, rental);
			ps.setDouble(6, rentRate);
			ps.setInt(7, length);
			ps.setDouble(8, replace);
			ps.setString(9, rating);
			ps.setString(10, specialFeat);
			ps.setInt(11, ID);
			int rows = ps.executeUpdate();
			System.out.println("FILM tabulā atjaunots: "+rows+" rindas");
		}
	}
}
