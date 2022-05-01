package cs353.group14.repositories;

import cs353.group14.Attend;
import cs353.group14.CodingChallenge;
import cs353.group14.Interview;
import cs353.group14.Notification;
import cs353.group14.db.ConnectionSingle;
import org.springframework.stereotype.Repository;

import java.sql.*;

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
            String updateAttend = "UPDATE attend SET interview_result = ? where interview_id = ?";
            PreparedStatement updateAttendStmt = ConnectionSingle.getConnection().prepareStatement(updateAttend);
            updateAttendStmt.setString(1,result);
            updateAttendStmt.setInt(2,interviewId);
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


}
