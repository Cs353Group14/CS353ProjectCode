package cs353.group14;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class Group14Application {


	// İçeriğini direkt bastırmak için kullanabilirsiniz
	public static void printResultSet(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(" | ");
				System.out.print(rs.getString(i));
			}
			System.out.println("");
		}
	}


	public static void main(String[] args) {
		SpringApplication.run(Group14Application.class, args);

		//System.exit(0);

	}



}
