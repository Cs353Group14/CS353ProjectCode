package cs353.group14;

import cs353.group14.db.ConnectionSingle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class Group14Application {


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
		PreparedStatement statement= ConnectionSingle.getConnection().prepareStatement(checkUserCounter);
		statement.setString(1, username);
		statement.setString(2, mail);
		ResultSet checkRS = statement.executeQuery();
		checkRS.next();
		int count = checkRS.getInt("noOfRecord");

		return count>0;

	}

	public static void insertUserTable(User user) throws SQLException {
		String insertUser = "Insert INTO users (username, password, mail, name, usertype) VALUES(?,?,?,?,?)";
		PreparedStatement insertStmt= ConnectionSingle.getConnection().prepareStatement(insertUser);
		insertStmt.setString(1,user.getUsername());
		insertStmt.setString(2,user.getPassword());
		insertStmt.setString(3,user.getMail());
		insertStmt.setString(4,user.getMail());
		insertStmt.setInt(5,user.getUserType().ordinal());
		int i = insertStmt.executeUpdate();
	}

	public static int getUserId(String username) throws SQLException {
		String getUserId = "SELECT user_id from users WHERE username = ?";
		PreparedStatement getUserIdPrepared= ConnectionSingle.getConnection().prepareStatement(getUserId);
		getUserIdPrepared.setString(1,username);
		ResultSet rs2 = getUserIdPrepared.executeQuery();
		rs2.next();

		return rs2.getInt("user_id");

	}


	public static void register( User user ){

		try {
			if(checkUserExist( user.getUsername(), user.getMail())){
				System.out.println("username or mail already used");
			}else{
				insertUserTable(user);
				int userId = getUserId(user.getUsername());
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
		switch (user.getUserType()){

			case Admin:
				insertUserWithType = "Insert INTO admin(user_id) VALUES(?)";
				insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.getUserId());
				break;

			case Coder:
				insertUserWithType = "Insert INTO coder(user_id,rating,points,position,place,birth_year) VALUES(?,?,?,?,?,?)";
				insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.getUserId());
				insertPrepared.setInt(2,0);
				insertPrepared.setInt(3,0);
				insertPrepared.setString(4,((Coder) user).getPosition());
				insertPrepared.setString(5,((Coder) user).getPlace());
				insertPrepared.setInt(6, ((Coder) user).getBirthYear() );
				break;

			case Editor:
				insertUserWithType = "Insert INTO editor(user_id,position,place) VALUES(?,?,?)";
				insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.getUserId());
				insertPrepared.setString(2,((Editor) user).getPosition());
				insertPrepared.setString(3,((Editor) user).getPlace());
				break;
			case Company:
				insertUserWithType = "Insert INTO company(user_id,location,web_page_link) VALUES(?,?,?)";
				insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
				insertPrepared.setInt(1,user.getUserId());
				insertPrepared.setString(2,((Company) user).getLocation());
				insertPrepared.setString(3,((Company) user).getWebPageLink());
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

	public static void createCodingChallenge(int editorId, CodingChallenge codingChallenge){


		try {
			int codingChallengeId = insertCodingChallengeTable(codingChallenge);
			insertCreatesTable(editorId,codingChallengeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}


		/*
		insertUserTable(user);
		int userId = getUserId(user.getUsername());
		user.setUserId(userId);
		insertUserWithType(user);
		 */

	}

	public static void insertCreatesTable(int editorId, int codingChallengeId)throws SQLException  {
		String insertCreatesChallenge = "Insert INTO creates(user_id,challenge_id) VALUES(?,?)";
		PreparedStatement insertCreatesPrepared= ConnectionSingle.getConnection().prepareStatement(insertCreatesChallenge);
		insertCreatesPrepared.setInt(1,editorId);
		insertCreatesPrepared.setInt(2,codingChallengeId);

		insertCreatesPrepared.executeUpdate();

	}


	public static int insertCodingChallengeTable(CodingChallenge codingChallenge)throws SQLException {

		String insertCodingChallenge = "Insert INTO coding_challenge(question,points,difficulty,solved_number,attempt_number,title,solution,publicity ) " +
				"VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement insertCodingPrepared= ConnectionSingle.getConnection().prepareStatement(insertCodingChallenge, Statement.RETURN_GENERATED_KEYS);
		insertCodingPrepared.setString(1,codingChallenge.getQuestion());
		insertCodingPrepared.setInt(2,codingChallenge.getPoints());
		insertCodingPrepared.setString(3,codingChallenge.getDifficulty());
		insertCodingPrepared.setInt(4,0);
		insertCodingPrepared.setInt(5,0);
		insertCodingPrepared.setString(6,codingChallenge.getTitle());
		insertCodingPrepared.setString(7,codingChallenge.getSolution());
		insertCodingPrepared.setInt(8,codingChallenge.getPublicity());


		insertCodingPrepared.executeUpdate();
		ResultSet keys = insertCodingPrepared.getGeneratedKeys();

		keys.next();

		return keys.getInt(1);
	}


	public static void main(String[] args) {
		SpringApplication.run(Group14Application.class, args);


		createTestUsers();

		for(int i=0;i<10;i++){
			createCodingChallenge(4,new CodingChallenge(-1,"q1",100,"zor",10,10,"title1","solution1",0));
		}




		/*
		User user1  = login("admin","admin");
		System.out.println(user1);
		User user2  = login("akin","1234");
		System.out.println(user2);
		User user3  = login("b","1234");
		System.out.println(user3);
		User user4  = login("q","1234");
		System.out.println(user4);
		*/

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
