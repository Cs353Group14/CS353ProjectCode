package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.NonCodingChallenge;
import cs353.group14.Reply;
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

    public NonCodingChallenge getNonCodingChallenge(int noncodingChallengeId){

        int non_challenge_id = -1;
        String question = "";
        String difficulty = "";
        String title = "";
        int publicity = -1;

        try {
            String getNonChallengeSql = "Select * From non_coding_challenge where non_challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getNonChallengeSql);
            insertCodingPrepared.setInt(1,noncodingChallengeId);
            ResultSet rs = insertCodingPrepared.executeQuery();

            int size = 0;

            while (rs.next()){
                non_challenge_id = rs.getInt("challenge_id");
                question = rs.getString("question");
                difficulty = rs.getString("difficulty");
                title = rs.getString("title");
                publicity = rs.getInt("publicity");

                size++;
            }

            if(size!=1){
                return null;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new NonCodingChallenge (non_challenge_id, question, difficulty, title, publicity);

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

    public void replyQuestion(Reply reply){

        try {
            String insertReplySql = "Insert into reply VALUES( ?, ?, ?, null, ?)";

            PreparedStatement insertStmt= ConnectionSingle.getConnection().prepareStatement(insertReplySql);
            insertStmt.setInt(1,reply.getNonChallengeId());
            insertStmt.setInt(2, reply.getUserId());
            insertStmt.setString(3,reply.getAnswer());
            insertStmt.setTimestamp(4,reply.getReplyTime());

            insertStmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




    }

    public Reply seeReply(int userId, int nonChallengeId) {

        String answer = "";
        String theResult = "";
        Timestamp replyTime = null;


        try {
            String getReplySql = "Select * from reply where non_challenge_id = ? and user_id = ?";
            PreparedStatement getReplyPrepared = ConnectionSingle.getConnection().prepareStatement(getReplySql);
            getReplyPrepared.setInt(1,nonChallengeId);
            getReplyPrepared.setInt(2,userId);

            ResultSet rs = getReplyPrepared.executeQuery();

            int size = 0;

            while (rs.next()){
                answer = rs.getString("answer");
                theResult = rs.getString("the_result");
                replyTime = rs.getTimestamp("reply_time");

                size++;
            }

            if(size!=1){
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Reply(nonChallengeId,userId,answer,theResult,replyTime);
    }
}
