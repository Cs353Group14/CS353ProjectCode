package cs353.group14.repositories;

import cs353.group14.Submission;
import cs353.group14.User;
import cs353.group14.db.ConnectionSingle;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class SubmissionRepository {

    private int insertSubmission(Submission submission) throws SQLException {
        String insertSubmissionSql = "INSERT into submission (answer,pass_result,fail_result,programming_language, submission_time) " +
                "VALUES( ?, ?, ?, ?, ?)";

            PreparedStatement insertStmt= ConnectionSingle.getConnection().prepareStatement(insertSubmissionSql, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1,submission.getAnswer());
            insertStmt.setInt(2,submission.getPass_result());
            insertStmt.setInt(3,submission.getFail_result());
            insertStmt.setString(4,submission.getProgramming_language());
            insertStmt.setTimestamp(5,submission.getSubmission_time());

            insertStmt.executeUpdate();
            ResultSet keys = insertStmt.getGeneratedKeys();

            keys.next();

            return keys.getInt(1);

    }

    private void insertSubmit(int userId,int challengeId,int submissionId) throws SQLException {
        String insertSubmitSql = "INSERT INTO submit(submission_id, challenge_id, user_id)" +
                "VALUES (?, ?, ?)";

        PreparedStatement insertStmt = ConnectionSingle.getConnection().prepareStatement(insertSubmitSql);
        insertStmt.setInt(1,submissionId);
        insertStmt.setInt(2,challengeId);
        insertStmt.setInt(3,userId);
        insertStmt.executeUpdate();

    }

    private void updateCodingChallenge(int challengeId,int submissionId) throws SQLException{
        String updateAttemptSql = "UPDATE coding_challenge SET attempt_number = attempt_number +1 where challenge_id = ?";
        PreparedStatement updateAttemptStmt = ConnectionSingle.getConnection().prepareStatement(updateAttemptSql);
        updateAttemptStmt.setInt(1,challengeId);

        updateAttemptStmt.executeUpdate();


        String updateSolvedNumberSql ="UPDATE coding_challenge SET solved_number = case WHEN EXISTS" +
                "( SELECT * FROM  submission where submission_id = ? and fail_result = 0) " +
                "THEN solved_number + 1 " +
                "else solved_number END " +
                "WHERE challenge_id = ?";

        PreparedStatement updateSolvedStmt = ConnectionSingle.getConnection().prepareStatement(updateSolvedNumberSql);
        updateSolvedStmt.setInt(1,submissionId);
        updateSolvedStmt.setInt(2,challengeId);
        updateSolvedStmt.executeUpdate();

    }

    public void submitQuestion(int userId,int challengeId,Submission submission){

        try {
            int submissionId = insertSubmission(submission);
            submission.setSubmission_id(submissionId);
            insertSubmit(userId,challengeId,submissionId);
            updateCodingChallenge( challengeId, submissionId);

            System.out.println(submission);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
