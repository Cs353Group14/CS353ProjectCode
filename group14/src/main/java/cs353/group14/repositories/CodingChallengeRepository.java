package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.db.ConnectionSingle;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class CodingChallengeRepository {

    public static void createCodingChallenge(int editorId, CodingChallenge codingChallenge){

        try {
            int codingChallengeId = insertCodingChallengeTable(codingChallenge);
            insertCreatesTable(editorId,codingChallengeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertCreatesTable(int editorId, int codingChallengeId)throws SQLException  {
        String insertCreatesChallenge = "Insert INTO creates(user_id,challenge_id) VALUES(?,?)";
        PreparedStatement insertCreatesPrepared= ConnectionSingle.getConnection().prepareStatement(insertCreatesChallenge);
        insertCreatesPrepared.setInt(1,editorId);
        insertCreatesPrepared.setInt(2,codingChallengeId);

        insertCreatesPrepared.executeUpdate();

    }


    public static int insertCodingChallengeTable(CodingChallenge codingChallenge)throws SQLException {

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
}
