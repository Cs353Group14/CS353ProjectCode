package cs353.group14.services;

import cs353.group14.*;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int register(User user ){

        if( userRepository.checkUserExist( user.getUsername(), user.getMail())){
            System.out.println("username or mail already used");
            return -1;
        }else{
            userRepository.insertUser(user);
            return userRepository.getUserId(user.getUsername());
        }
    }

    public void registerCoder(Coder coder) {

        int userId = register(coder);

        if(userId == -1){
            System.out.println("error");
        return;
        }

        userRepository.insertCoder(userId, coder);

    }

    public void registerEditor(Editor editor) {
        int userId = register(editor);

        if(userId == -1){
            System.out.println("error");
            return;
        }

        userRepository.insertEditor(userId, editor);

    }

    public void registerCompany(Company company) {
        int userId = register(company);

        if(userId == -1){
            System.out.println("error");
            return;
        }

        userRepository.insertCompany(userId,company);

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

    public void insertUserWithType(User user) throws SQLException {

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


}
