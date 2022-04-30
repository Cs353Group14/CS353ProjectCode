package cs353.group14.services;

import cs353.group14.*;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.repositories.UserRepository;
import cs353.group14.responses.LoginResponse;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private int register(User user ){

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


    public void registerAdmin(Admin admin) {
        int userId = register(admin);

        if(userId == -1){
            System.out.println("error");
            return;
        }

        userRepository.insertAdmin(userId);

    }

    public User login( String username, String password) {
        return userRepository.login(username,password);
    }

    public LoginResponse loginWithBasicResponse ( String username, String password) {
        return userRepository.loginWithBasicResponse(username,password);
    }

    public void giveReferCoder(int userId, int referredId, String referReason) {
        userRepository.giveReferCoder(userId, referredId, referReason);
    }

    public void askReferCoder(int userId, int referredId) {
        userRepository.askReferCoder(userId, referredId);
    }

    public void answerReferCoder(int userId, int referredId, int answer,String referReason ){
        userRepository.answerReferCoder(userId, referredId,answer, referReason );
    }

    public void giveReferEditor(int userId, int coderId, String suggestReason) {
        userRepository.giveReferEditor(userId, coderId, suggestReason);
    }

    public void askReferEditor(int userId, int coderId) {
        userRepository.askReferEditor(userId, coderId);
    }

    public void answerReferEditor(int userId, int coderId, int answer,String suggestReason ){
        userRepository.answerReferEditor(userId, coderId, answer, suggestReason);
    }

    public List<Integer> listReferCoder(int userId){
        return userRepository.listReferCoder(userId);
    }

    public List<Integer> listReferEditor(int userId){
        return userRepository.listReferEditor(userId);
    }
}
