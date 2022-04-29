package cs353.group14.repositories;


import cs353.group14.User;
import cs353.group14.db.ConnectionSingle;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    public boolean checkUserExist( String username, String mail) throws SQLException {
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
        insertStmt.setString(1,user.username);
        insertStmt.setString(2,user.password);
        insertStmt.setString(3,user.mail);
        insertStmt.setString(4,user.name);
        insertStmt.setInt(5,user.userType.ordinal());
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
}
