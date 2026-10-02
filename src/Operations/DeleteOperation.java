package Operations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteOperation {
static Scanner scan = new Scanner(System.in);
	
	public void delete (Connection con, String table) {
		try {
			switch(table) {
			case "city" -> deleteCity(con);
			case "actor" -> deleteActor(con);
			case "customer" -> deleteCustomer(con);
			case "film" -> deleteFilm(con);
			default -> System.out.println("Neatbalstīta tabula: "+table);
			}
		}catch(SQLException e) {
			System.out.println("DELETE kļūda: "+e.getMessage());
		}
	}
	
	private void deleteCity(Connection con) throws SQLException{
		System.out.println("Norādi pilsētas ID, kuru vēlies dzēst");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM city WHERE city_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("CITY tabulā dzēstas "+ rows + " ieraksti");
		}
	}
	
	private void deleteActor(Connection con) throws SQLException{
		System.out.println("Norādi aktiera ID, kuru vēlies dzēst");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM actor WHERE actor_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("ACTOR tabulā dzēstas "+ rows + " ieraksti");
		}
	}
	
	private void deleteCustomer(Connection con) throws SQLException{
		System.out.println("Norādi klienta ID, kuru vēlies dzēst");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM customer WHERE customer_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("CUSTOMER tabulā dzēstas "+ rows + " ieraksti");
		}
	}
	
	private void deleteFilm(Connection con) throws SQLException{
		System.out.println("Norādi filmas ID, kuru vēlies dzēst");
		int id = scan.nextInt();
		scan.nextLine();
		
		String sql = "DELETE FROM film WHERE film_id = ?";
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			int rows = ps.executeUpdate();
			System.out.println("FILM tabulā dzēstas "+ rows + " ieraksti");
		}
	}
}
