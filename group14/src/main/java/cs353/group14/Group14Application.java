package cs353.group14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class Group14Application {

	public static Connection connection = null;

	public static void createUserTables(){

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
				"usertype INTEGER NOT NULL, " +
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

	// İçeriğini direkt bastırmak için kullanabilirsinize
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

	public static boolean checkUserExist( String username, String mail) throws SQLException {
		String checkUserCounter = "SELECT COUNT(*) AS noOfRecord from users WHERE username = ? or mail = ?";
		PreparedStatement statement= connection.prepareStatement(checkUserCounter);
		statement.setString(1, username);
		statement.setString(2, mail);
		ResultSet checkRS = statement.executeQuery();
		checkRS.next();
		int count = checkRS.getInt("noOfRecord");

		return count>0;

	}

	public static void insertUserTable(User user) throws SQLException {
		String insertUser = "Insert INTO users (username, password, mail, name, usertype) VALUES(?,?,?,?,?)";
		PreparedStatement insertStmt= connection.prepareStatement(insertUser);
		insertStmt.setString(1,user.username);
		insertStmt.setString(2,user.password);
		insertStmt.setString(3,user.mail);
		insertStmt.setString(4,user.name);
		insertStmt.setInt(5,user.userType.ordinal());
		int i = insertStmt.executeUpdate();
	}

	public static int getUserId(String username) throws SQLException {
		String getUserId = "SELECT user_id from users WHERE username = ?";
		PreparedStatement getUserIdPrepared= connection.prepareStatement(getUserId);
		getUserIdPrepared.setString(1,username);
		ResultSet rs2 = getUserIdPrepared.executeQuery();
		rs2.next();

		return rs2.getInt("user_id");

	}


	public static void register( User user ){

		try {
			if(checkUserExist( user.username, user.mail)){
				System.out.println("username or mail already used");
			}else{
				insertUserTable(user);
				int userId = getUserId(user.username);
				user.setUserId(userId);
				insertUserWithType(user);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void insertUserWithType(User user) throws SQLException {

		PreparedStatement insertPrepared = null;
		String insertUserWithType;
		switch (user.userType){

			case Admin:
				insertUserWithType = "Insert INTO admin(user_id) VALUES(?)";
				insertPrepared= connection.prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.userId);
				break;

			case Coder:
				insertUserWithType = "Insert INTO coder(user_id,rating,points,position,place,birth_year) VALUES(?,?,?,?,?,?)";
				insertPrepared= connection.prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.userId);
				insertPrepared.setInt(2,0);
				insertPrepared.setInt(3,0);
				insertPrepared.setString(4,((Coder) user).position);
				insertPrepared.setString(5,((Coder) user).place);
				insertPrepared.setInt(6, ((Coder) user).birthYear );
				break;

			case Editor:
				insertUserWithType = "Insert INTO editor(user_id,position,place) VALUES(?,?,?)";
				insertPrepared= connection.prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.userId);
				insertPrepared.setString(2,((Editor) user).position);
				insertPrepared.setString(3,((Editor) user).place);
				break;
			case Company:
				insertUserWithType = "Insert INTO company(user_id,location,web_page_link) VALUES(?,?,?)";
				insertPrepared= connection.prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.userId);
				insertPrepared.setString(2,((Company) user).location);
				insertPrepared.setString(3,((Company) user).webPageLink);
				break;
		}

		if (insertPrepared!=null){
			insertPrepared.executeUpdate();
		}else{
			System.out.println("error in insertUserWithType");
		}

	}

	public static void createTestUsers()  {
		String username = "admin";
		String password = "admin";
		String mail = "admin@admin.com";
		String name = "admin";
		int userId = -1;

		register(new Admin(userId,username,mail,password, UserType.Admin, name, "", ""));

		username = "akin";
		password = "1234";
		mail = "akin@gmail.com";
		name = "akin kutlu";

		String position = "position1";
		String place = "place1";
		int year = 1990;
		register(new Coder(userId,username,mail,password,UserType.Coder,name,"","",0,0,position,place,year));

		username = "b";
		password = "1234";
		mail = "b@gmail.com";
		name = "b c";

		String location = "location1";
		String link = "web_page_link1";

		register(new Company(userId,username,mail,password,UserType.Company,name,"","",location,link));

		username = "q";
		password = "1234";
		mail = "q@gmail.com";
		name = "q m";

		position = "position1";
		place = "place1";

		register(new Editor(userId,username,mail,password,UserType.Editor,name,"","",position,place));

	}


	public static User login( String username, String password) {
		User user = null;

		try {
			String loginQuery = "SELECT * from users WHERE username = ? and password = ?";
			PreparedStatement loginStmt = connection.prepareStatement(loginQuery);
			loginStmt.setString(1,username);
			loginStmt.setString(2,password);
			ResultSet rs = loginStmt.executeQuery();

			rs.next();
			int userId = rs.getInt("user_id");
			int userTypeInt = rs.getInt("usertype");
			UserType userType = UserType.values()[userTypeInt];
			String mail = rs.getString("mail");
			String name = rs.getString("name");
			String information = rs.getString("information");
			//Byte[] foto; // şimdilik dursun

			System.out.println(userType);

			String dataForUserType = "SELECT * from "+userType+" WHERE user_id = ?";
			PreparedStatement getUserDataStmt = connection.prepareStatement(dataForUserType);
			getUserDataStmt.setInt(1,userId);
			ResultSet rs2 = getUserDataStmt.executeQuery();
			rs2.next();

			switch (userType){
				case Admin:
					user = new Admin(userId,username,mail,password,userType,name,information,"");
					break;
				case Coder:
					int rating = rs2.getInt("rating");
					int points = rs2.getInt("points");
					String position = rs2.getString("position");
					String place = rs2.getString("place");
					int birthYear = rs2.getInt("birth_year");
					user = new Coder(userId,username,mail,password,userType,name,information,"",rating,points,position,place,birthYear);
					break;
				case Company:
					String location = rs2.getString("location");
					String webPageLink = rs2.getString("web_page_link");
					user = new Company(userId,username,mail,password,userType,name,information,"",location,webPageLink);
					break;
				case Editor:
					String positionE = rs2.getString("position");
					String placeE = rs2.getString("place");
					user = new Editor(userId,username,mail,password,userType,name,information,"",positionE,placeE);
					break;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}



	public static void main(String[] args) {
		SpringApplication.run(Group14Application.class, args);

		try {
			connection =  DriverManager.getConnection("jdbc:postgresql://group14.ckx3kijsemgc.us-east-2.rds.amazonaws.com:5432/", "postgres", "postgrelc");
			System.out.println("Connection successful.");


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		createUserTables();
		createTestUsers();
		User user1  = login("admin","admin");
		System.out.println(user1);
		User user2  = login("akin","1234");
		System.out.println(user2);
		User user3  = login("b","1234");
		System.out.println(user3);
		User user4  = login("q","1234");
		System.out.println(user4);
		/*

		try {

			String username = "admin";
			String password = "admin";
			String mail = "admin@admin.com";
			register(username, "kb", mail, "kd", "admin", null);
			login("admin","admin");
			login("akin","1234");
			login("b","1234");
			login("q","1234");


		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		*/

		System.exit(0);

	}



}
