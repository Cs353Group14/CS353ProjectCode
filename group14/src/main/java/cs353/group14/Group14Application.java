package cs353.group14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Group14Application {


	public static void main(String[] args) {
		SpringApplication.run(Group14Application.class, args);

		/*
		try {

			Connection connection =  DriverManager.getConnection("jdbc:postgresql://group14.ckx3kijsemgc.us-east-2.rds.amazonaws.com:5432/", "postgres", "postgrelc");

			Statement statement = connection.createStatement();

			// drop table
			String s = "CREATE TABLE users( " +
					"user_id SERIAL PRIMARY KEY, " +
					"username VARCHAR(31) NOT NULL UNIQUE, " +
					"password VARCHAR(31) NOT NULL, " +
					"mail VARCHAR(255) NOT NULL UNIQUE, " +
					"name VARCHAR(31) NOT NULL, " +
					"profile_photo BYTEA, " +
					"information VARCHAR(1023)) ";

			statement.executeUpdate(s);
			System.out.println("there");

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		 */


	}

}
