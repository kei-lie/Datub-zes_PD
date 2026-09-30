package Operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import db.DatabaseConnection;
import Operations.ViewManager;
//import operation.DeleteOperation;
import Operations.InsertOperation;
//import operation.SelectOperation;
//import operation.UpdateOperation;

public class sakila_db {
	
	private static Connection con;
	private static final Scanner scan = new Scanner(System.in);
	
	static final String RESET = "\u001B[0m";
	static final String CYAN = "\u001B[36m";
	static final String GREEN = "\u001B[32m";
	static final String RED = "\u001B[31m";
	
	private static String chooseTable() {
		while(true) {
			System.out.println(CYAN + "\n ----- TABULAS -----\n" + RESET
					+ "1. CITY\n"
					+ "2. ACTOR\n"
					+ "3. CUSTOMER\n"
					+ "4. STORE\n"
					+ "0. Atpakaļ\n"
					+ "Izvēlies tabulu: ");
			String c = scan.nextLine().trim();
			
			return switch(c) {
			case "1" -> "city";
			case "2" -> "actor";
			case "3" -> "customer";
			case "4" -> "store";
			case "0" -> "exit";
			default -> {
				System.out.println("Nepareiza izvēle.");
				yield "exit";
			}
		};	
	}
}
	
	private static void tableMenu(String table, SelectOperation selectOp, InsertOperation insertOp) {
		boolean back = false;
		while (!back) {
			System.out.println("\n--- " + table.toUpperCase() + " ---\n"
					+ "1. Atlasīt (SELECT)\n"
					+ "2. Pievienot (INSERT)\n"
					+ "3. Atjaunot (UPDATE)\n"
					+ "4. Dzēst (DELETE)\n"
					+ "0. Atpakaļ (RETURN)\n"
					+ "Izvēle: ");
			String c = scan.nextLine().trim();
			
			
			
			switch(c) {
				case "1" -> {
					selectOp.select(con, table);
//					System.out.println("Vai limitēt SELECT rindu skaitu?(J/N)");
//					String atbilde = scan.nextLine();
					
//					while(atbilde != "J" || atbilde != "N") System.out.println("Nepareiza datu ievade");
//					if(atbilde == "J") {
//						System.out.println("Kāds būs rindu skaits?");
//						int sk = scan.nextInt();
//						selectOp.selectLimit(con, table, sk);
//					}else if(atbilde == "N") selectOp.select(con, table);
					
				}
				case "2" -> insertOp.insert(con, table);
//				case "3" -> updateOp.update(con, table);
//				case "4" -> deleteOp.delete(con, table);
				case "0" -> back = true;
				default -> System.out.println("Nepareiza izvēle.");
			}
		}
	}

	public static void main(String[] args) {
		try {
			con = DatabaseConnection.getConnection();
			System.out.println("Izveidots savienojums ar DB world.");
			
			boolean running = true;
		
			SelectOperation selectOp = new SelectOperation();
			ViewManager viewManager = new ViewManager(con, selectOp, scan);
			InsertOperation insertOp = new InsertOperation();
//			UpdateOperation updateOp = new UpdateOperation();
//			DeleteOperation deleteOp = new DeleteOperation();
		
			while(running) {
				System.out.println(CYAN + "\n----- SAKILA DB -----\n" + RESET
						+ GREEN +  "1. Tabulas\n" 
						+ "2. Skati\n" + RESET
						+ RED + "0. Apturēt\n" + RESET
						+ CYAN + "Izvēle: " + RESET);
				String mainChoice = scan.nextLine().trim();
				
				switch(mainChoice) {
				case "1" -> {
					String table = chooseTable();
					if(!table.equals("exit")) {
						tableMenu(table, selectOp, insertOp);
//						, insertOp, updateOp, deleteOp
					}
				}
				
				case "2" -> 
				viewManager.showViewsMenu();
				
				case "0" -> running = false;
				
				default -> System.out.println(RED + "Nepareiza izvēle!" + RESET);
				
				}
				
			}
			
			con.close();
			System.out.println("Savienojums ar DB slēgts.");
			
		} catch (SQLException e) {
				System.out.println("DB kļūda: " + e.getMessage());
			}
		}

}
