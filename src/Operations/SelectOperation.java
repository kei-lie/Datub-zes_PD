package Operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectOperation {
	
	private static final String RESET = "\u001B[0m";
	private static final String EVEN_ROW = "\u001B[33m";
	private static final String ODD_ROW = "\u001B[36m";
	
	public void select(Connection con, String tableOrView, int sk) {
		String sql = "SELECT * FROM " + tableOrView;
		
		//LIMIT daļa
		if(sk>0)
			sql = sql+ " LIMIT " + sk;
		
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)){
			
			ResultSetMetaData meta = rs.getMetaData();
			int colCount = meta.getColumnCount();
			int colWidth = 30;
			
			for(int i = 1; i <= colCount; i++) {
				System.out.printf("%-" + colWidth +"s", meta.getColumnName(i));
			}
			
			System.out.println();
			System.out.println("_".repeat(colCount * colWidth));
			
			int colInd = 0;
			while(rs.next()) {
				for(int i=1; i<=colCount; i++) {
					String value = rs.getString(i);
					
					colInd++;
					String col = (colInd % 2 == 0) ? EVEN_ROW : ODD_ROW;
					
					if(value == null) value = "NULL";
					
					else if(value.length() > colWidth - 5)
						value = value.substring(0, colWidth - 5) + "...";
					
					String formattedValue = String.format("%-" + colWidth + "s", value);
					System.out.print(col + formattedValue);
					
				}
				System.out.println(RESET);
			}
			
		}catch(SQLException e) {
			System.out.println("SELECT kļūda: " + e.getMessage());
		}
	}
}