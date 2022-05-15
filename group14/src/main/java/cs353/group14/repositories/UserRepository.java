package cs353.group14.repositories;


import cs353.group14.*;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.requests.LoginRequest;
import cs353.group14.responses.*;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public List<UserCoderResponse> getCoders() {
        List<UserCoderResponse> result= new ArrayList<>();
        String getUserId = "SELECT * from usercodersview order by rating DESC";
        int size =0;
        int userId = -1;
        String username = "";
        String mail = "";
        String name = "";
        String profile_photo = "";
        String information = "";
        int rating = -1;
        String position = "";
        int points = -1;
        String place = "";
        int birth_year = -1;
        try{
            PreparedStatement getUserIdPrepared= ConnectionSingle.getConnection().prepareStatement(getUserId);
            ResultSet rs = getUserIdPrepared.executeQuery();
            while ( rs.next()) {
                userId = rs.getInt("user_id");
                username = rs.getString("username");
                mail = rs.getString("mail");
                name = rs.getString("name");
                profile_photo = rs.getString("profile_photo");
                information = rs.getString("information");
                rating = rs.getInt("rating");
                position = rs.getString("position");
                points = rs.getInt("points");
                place = rs.getString("place");
                birth_year = rs.getInt("birth_year");

                UserCoderResponse ucr = new UserCoderResponse(rating, points, position, place, birth_year, userId
                        , username, mail, name, information, profile_photo);
                result.add(ucr);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    public UserCoderResponse getCoderProfile( int user_id) {

        String getUserId = "SELECT * from usercodersview where user_id = ?";
        int size =0;
        int userId = -1;
        String username = "";
        String mail = "";
        String name = "";
        String profile_photo = "";
        String information = "";
        int rating = -1;
        String position = "";
        int points = -1;
        String place = "";
        int birth_year = -1;
        try{

            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(getUserId);
            preparedStatement.setInt(1,user_id);
            ResultSet rs = preparedStatement.executeQuery();
            while ( rs.next()) {
                userId = rs.getInt("user_id");
                username = rs.getString("username");
                mail = rs.getString("mail");
                name = rs.getString("name");
                profile_photo = rs.getString("profile_photo");
                information = rs.getString("information");
                rating = rs.getInt("rating");
                position = rs.getString("position");
                points = rs.getInt("points");
                place = rs.getString("place");
                birth_year = rs.getInt("birth_year");

                UserCoderResponse ucr = new UserCoderResponse(rating, points, position, place, birth_year, userId
                        , username, mail, name, information, profile_photo);
                return ucr;
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public List<UserCoderResponse> getCodersWithFilter( String filter) {
        List<UserCoderResponse> result= new ArrayList<>();
        String getUserId = "SELECT * from usercodersview  WHERE username LIKE ? order by rating DESC";
        int size =0;
        int userId = -1;
        String username = "";
        String mail = "";
        String name = "";
        String profile_photo = "";
        String information = "";
        int rating = -1;
        String position = "";
        int points = -1;
        String place = "";
        int birth_year = -1;
        try{
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(getUserId);
            preparedStatement.setString( 1,"%"+filter+"%");
            ResultSet rs = preparedStatement.executeQuery();

            while ( rs.next()) {
                userId = rs.getInt("user_id");
                username = rs.getString("username");
                mail = rs.getString("mail");
                name = rs.getString("name");
                profile_photo = rs.getString("profile_photo");
                information = rs.getString("information");
                rating = rs.getInt("rating");
                position = rs.getString("position");
                points = rs.getInt("points");
                place = rs.getString("place");
                birth_year = rs.getInt("birth_year");

                UserCoderResponse ucr = new UserCoderResponse(rating, points, position, place, birth_year, userId
                        , username, mail, name, information, profile_photo);
                result.add(ucr);
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return result;
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


    public UserNameandNameResponse getUserNameandName( String username)
    {
        String getUserId = "SELECT * from users WHERE username = ?";
        String name ="";
        try{
            PreparedStatement getUserIdPrepared= ConnectionSingle.getConnection().prepareStatement(getUserId);
            getUserIdPrepared.setString(1,username);
            ResultSet rs2 = getUserIdPrepared.executeQuery();
            rs2.next();

            name = rs2.getString("name");
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return new UserNameandNameResponse(username,name);
    }


    public IdUserNameandNameResponse getIdUserNameandName(String username)
    {
        String getUserId = "SELECT * from users WHERE username = ?";
        String name ="";
        int id = -1;
        try{
            PreparedStatement getUserIdPrepared= ConnectionSingle.getConnection().prepareStatement(getUserId);
            getUserIdPrepared.setString(1,username);
            ResultSet rs2 = getUserIdPrepared.executeQuery();
            rs2.next();

            id = rs2.getInt("user_id");
            name = rs2.getString("name");
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return new IdUserNameandNameResponse(id,username,name);
    }


    public MessageResponse insertCoder(int userId, Coder coder)  {
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

            return new MessageResponse(MessageType.SUCCESS, "Register is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }

    public MessageResponse insertEditor(int userId, Editor editor)  {
        String sql = "Insert INTO editor(user_id,position,place) VALUES(?,?,?)";

        try {
            PreparedStatement insertPrepared= ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);
            insertPrepared.setString(2,editor.getPosition());
            insertPrepared.setString(3, editor.getPlace());

            insertPrepared.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Register is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
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

    public MessageResponse insertCompany(int userId, Company company)  {
        String sql = "Insert INTO company(user_id,location,web_page_link) VALUES(?,?,?)";
        try {
            PreparedStatement insertPrepared= ConnectionSingle.getConnection().prepareStatement(sql);
            insertPrepared.setInt(1,userId);
            insertPrepared.setString(2, company.getLocation());
            insertPrepared.setString(3, company.getWebPageLink());

            insertPrepared.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Register is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
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

    public MessageResponse giveReferCoder(int userId, int referredId, String referReason) {

        try {
            String insertRefer = "insert into refer ( user_id, referred_id, refer_reason, accepted) VALUES(?, ?, ?, ?)";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(insertRefer);
            insertReferStmt.setInt(1,userId);
            insertReferStmt.setInt(2,referredId);
            insertReferStmt.setString(3,referReason);
            insertReferStmt.setInt(4,1);
            insertReferStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Insertion is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }

    public MessageResponse askReferCoder(int userId, int referredId) {

        try {
            String insertRefer = "insert into refer ( user_id, referred_id, refer_reason, accepted) VALUES(?, ?, ?, ?)";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(insertRefer);
            insertReferStmt.setInt(1,userId);
            insertReferStmt.setInt(2,referredId);
            insertReferStmt.setString(3,"");
            insertReferStmt.setInt(4,0);
            insertReferStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Insertion is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }

    public MessageResponse answerReferCoder(int userId, int referredId, int answer,String referReason ) {

        try {
            String insertRefer = "UPDATE refer SET refer_reason = ? , accepted = ?  where user_id = ? and referred_id = ?";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(insertRefer);
            insertReferStmt.setString(1,referReason);
            insertReferStmt.setInt(2,answer);
            insertReferStmt.setInt(3,userId);
            insertReferStmt.setInt(4,referredId);
            insertReferStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Update is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }




    public MessageResponse giveReferEditor(int userId, int coderId, String suggestReason) {

        try {
            String insertRefer = "insert into suggest ( user_id, coder_id, suggest_reason, accepted) VALUES(?, ?, ?, ?)";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(insertRefer);
            insertReferStmt.setInt(1,userId);
            insertReferStmt.setInt(2,coderId);
            insertReferStmt.setString(3,suggestReason);
            insertReferStmt.setInt(4,1);
            insertReferStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Insertion is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }

    public MessageResponse askReferEditor(int userId, int coderId) {

        try {
            String insertRefer = "insert into suggest ( user_id, coder_id, suggest_reason, accepted) VALUES(?, ?, ?, ?)";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(insertRefer);
            insertReferStmt.setInt(1,userId);
            insertReferStmt.setInt(2,coderId);
            insertReferStmt.setString(3,"");
            insertReferStmt.setInt(4,0);
            insertReferStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Insertion is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }


    public MessageResponse answerReferEditor(int userId, int coderId, int answer,String suggestReason ) {

        try {
            String insertRefer = "UPDATE suggest SET suggest_reason = ? , accepted = ?  where user_id = ? and coder_id = ?";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(insertRefer);
            insertReferStmt.setString(1,suggestReason);
            insertReferStmt.setInt(2,answer);
            insertReferStmt.setInt(3,userId);
            insertReferStmt.setInt(4,coderId);
            insertReferStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Update is successful");

        } catch (SQLException exception) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + exception.getMessage());
        }
    }

    public List<ReferCoder> listReferCoder(int userId){

        List<ReferCoder> result = new ArrayList<>();

        try {
        String listRefer = "select * from refer,users,coder where accepted = 1 and refer.user_id = ? and refer.referred_id=users.user_id and coder.user_id = refer.referred_id ";
        PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(listRefer);
        insertReferStmt.setInt(1,userId);

        ResultSet rs = insertReferStmt.executeQuery();
            while (rs.next()){
                int referred_id= rs.getInt("referred_id");
                String username = rs.getString("username");
                String position = rs.getString("position");
                String refer_reason= rs.getString("refer_reason");
                ReferCoder referCoder = new ReferCoder(referred_id,username,position,refer_reason );
                result.add(referCoder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public List<Integer> listReferEditor(int userId){

        List<Integer> result = new ArrayList<>();

        try {
            String listRefer = "select from suggest where accepted = 0 and user_id = ?";
            PreparedStatement insertReferStmt = ConnectionSingle.getConnection().prepareStatement(listRefer);
            insertReferStmt.setInt(1,userId);

            ResultSet rs = insertReferStmt.executeQuery();

            while (rs.next()){
                int coder_id= rs.getInt("coder_id");
                result.add(coder_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public List<Submission> listOldAttempts(int userId, int challengeId){

        List<Submission> result = new ArrayList<>();

        try {
        String listAttempts = "SELECT * from submit NATURAL JOIN submission where challenge_id = ? and user_id = ? ";
        PreparedStatement listAttemptsStmt = ConnectionSingle.getConnection().prepareStatement(listAttempts);
        listAttemptsStmt.setInt(1,challengeId);
        listAttemptsStmt.setInt(2,userId);

        ResultSet rs = listAttemptsStmt.executeQuery();

        while (rs.next()){

            int submission_id = rs.getInt("submission_id");
            String answer = rs.getString("answer");
            int passResult = rs.getInt("pass_result");
            int failResult = rs.getInt("fail_result");
            String programmingLanguage = rs.getString("programming_language");
            Timestamp submissionTime = rs.getTimestamp("submission_time");

            Submission submission = new Submission(submission_id,answer,passResult,failResult,programmingLanguage,submissionTime);
            result.add(submission);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;


    }


    public List<ContestAndOrderPoint> getContestsAndOrdersOfUser(int userId){

        List<ContestAndOrderPoint> result = new ArrayList<>();

        try {
           String forContestAndPoint1 = "SELECT * FROM " +
                    "( SELECT CT.contest_id, CT.start_time, CT.description,CT.title,CT.difficulty,CT.duration,CT.deadline, P.points" +
                    " from participate P,coder C,contest CT" +
                    " where  CT.contest_id = P.contest_id and P.user_id = C.user_id and C.user_id = ? ) s1 " +
                    "JOIN" +
                    "( SELECT P2.contest_id, 1+count(*) as orders from participate P2 where P2.points > " +
                    "( SELECT points from participate P3 where P3.user_id = ? ) group by P2.contest_id ) s2 " +
                    "ON s1.contest_id = s2.contest_id";


           String forContestAndPoint = "SELECT * FROM " +
                   "( SELECT CT.contest_id, CT.start_time, CT.description, CT.title, CT.difficulty, CT.duration, CT.deadline, P.points" +
                   " from participate P,coder C,contest CT" +
                   " where  CT.contest_id = P.contest_id and P.user_id = C.user_id and C.user_id = ? ) s1" +
                   " JOIN" +
                   " ( SELECT P2.contest_id, 1+( select count(*) from participate where contest_id = P2.contest_id) - count(*) as orders " +
                   " from participate P2 where P2.points  <= ( SELECT points from participate P3 where P3.user_id = ? ) group by P2.contest_id ) " +
                   " s2 ON s1.contest_id = s2.contest_id";

            PreparedStatement listAttemptsStmt = ConnectionSingle.getConnection().prepareStatement(forContestAndPoint);
            listAttemptsStmt.setInt(1,userId);
            listAttemptsStmt.setInt(2,userId);

            Timestamp start_time = null;
            String description = "";
            String title = "";
            int difficulty = 0;
            int duration = 0;
            Timestamp deadline = null;
            int order = -1;
            int points = -1;
            int contestid = -1;

            ResultSet rs = listAttemptsStmt.executeQuery();
            while (rs.next()){

                start_time = ( rs.getTimestamp("start_time"));
                description = ( rs.getString("description"));
                title = ( rs.getString("title"));
                difficulty = ( rs.getInt("difficulty"));
                duration = ( rs.getInt("duration"));
                deadline = ( rs.getTimestamp("deadline"));
                points = ( rs.getInt("points"));
                order = ( rs.getInt("orders"));
                contestid = ( rs.getInt("contest_id"));

                Contest c = new Contest(contestid,start_time,description,title,difficulty,duration,deadline);
                ContestAndOrderPoint  contestAndOrderPoint = new ContestAndOrderPoint( c,order,points);
                result.add(contestAndOrderPoint);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public UserCompanyResponse getCompanyProfile(int user_id) {
        String getUserId = "SELECT * from usercompanyview where user_id = ?";
        int size =0;
        int userId = -1;
        String username = "";
        String mail = "";
        String name = "";
        String profile_photo = "";
        String information = "";
        int rating = -1;
        String position = "";
        int points = -1;
        String place = "";
        int birth_year = -1;
         String location = "";
         String webpagelink = "";
        try{

            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(getUserId);
            preparedStatement.setInt(1,user_id);
            ResultSet rs = preparedStatement.executeQuery();
            while ( rs.next()) {
                userId = rs.getInt("user_id");
                username = rs.getString("username");
                mail = rs.getString("mail");
                name = rs.getString("name");
                profile_photo = rs.getString("profile_photo");
                information = rs.getString("information");
                location = rs.getString("location");
                webpagelink = rs.getString("web_page_link");

                UserCompanyResponse ucr = new UserCompanyResponse(userId
                        , username, mail, name, information, profile_photo,location,webpagelink);
                return ucr;
            }
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
