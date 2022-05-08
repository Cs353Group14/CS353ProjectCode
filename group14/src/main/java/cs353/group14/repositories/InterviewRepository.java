package cs353.group14.repositories;

import cs353.group14.*;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.InterviewResponse;
import cs353.group14.responses.UserNameAndInterviewResultResponse;
import cs353.group14.responses.UserNameandNameResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InterviewRepository {
    public int createInterview(Interview interview){
        try {
            String insertCodingChallenge = "INSERT INTO interview(user_id,duration,position) VALUES ( ?,?,?);";
            PreparedStatement insertCodingPrepared= ConnectionSingle.getConnection().prepareStatement(insertCodingChallenge, Statement.RETURN_GENERATED_KEYS);
            insertCodingPrepared.setInt(1,interview.getUser_id());
            insertCodingPrepared.setInt(2,interview.getDuration());
            insertCodingPrepared.setString(3,interview.getPosition());


            insertCodingPrepared.executeUpdate();
            ResultSet keys = insertCodingPrepared.getGeneratedKeys();

            keys.next();

            return keys.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public Interview getInterview( int interviewId)
    {

         int user_id = 0;
         int duration = 0;
         String position = "";
        try{
            String query = "Select * from interview  where interview_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,interviewId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                user_id = rs.getInt("user_id");
                duration = rs.getInt("duration");
                position = rs.getString("position");


            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return  new Interview(user_id,interviewId,duration, position);
    }

    public Company getCompanyofInterview( int interview_id)
    {
        int userId = -1; String username =""; String mail = ""; String password =""; UserType userType =UserType.Company;
            String name =""; String information =""; String foto =""; String location = ""; String webPageLink ="";
        try{
            String query = "Select * from interview I, company C, users U  " +
                    "where I.interview_id = ? and I.user_id = C.user_id and U.user_id = C.user_id ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,interview_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                userId = rs.getInt("user_id");
                username = rs.getString("username");
                mail = rs.getString("mail");
                password = rs.getString("password");
               // userType = rs.getInt("usetype");
                name = rs.getString("name");
                information = rs.getString("information");
                foto = rs.getString("profile_photo");
                location = rs.getString("location");
                webPageLink = rs.getString("web_page_link");

            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
            return new Company(userId, username, mail , password , userType ,
                    name ,information ,foto ,location ,  webPageLink);
    }

    public void insertAttend(Attend attend){

        try {
            String insertAttendSql = "INSERT INTO attend (interview_id,coder_id,company_id, start_time,end_time,interview_result,invitation_code)" +
                    "VALUES ( ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement insertAttendStmt = ConnectionSingle.getConnection().prepareStatement(insertAttendSql);
            insertAttendStmt.setInt(1,attend.getInterviewId());
            insertAttendStmt.setInt(2,attend.getCoderId());
            insertAttendStmt.setInt(3,attend.getCompanyId());
            insertAttendStmt.setTimestamp(4,attend.getStartTime());
            insertAttendStmt.setTimestamp(5,attend.getEndTime());
            insertAttendStmt.setString(6,"Not determined");
            insertAttendStmt.setString(7,attend.getInvitationCode());
            insertAttendStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void changeAttendResult(int interviewId, String result , int userId){
        try {
            String updateAttend = "UPDATE attend SET interview_result = ? where interview_id = ? and coder_id = ?";
            PreparedStatement updateAttendStmt = ConnectionSingle.getConnection().prepareStatement(updateAttend);
            updateAttendStmt.setString(1,result);
            updateAttendStmt.setInt(2,interviewId);
            updateAttendStmt.setInt(3,userId);
            updateAttendStmt.executeUpdate();

            String nInfo = "You found "+result+ " in one of the interviews check your interviews";

            Notification notification = new Notification(-1,nInfo,new Timestamp(System.currentTimeMillis()),"Interview");

           int n_id =  insertNotification(notification);

           insertNotify(userId,n_id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertNotification(Notification notification){
        try {
            String insertNotificationSql = "INSERT INTO notification( n_info,notif_date,type) VALUES ( ?, ? , ? )";

            PreparedStatement insertNotificationPrepared= ConnectionSingle.getConnection().prepareStatement(insertNotificationSql, Statement.RETURN_GENERATED_KEYS);
            insertNotificationPrepared.setString(1,notification.getNInfo());
            insertNotificationPrepared.setTimestamp(2,notification.getNotifDate());
            insertNotificationPrepared.setString(3,notification.getType());
            insertNotificationPrepared.executeUpdate();

            ResultSet keys = insertNotificationPrepared.getGeneratedKeys();

            keys.next();

            return keys.getInt(1);

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<CodingChallenge> getCodingChallengesOfInterview( int interview_id)
    {
        List<CodingChallenge> result = new ArrayList<>();

        int challenge_id = -1;
        String question = "";
        int points = -1;
        String difficulty = "";
        int solved_number = -1;
        int attempt_number = -1;
        String title = "";
        String solution = "";
        int publicity = -1;
        try{
            String query = "Select * from  includes IC, coding_challenge C  " +
                    "where C.challenge_id = IC.challenge_id and IC.interview_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,interview_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                challenge_id = rs.getInt("challenge_id");
                question = rs.getString("question");
                points = rs.getInt("points");
                difficulty = rs.getString("difficulty");
                solved_number = rs.getInt("solved_number");
                attempt_number = rs.getInt("attempt_number");
                title = rs.getString("title");
                solution = rs.getString("solution");
                publicity = rs.getInt("publicity");

                CodingChallenge cc =  new CodingChallenge (challenge_id, question, points, difficulty,  solved_number, attempt_number, title, solution, publicity);

                result.add(cc);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return  result;
    }



    public List<NonCodingChallenge> getNonCodingChallengesOfInterview(int interview_id)
    {
        List<NonCodingChallenge> result = new ArrayList<>();

        int non_challenge_id = -1;
        String question = "";
        String difficulty = "";
        String title = "";
        int publicity = -1;
        try{
            String query = "Select * from  made_of M, non_coding_challenge N  " +
                    "where M.non_challenge_id = N.non_challenge_id and M.interview_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,interview_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                non_challenge_id = rs.getInt("non_challenge_id");
                question = rs.getString("question");
                difficulty = rs.getString("difficulty");
                title = rs.getString("title");
                publicity = rs.getInt("publicity");

                NonCodingChallenge ncc = new NonCodingChallenge (non_challenge_id, question, difficulty, title, publicity);

                result.add(ncc);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return  result;
    }






    public void insertNotify(int userId, int nId){
        try {
            String insertNotifSql = "INSERT INTO notify(user_id,n_id) VALUES( ?, ?)";
            PreparedStatement insertNotifPrepared= ConnectionSingle.getConnection().prepareStatement(insertNotifSql);
            insertNotifPrepared.setInt(1,userId);
            insertNotifPrepared.setInt(2,nId);

            insertNotifPrepared.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addCodingQuestionToInterview(int interview_id,int challenge_id, int company_id, int time_limit)  {
        try {
            String query = "INSERT INTO includes(challenge_id, interview_id,company_id,time_limit) VALUES (?,?,?,?) ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);

                preparedStatement.setInt(1, challenge_id);
                preparedStatement.setInt(2, interview_id);
                preparedStatement.setInt(3, company_id);
                 preparedStatement.setInt(4, time_limit);

                 preparedStatement.executeUpdate();
            }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public void addNonCodingQuestionToInterview(int interview_id,int non_challenge_id, int company_id)  {
        try {
            String query = "INSERT INTO made_of(non_challenge_id, user_id,company_id) VALUES (?,?,?) ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, non_challenge_id);
            preparedStatement.setInt(2, interview_id);
            preparedStatement.setInt(3, company_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public List<Notification> seeNotifications(int userId){

        List<Notification> result = new ArrayList<>();

        try{
            String query = "Select * from notification N, notify O where N.n_id = O.n_id and user_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int n_id = rs.getInt("n_id");
                String n_info = rs.getString("n_info");
                Timestamp notif_date = rs.getTimestamp("notif_date");
                String type = rs.getString("type");

                Notification notification = new Notification(n_id,n_info,notif_date,type);
                result.add(notification);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return result;


    }





    public List<InterviewResponse> getInterviewsForCoder( int userId)
    {
        List<InterviewResponse> result = new ArrayList<>();


        try{
            String query = "Select * from attend A, interview I,company C,users U where I.interview_id = A.interview_id " +
                    "and C.user_id = I.user_id and A.coder_id = ? and C.user_id = U.user_id";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int duration = rs.getInt("duration");
                int interviewId = rs.getInt("interview_id");
                String companyName = rs.getString("username");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String position = rs.getString("position");
                String interviewResult = rs.getString("interview_result");
                String invitationCode = rs.getString("invitation_code");

                InterviewResponse interviewResponse = new InterviewResponse(companyName,duration,position,interviewId,startTime,endTime,interviewResult,invitationCode);
                result.add(interviewResponse);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;
    }




    public List<Interview> getInterviewsOfCompanyNew( int userId)
    {
        List<Interview> result = new ArrayList<>();


        try{
            String query = "Select * from  interview I  where   I.user_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int duration = rs.getInt("duration");
                int interviewId = rs.getInt("interview_id");


                String position = rs.getString("position");


                Interview interviewResponse = new Interview(userId,interviewId,duration,position);
                result.add(interviewResponse);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;
    }


    public List<UserNameAndInterviewResultResponse> getInterviewsForCompany(int companyId)
    {
        List<UserNameAndInterviewResultResponse> result = new ArrayList<>();

        try{
            String query = "Select * from attend A, interview I, users U where I.interview_id = A.interview_id and A.coder_id = U.user_id and I.user_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,companyId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int duration = rs.getInt("duration");
                int interviewId = rs.getInt("interview_id");
                String companyName = rs.getString("username");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String position = rs.getString("position");
                String interviewResult = rs.getString("interview_result");
                String invitationCode = rs.getString("invitation_code");

                UserNameAndInterviewResultResponse userNameAndInterviewResultResponse = new UserNameAndInterviewResultResponse(companyName,interviewId,duration,position,startTime,endTime,interviewResult,invitationCode);
                result.add(userNameAndInterviewResultResponse);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;

    }


    public List<UserNameandNameResponse> getUsersAttendingToInterview( int interviewId)
    {
        List<UserNameandNameResponse> result = new ArrayList<>();

        try{
            String query = "Select * from attend A, users U where A.interview_id = ? and A.coder_id = U.user_id ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,interviewId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                String userName = rs.getString("username");
                String name = rs.getString("name");

                UserNameandNameResponse userNameandNameResponse = new UserNameandNameResponse(userName,name);
                result.add(userNameandNameResponse);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;

    }


    public List<InterviewResponse> getPastInterviewsForCoder(int coder_id) {

        List<InterviewResponse> result = new ArrayList<>();


        try{
            String query = "Select * from attend A, interview I,company C, users U where I.interview_id = A.interview_id " +
                    "and C.user_id = I.user_id and A.coder_id = ? and A.start_time < CURRENT_TIMESTAMP and C.user_id = U.user_id";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,coder_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int duration = rs.getInt("duration");
                int interviewId = rs.getInt("interview_id");
                String companyName = rs.getString("username");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String position = rs.getString("position");
                String interviewResult = rs.getString("interview_result");
                String invitationCode = rs.getString("invitation_code");

                InterviewResponse interviewResponse = new InterviewResponse(companyName,duration,position,interviewId,startTime,endTime,interviewResult,invitationCode);
                result.add(interviewResponse);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;
    }


    public List<InterviewResponse> getFutureInterviewsForCoder(int coder_id) {

        List<InterviewResponse> result = new ArrayList<>();


        try{
            String query = "Select * from attend A, interview I,company C, users U where I.interview_id = A.interview_id " +
                    "and C.user_id = I.user_id and A.coder_id = ? and A.end_time > CURRENT_TIMESTAMP and C.user_id = U.user_id ";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,coder_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int duration = rs.getInt("duration");
                int interviewId = rs.getInt("interview_id");
                String companyName = rs.getString("username");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String position = rs.getString("position");
                String interviewResult = rs.getString("interview_result");
                String invitationCode = rs.getString("invitation_code");

                InterviewResponse interviewResponse = new InterviewResponse(companyName,duration,position,interviewId,startTime,endTime,interviewResult,invitationCode);
                result.add(interviewResponse);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return result;
    }
}
