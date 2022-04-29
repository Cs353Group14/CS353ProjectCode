package cs353.group14.db;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionSingle {

    private static Connection connection = null;

    public ConnectionSingle() {

        try {
            connection =  DriverManager.getConnection("jdbc:postgresql://group14.ckx3kijsemgc.us-east-2.rds.amazonaws.com:5432/", "postgres", "postgrelc");
            System.out.println("Connection successful.");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() {
         return connection;
     }
}
