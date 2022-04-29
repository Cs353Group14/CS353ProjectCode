package cs353.group14.services;

import cs353.group14.Coder;
import cs353.group14.Company;
import cs353.group14.Editor;
import cs353.group14.User;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int register(User user ){

        try {
            if( userRepository.checkUserExist( user.username, user.mail)){
                System.out.println("username or mail already used");
            }else{
                userRepository.insertUserTable(user);
                return userRepository.getUserId(user.username);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void registerCoder(Coder coder) {

        int userId = register(coder);

        String sql = "Insert INTO coder(user_id,rating,points,position,place,birth_year) VALUES(?,?,?,?,?,?)";
        PreparedStatement insertPrepared= null;
        try {
            insertPrepared = ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);
            insertPrepared.setInt(2,0);
            insertPrepared.setInt(3,0);
            insertPrepared.setString(4,coder.position);
            insertPrepared.setString(5,coder.place);
            insertPrepared.setInt(6, coder.birthYear );

            insertPrepared.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void insertUserWithType(User user) throws SQLException {

        PreparedStatement insertPrepared = null;
        String insertUserWithType;
        switch (user.userType){

            case Admin:
                insertUserWithType = "Insert INTO admin(user_id) VALUES(?)";
                insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
                insertPrepared.setInt(1,user.userId);
                break;

            case Coder:
                insertUserWithType = "Insert INTO coder(user_id,rating,points,position,place,birth_year) VALUES(?,?,?,?,?,?)";
                insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
                insertPrepared.setInt(1,user.userId);
                insertPrepared.setInt(2,0);
                insertPrepared.setInt(3,0);
                insertPrepared.setString(4,((Coder) user).position);
                insertPrepared.setString(5,((Coder) user).place);
                insertPrepared.setInt(6, ((Coder) user).birthYear );
                break;

            case Editor:
                insertUserWithType = "Insert INTO editor(user_id,position,place) VALUES(?,?,?)";
                insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
                insertPrepared.setInt(1,user.userId);
                insertPrepared.setString(2,((Editor) user).position);
                insertPrepared.setString(3,((Editor) user).place);
                break;
            case Company:
                insertUserWithType = "Insert INTO company(user_id,location,web_page_link) VALUES(?,?,?)";
                insertPrepared= ConnectionSingle.getConnection().prepareStatement(insertUserWithType);
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
}
