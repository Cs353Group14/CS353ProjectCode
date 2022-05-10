package cs353.group14.repositories;

import cs353.group14.Submission;
import cs353.group14.User;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
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

    private void updateCodingChallenge(int challengeId,int submissionId,int userId) throws SQLException{
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


        String query ="UPDATE coder SET points = case WHEN EXISTS" +
                "( SELECT * FROM  submission where submission_id = ? and fail_result = 0) AND  NOT EXISTS" +
                "( SELECT ST.challenge_id FROM submission S, submit ST where S.submission_id = ST.submission_id AND " +
                "ST.challenge_id = ? and ST.user_id = ? and S.fail_result = 0 group by ST.challenge_id having count(*) >= 2 )" +
                "THEN points + ( SELECT points from coding_challenge where challenge_id = ?) " +
                "else points END " +
                "where user_id = ?" ;

        PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,submissionId);
        preparedStatement.setInt(2,challengeId);
        preparedStatement.setInt(3,userId);
        preparedStatement.setInt(4,challengeId);
        preparedStatement.setInt(5,userId);
        preparedStatement.executeUpdate();

    }



    public MessageResponse submitQuestion(int userId, int challengeId, Submission submission){

        try {
            int submissionId = insertSubmission(submission);
            submission.setSubmission_id(submissionId);
            insertSubmit(userId,challengeId,submissionId);
            updateCodingChallenge( challengeId, submissionId,userId);
            return new MessageResponse(MessageType.SUCCESS, "Insertion is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }

    }





    private void updateCodingChallengeContest(int challengeId,int submissionId,int userId,int contestId) throws SQLException{
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


        String query ="UPDATE participate SET points = case WHEN EXISTS" +
                "( SELECT * FROM  submission where submission_id = ? and fail_result = 0) AND NOT EXISTS" +
                "( SELECT ST.challenge_id FROM submission S, submit ST where S.submission_id = ST.submission_id AND " +
                " ST.challenge_id = ? and ST.user_id = ? and S.fail_result = 0 group by ST.challenge_id having count(*) >= 2 )" +
                "THEN points + ( SELECT points from coding_challenge where challenge_id = ?) " +
                "else points END " +
                "where user_id = ? and contest_id = ?" ;

        PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
        preparedStatement.setInt(1,submissionId);
        preparedStatement.setInt(2,challengeId);
        preparedStatement.setInt(3,userId);
        preparedStatement.setInt(4,challengeId);
        preparedStatement.setInt(5,userId);
        preparedStatement.setInt(6,contestId);
        preparedStatement.executeUpdate();

        String query2 = "UPDATE coder SET rating = case WHEN EXISTS " +
               "( SELECT * FROM  submission where submission_id = ? and fail_result = 0) AND NOT EXISTS" +
        "( SELECT ST.challenge_id FROM submission S, submit ST where S.submission_id = ST.submission_id AND " +
                " ST.challenge_id = ? and ST.user_id = ? and S.fail_result = 0 group by ST.challenge_id having count(*) >= 2 )"+
                "THEN ( rating * ( SELECT count(contest_id) from participate where user_id = ? ) " +
                "+ (SELECT difficulty from contest where contest_id = ? )" +
                "* (SELECT points from coding_challenge where challenge_id = ?) ) " +
                "/ (1 + (SELECT count(contest_id) from participate where user_id = ?) )" +
                " else rating END"+
                " where user_id = ?";

        PreparedStatement pS = ConnectionSingle.getConnection().prepareStatement(query2);
        pS.setInt(1,submissionId);
        pS.setInt(2,challengeId);
        pS.setInt(3,userId);
        pS.setInt(4,userId);
        pS.setInt(5,contestId);
        pS.setInt(6,challengeId);
        pS.setInt(7,userId);
        pS.setInt(8,userId);
        pS.executeUpdate();
    }

    public MessageResponse submitQuestionToContest(int userId,int challengeId,int contestId,Submission submission){

        try {
            int submissionId = insertSubmission(submission);
            submission.setSubmission_id(submissionId);
            insertSubmit(userId,challengeId,submissionId);
            updateCodingChallengeContest( challengeId, submissionId,userId,contestId);

            return new MessageResponse(MessageType.SUCCESS, "Submit is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }

    }


}
