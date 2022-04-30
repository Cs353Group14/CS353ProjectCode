package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.UserType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.CodingChallengeQueryResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CodingChallengeRepository {

    public void createCodingChallenge(int editorId, CodingChallenge codingChallenge){

        try {
            int codingChallengeId = insertCodingChallengeTable(codingChallenge);
            insertCreatesTable(editorId,codingChallengeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertCreatesTable(int editorId, int codingChallengeId)throws SQLException  {
        String insertCreatesChallenge = "Insert INTO creates(user_id,challenge_id) VALUES(?,?)";
        PreparedStatement insertCreatesPrepared= ConnectionSingle.getConnection().prepareStatement(insertCreatesChallenge);
        insertCreatesPrepared.setInt(1,editorId);
        insertCreatesPrepared.setInt(2,codingChallengeId);

        insertCreatesPrepared.executeUpdate();

    }


    public int insertCodingChallengeTable(CodingChallenge codingChallenge)throws SQLException {

        String insertCodingChallenge = "Insert INTO coding_challenge(question,points,difficulty,solved_number,attempt_number,title,solution,publicity ) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement insertCodingPrepared= ConnectionSingle.getConnection().prepareStatement(insertCodingChallenge, Statement.RETURN_GENERATED_KEYS);
        insertCodingPrepared.setString(1,codingChallenge.getQuestion());
        insertCodingPrepared.setInt(2,codingChallenge.getPoints());
        insertCodingPrepared.setString(3,codingChallenge.getDifficulty());
        insertCodingPrepared.setInt(4,0);
        insertCodingPrepared.setInt(5,0);
        insertCodingPrepared.setString(6,codingChallenge.getTitle());
        insertCodingPrepared.setString(7,codingChallenge.getSolution());
        insertCodingPrepared.setInt(8,codingChallenge.getPublicity());


        insertCodingPrepared.executeUpdate();
        ResultSet keys = insertCodingPrepared.getGeneratedKeys();

        keys.next();

        return keys.getInt(1);
    }

    public List<CodingChallengeQueryResponse> getAllPublicChallenges()  {

        List<CodingChallengeQueryResponse> result = new ArrayList<>();

        try {
            String getAllPublicChallengesSql = "Select * From coding_challenge where publicity = 1";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getAllPublicChallengesSql);
            ResultSet rs = insertCodingPrepared.executeQuery();
            while (rs.next()){
                int challenge_id = rs.getInt("challenge_id");
                int points = rs.getInt("points");
                String difficulty = rs.getString("difficulty");
                int solved_number = rs.getInt("solved_number");
                int attempt_number = rs.getInt("attempt_number");
                String title = rs.getString("title");

                CodingChallengeQueryResponse ccqr = new CodingChallengeQueryResponse(challenge_id, title, difficulty, points, solved_number, attempt_number);

                result.add(ccqr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;
    }

    public CodingChallenge getCodingChallenge(int challengeId){

        int challenge_id = -1;
        String question = "";
        int points = -1;
        String difficulty = "";
        int solved_number = -1;
        int attempt_number = -1;
        String title = "";
        String solution = "";
        int publicity = -1;

        try {
            String getChallengeSql = "Select * From coding_challenge where challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
            insertCodingPrepared.setInt(1,challengeId);
            ResultSet rs = insertCodingPrepared.executeQuery();

            int size = 0;

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

                size++;
            }

            if(size!=1){
                return null;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new CodingChallenge (challenge_id, question, points, difficulty,  solved_number, attempt_number, title, solution, publicity);

    }
}
