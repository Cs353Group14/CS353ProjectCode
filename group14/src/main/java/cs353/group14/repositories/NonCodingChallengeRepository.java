package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.UserType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.CodingChallengeQueryResponse;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NonCodingChallengeRepository {

    public void createNonCodingChallenge(int editorId, NonCodingChallenge noncodingChallenge){

        try {
            int codingChallengeId = insertNonCodingChallengeTable(noncodingChallenge);
          //  insertCreatesTable(editorId,codingChallengeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertCreatesTable(int editorId, int noncodingChallengeId)throws SQLException  {
        String insertCreatesChallenge = "Insert INTO creates(user_id,challenge_id) VALUES(?,?)";
        PreparedStatement insertCreatesPrepared= ConnectionSingle.getConnection().prepareStatement(insertCreatesChallenge);
        insertCreatesPrepared.setInt(1,editorId);
        insertCreatesPrepared.setInt(2,noncodingChallengeId);

        insertCreatesPrepared.executeUpdate();
    }


    public int insertNonCodingChallengeTable(NonCodingChallenge noncodingChallenge)throws SQLException {

        String insertCodingChallenge = "Insert INTO non_coding_challenge(question,difficulty,title,publicity ) " +
                "VALUES(?,?,?,?,?)";
        PreparedStatement insertCodingPrepared= ConnectionSingle.getConnection().prepareStatement(insertCodingChallenge, Statement.RETURN_GENERATED_KEYS);
        insertCodingPrepared.setString(1,noncodingChallenge.getQuestion());
        insertCodingPrepared.setString(2,noncodingChallenge.getDifficulty());
        insertCodingPrepared.setString(3,noncodingChallenge.getTitle());
        insertCodingPrepared.setInt(4,noncodingChallenge.getPublicity());


        insertCodingPrepared.executeUpdate();
        ResultSet keys = insertCodingPrepared.getGeneratedKeys();

        keys.next();

        return keys.getInt(1);
    }


    public List<NonCodingChallengeQueryResponse> getAllPublicNonCodingChallengesWithFiltered(List <String> input )  {

        List<NonCodingChallengeQueryResponse> result = new ArrayList<>();
        String getAllPublicChallengesSql = "Select * From non_coding_challenge where publicity = 1";
        int i = 0;
        if ( input.size() > 0 ) {
            getAllPublicChallengesSql += "AND non_challenge_id IN";
            while (i < input.size() - 1) {
                getAllPublicChallengesSql += "(SELECT challenge_id from coding_challenge_categories WHERE category =  ? " +
                        " AND challenge_id IN";

                i++;
            }

            getAllPublicChallengesSql += "(SELECT non_challenge_id from non_coding_challenge_categories WHERE category = ?)";
            i = 0;
            while (i < input.size() - 1) {
                getAllPublicChallengesSql += ")";
                i++;
            }
        }

        try {

            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getAllPublicChallengesSql);
            int j = 0;
            while ( j < input.size())
            {
                insertCodingPrepared.setString(j+1, input.get(j));
                j++;
            }
            ResultSet rs = insertCodingPrepared.executeQuery();


            while (rs.next()){

                String difficulty = rs.getString("difficulty");
                String title = rs.getString("title");

                NonCodingChallengeQueryResponse nccqr = new NonCodingChallengeQueryResponse( title, difficulty);

                result.add(nccqr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;
    }


    public List<NonCodingChallengeQueryResponse> getAllNonCodingChallenges( )
    {
        List<NonCodingChallengeQueryResponse> result = new ArrayList<>();

        try {
            String getAllPublicChallengesSql = "Select * From non_coding_challenge where publicity = 1";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getAllPublicChallengesSql);
            ResultSet rs = insertCodingPrepared.executeQuery();
            while (rs.next()){

                String difficulty = rs.getString("difficulty");

                String title = rs.getString("title");

                NonCodingChallengeQueryResponse nccqr = new NonCodingChallengeQueryResponse(title, difficulty);

                result.add(nccqr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;
    }

}
