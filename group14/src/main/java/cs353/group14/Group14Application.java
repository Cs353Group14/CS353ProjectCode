package cs353.group14;


import cs353.group14.db.ConnectionSingle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

		/*
		System.out.println("hello");

		int userId = 3;


		try {
			String getChallengeSql = "Select * from attend A, interview I,company C, users U where I.interview_id = A.interview_id and C.user_id = I.user_id and A.coder_id = ? and A.start_time > ? and A.start_time < ? and C.user_id = U.user_id and A.end_time > CURRENT_TIMESTAMP + INTERVAL '03:00' HOUR TO MINUTE";
			PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
			insertCodingPrepared.setInt(1,userId);
			insertCodingPrepared.setInt(2,userId);
			ResultSet rs = insertCodingPrepared.executeQuery();

			printResultSet(rs);


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		System.exit(0);

		 */

	}



}
