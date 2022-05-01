package cs353.group14.repositories;

import cs353.group14.Attend;
import cs353.group14.CodingChallenge;
import cs353.group14.Interview;
import cs353.group14.db.ConnectionSingle;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void changeAttendResult(int interviewId, String result){
        try {
            String updateAttend = "UPDATE attend SET interview_result = ? where interview_id = ?";
            PreparedStatement updateAttendStmt = ConnectionSingle.getConnection().prepareStatement(updateAttend);
            updateAttendStmt.setString(1,result);
            updateAttendStmt.setInt(2,interviewId);
            updateAttendStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
