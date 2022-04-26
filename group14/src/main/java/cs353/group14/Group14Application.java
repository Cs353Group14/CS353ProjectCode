package cs353.group14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.*;
import java.util.Random;

@SpringBootApplication
public class Group14Application {

	public static String transformString(String text){
		return "'"+text+"'";
	}

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

	public static void register(Connection connection, String username, String password,String mail,String name, String userType, String[] params ){
		username = transformString(username);
		password = transformString(password);
		mail = transformString(mail);
		name = transformString(name);

		try {
			Statement statement = connection.createStatement();

			String checkUserCounter = "SELECT COUNT(*) AS recordCount from users WHERE username = "+username+" or mail = "+mail;
			ResultSet rs = statement.executeQuery( checkUserCounter );
			rs.next();
			int count = rs.getInt("recordCount");

			if(count>0){
				System.out.println("username or mail already used");
			}else{
				String insertUser = "Insert INTO users (username, password, mail, name)" +
						" VALUES("+username+","+password+","+mail+","+name+")";
				int i = statement.executeUpdate(insertUser);

				String getUserId = "SELECT user_id from users WHERE username = "+username;
				ResultSet rs2 = statement.executeQuery( getUserId );
				rs2.next();

				int userId = rs2.getInt("user_id");
				System.out.println(userId);

				String insertUserWithType = registerQuery(userId,userType, params);

				System.out.println(insertUserWithType);

				i = statement.executeUpdate(insertUserWithType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static String registerQuery(int userId, String userType, String[] params){

		String insertUserWithType;

		switch (userType){
			case "admin":
				insertUserWithType = "Insert INTO admin(user_id)" +
						"VALUES("+userId+")";
				break;
			case "coder":
				String coderPosition = transformString(params[0]);
				String place = transformString(params[1]);
				int birth_year = Integer.parseInt(params[2]);
				insertUserWithType = "Insert INTO coder(user_id,rating,points,position,place,birth_year)" +
						"VALUES("+userId+",0, 0,"+coderPosition+","+place+","+birth_year+")";
				break;
			case "editor":
				String editorPosition = transformString(params[0]);
				String editorPlace = transformString(params[1]);
				insertUserWithType = "Insert INTO editor(user_id,position,place)" +
						"VALUES("+userId+","+ editorPosition+","+editorPlace+")";
				break;
			case "company":
				String location = transformString(params[0]);
				String web_page_link = transformString(params[1]);
				insertUserWithType = "Insert INTO company(user_id,location,web_page_link)" +
						"VALUES("+userId+","+ location+","+ web_page_link+")";
				break;
			default:
				insertUserWithType = "error???";

		}

		return insertUserWithType;

	}

	public static void createTestUsers(Connection connection){
		String username = "admin";
		String password = "admin";
		String mail = "admin@admin.com";
		String name = "admin";
		String[] params = null;

		register(connection,username,password,mail,name,"admin", params);

		username = "akin";
		password = "1234";
		mail = "akin@gmail.com";
		name = "akin kutlu";
		params = new String[3];

		params[0] = "position1";
		params[1] = "place1";
		params[2] = "1990";
		register(connection,username,password,mail,name,"coder", params);

		username = "b";
		password = "1234";
		mail = "b@gmail.com";
		name = "b c";

		params[0] = "location1";
		params[1] = "web_page_link1";

		register(connection,username,password,mail,name,"company", params);

		username = "q";
		password = "1234";
		mail = "q@gmail.com";
		name = "q m";

		params[0] = "position1";
		params[1] = "place1";

		register(connection,username,password,mail,name,"editor", params);

	}


	public static void login( Connection connection, String username, String password) {

		try {
			String loginQuery = "SELECT user_id, username from users WHERE username =" + transformString(username) + "and password =" + transformString(password);
			Statement statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(loginQuery);
			printResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}



	public static void main(String[] args) {
		SpringApplication.run(Group14Application.class, args);

		Connection connection = null;

		try {
			connection =  DriverManager.getConnection("jdbc:postgresql://group14.ckx3kijsemgc.us-east-2.rds.amazonaws.com:5432/", "postgres", "postgrelc");
			System.out.println("Connection successful.");


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		//bir kere çalıştırmak yeterli
		//createUserTables(connection);

		//createTestUsers(connection);

		login(connection,"admin","admin");

		login(connection,"akin","1234");
		login(connection,"b","1234");
		login(connection,"q","1234");





		System.exit(0);

	}

	public static void createUserTables(Connection connection){

		int sqlCount = 5;

		String[] createSqls = new String[sqlCount];
		String[] dropSqls = new String[sqlCount];

		dropSqls[0] = "users";
		dropSqls[1] = "admin";
		dropSqls[2] = "editor";
		dropSqls[3] = "company";
		dropSqls[4] = "coder";

		createSqls[0]  = "CREATE TABLE users( " +
				"user_id SERIAL PRIMARY KEY, " +
				"username VARCHAR(31) NOT NULL UNIQUE, " +
				"password VARCHAR(31) NOT NULL, " +
				"mail VARCHAR(255) NOT NULL UNIQUE, " +
				"name VARCHAR(31) NOT NULL, " +
				"profile_photo BYTEA, " +
				"information VARCHAR(1023)) ";

		createSqls[1] = "CREATE TABLE admin( " +
				"user_id INTEGER REFERENCES users (user_id), " +
				"PRIMARY KEY (user_id) )";

		createSqls[2] ="CREATE TABLE editor(" +
				"user_id INTEGER REFERENCES users (user_id)," +
				"position VARCHAR(31)," +
				"place VARCHAR(31)," +
				"PRIMARY KEY (user_id))";

		createSqls[3] = "CREATE TABLE company(" +
				"user_id INTEGER REFERENCES users (user_id)," +
				"location VARCHAR(255)," +
				"web_page_link VARCHAR(63)," +
				"PRIMARY KEY (user_id))";

		createSqls[4] = "CREATE TABLE coder(" +
				"   user_id INTEGER REFERENCES users (user_id)," +
				"   rating INTEGER NOT NULL," +
				"   points INTEGER NOT NULL," +
				"   position VARCHAR(31)," +
				"   place VARCHAR(255)," +
				"   birth_year INTEGER NOT NULL," +
				"   PRIMARY KEY (user_id))";

		try {

			Statement statement = connection.createStatement();
			for(int i=sqlCount-1;i>-1;i--){
				statement.executeUpdate("DROP TABLE IF EXISTS "+dropSqls[i]);
			}

			for(int i=0;i<sqlCount;i++){
				statement.executeUpdate(createSqls[i]);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
