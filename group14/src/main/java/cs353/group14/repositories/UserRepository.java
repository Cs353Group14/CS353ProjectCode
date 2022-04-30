package cs353.group14.repositories;


import cs353.group14.Coder;
import cs353.group14.Company;
import cs353.group14.Editor;
import cs353.group14.User;
import cs353.group14.db.ConnectionSingle;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    public boolean checkUserExist( String username, String mail) {
        String checkUserCounter = "SELECT COUNT(*) AS noOfRecord from users WHERE username = ? or mail = ?";

        try {
            PreparedStatement statement= ConnectionSingle.getConnection().prepareStatement(checkUserCounter);
            statement.setString(1, username);
            statement.setString(2, mail);
            ResultSet checkRS = statement.executeQuery();
            checkRS.next();
            int count = checkRS.getInt("noOfRecord");

            return count>0;
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }

    public void insertUser(User user) {
        String insertUser = "Insert INTO users (username, password, mail, name, usertype) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement insertStmt= ConnectionSingle.getConnection().prepareStatement(insertUser);
            insertStmt.setString(1,user.getUsername());
            insertStmt.setString(2,user.getPassword());
            insertStmt.setString(3,user.getMail());
            insertStmt.setString(4,user.getName());
            insertStmt.setInt(5,user.getUserType().ordinal());
            int i = insertStmt.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public int getUserId(String username) {
        String getUserId = "SELECT user_id from users WHERE username = ?";

        try{
            PreparedStatement getUserIdPrepared= ConnectionSingle.getConnection().prepareStatement(getUserId);
            getUserIdPrepared.setString(1,username);
            ResultSet rs2 = getUserIdPrepared.executeQuery();
            rs2.next();

            return rs2.getInt("user_id");
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return -1;

    }

    public void insertCoder(int userId, Coder coder)  {
        String sql = "Insert INTO coder(user_id,rating,points,position,place,birth_year) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement insertPrepared = ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);
            insertPrepared.setInt(2,0);
            insertPrepared.setInt(3,0);
            insertPrepared.setString(4,coder.getPosition());
            insertPrepared.setString(5,coder.getPlace());
            insertPrepared.setInt(6, coder.getBirthYear() );

            insertPrepared.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertEditor(int userId, Editor editor)  {
        String sql = "Insert INTO editor(user_id,position,place) VALUES(?,?,?)";

        try {
            PreparedStatement insertPrepared= ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);
            insertPrepared.setString(2,editor.getPosition());
            insertPrepared.setString(3, editor.getPlace());

            insertPrepared.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertCompany(int userId, Company company)  {
        String sql = "Insert INTO company(user_id,location,web_page_link) VALUES(?,?,?)";
        try {
            PreparedStatement insertPrepared= ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);
            insertPrepared.setString(2, company.getLocation());
            insertPrepared.setString(3, company.getWebPageLink());

            insertPrepared.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
