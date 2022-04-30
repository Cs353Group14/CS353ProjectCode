package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
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
           // printResultSet(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;
    }

    // silincek
    private void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rs.getString(i));
            }
            System.out.println("");
        }
    }
}
