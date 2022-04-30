package cs353.group14.repositories;


import cs353.group14.*;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.requests.LoginRequest;
import cs353.group14.responses.LoginResponse;
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

    public void insertAdmin(int userId)  {
        String sql = "Insert INTO admin(user_id) VALUES(?)";

        try {
            PreparedStatement insertPrepared= ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);

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

    public User login( String username, String password) {
        User user = null;

        try {
            String loginQuery = "SELECT * from users WHERE username = ? and password = ?";
            PreparedStatement loginStmt = ConnectionSingle.getConnection().prepareStatement(loginQuery);
            loginStmt.setString(1,username);
            loginStmt.setString(2,password);
            ResultSet rs = loginStmt.executeQuery();
            int size =0;
            int userId = -1;
            UserType userType = UserType.Company;
            String mail = "";
            String name = "";
            String information = "";

            while (rs.next()){
                userId = rs.getInt("user_id");
                int userTypeInt = rs.getInt("usertype");
                userType = UserType.values()[userTypeInt];
                mail = rs.getString("mail");
                name = rs.getString("name");
                information = rs.getString("information");
                //Byte[] foto; // şimdilik dursun
                size++;
            }

            if(size!=1){
                return null;
            }

            String dataForUserType = "SELECT * from "+userType+" WHERE user_id = ?";
            PreparedStatement getUserDataStmt = ConnectionSingle.getConnection().prepareStatement(dataForUserType);
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


    public LoginResponse loginWithBasicResponse(String username, String password)
    {
        LoginResponse user = null;

        try {
            String loginQuery = "SELECT * from users WHERE username = ? and password = ?";
            PreparedStatement loginStmt = ConnectionSingle.getConnection().prepareStatement(loginQuery);
            loginStmt.setString(1,username);
            loginStmt.setString(2,password);
            ResultSet rs = loginStmt.executeQuery();
            int size =0;
            int userId = -1;
            UserType userType = UserType.Company;
            String mail = "";
            String name = "";
            String information = "";

            while (rs.next()){
                userId = rs.getInt("user_id");
                int userTypeInt = rs.getInt("usertype");
                userType = UserType.values()[userTypeInt];
                mail = rs.getString("mail");
                name = rs.getString("name");
                information = rs.getString("information");
                //Byte[] foto; // şimdilik dursun
                size++;
            }

            if(size!=1){
                return null;
            }

            String dataForUserType = "SELECT * from "+userType+" WHERE user_id = ?";
            PreparedStatement getUserDataStmt = ConnectionSingle.getConnection().prepareStatement(dataForUserType);
            getUserDataStmt.setInt(1,userId);
            ResultSet rs2 = getUserDataStmt.executeQuery();
            rs2.next();

            switch (userType){
                case Admin:
                    user = new LoginResponse(username,userId,userType.ordinal());
                    break;
                case Coder:
                    int rating = rs2.getInt("rating");
                    int points = rs2.getInt("points");
                    String position = rs2.getString("position");
                    String place = rs2.getString("place");
                    int birthYear = rs2.getInt("birth_year");
                    user = new LoginResponse(username,userId,userType.ordinal());
                    break;
                case Company:
                    String location = rs2.getString("location");
                    String webPageLink = rs2.getString("web_page_link");
                    user = new LoginResponse(username,userId,userType.ordinal());
                    break;
                case Editor:
                    String positionE = rs2.getString("position");
                    String placeE = rs2.getString("place");
                    user = new LoginResponse(username,userId,userType.ordinal());
                    break;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
// username userid user type

}
