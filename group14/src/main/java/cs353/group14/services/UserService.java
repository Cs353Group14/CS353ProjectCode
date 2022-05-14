package cs353.group14.services;

import cs353.group14.*;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.repositories.UserRepository;
import cs353.group14.responses.*;
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

    public MessageResponse registerCoder(Coder coder) {

        int userId = register(coder);

        if(userId == -1){
            System.out.println("error");
        return new MessageResponse(MessageType.ERROR, "Username or mail already used");
        }

        return userRepository.insertCoder(userId, coder);

    }

    public MessageResponse registerEditor(Editor editor) {
        int userId = register(editor);

        if(userId == -1){
            System.out.println("error");
            return new MessageResponse(MessageType.ERROR, "Username or mail already used");
        }
        return userRepository.insertEditor(userId, editor);

    }

    public MessageResponse registerCompany(Company company) {
        int userId = register(company);

        if(userId == -1){
            System.out.println("error");
            return new MessageResponse(MessageType.ERROR, "Username or mail already used");
        }
        return userRepository.insertCompany(userId,company);

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

    public List<UserCoderResponse> getCoders() {
        return userRepository.getCoders();
    }

    public List<UserCoderResponse> getCodersWithFilter( String filter)
    {
        return userRepository.getCodersWithFilter(filter);
    }

    public UserCoderResponse getCoderProfile( int user_id)
    {
        return userRepository.getCoderProfile(user_id);
    }

    public LoginResponse loginWithBasicResponse ( String username, String password) {
        return userRepository.loginWithBasicResponse(username,password);
    }

    public MessageResponse giveReferCoder(int userId, int referredId, String referReason) {
        return userRepository.giveReferCoder(userId, referredId, referReason);
    }

    public MessageResponse askReferCoder(int userId, int referredId) {
        return userRepository.askReferCoder(userId, referredId);
    }

    public MessageResponse answerReferCoder(int userId, int referredId, int answer,String referReason ){
        return userRepository.answerReferCoder(userId, referredId,answer, referReason );
    }

    public MessageResponse giveReferEditor(int userId, int coderId, String suggestReason) {
        return userRepository.giveReferEditor(userId, coderId, suggestReason);
    }

    public MessageResponse askReferEditor(int userId, int coderId) {
        return userRepository.askReferEditor(userId, coderId);
    }

    public MessageResponse answerReferEditor(int userId, int coderId, int answer,String suggestReason ){
        return userRepository.answerReferEditor(userId, coderId, answer, suggestReason);
    }

    public List<Integer> listReferCoder(int userId){
        return userRepository.listReferCoder(userId);
    }

    public List<Integer> listReferEditor(int userId){
        return userRepository.listReferEditor(userId);
    }

    public List<Submission> listOldAttempts(int userId,int challengeId){
        return userRepository.listOldAttempts(userId, challengeId);
    }

    public UserNameandNameResponse getUserNameandName(String username)
    {
        return userRepository.getUserNameandName(username);
    }


    public IdUserNameandNameResponse getIdUserNameandName(String username)
    {
        return userRepository.getIdUserNameandName(username);
    }


    public List<ContestAndOrderPoint> getContestsAndOrdersOfUser(int userId)
    {
        return userRepository.getContestsAndOrdersOfUser( userId);
    }
}
