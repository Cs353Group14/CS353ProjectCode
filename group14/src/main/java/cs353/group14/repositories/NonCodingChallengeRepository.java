package cs353.group14.repositories;

import cs353.group14.*;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NonCodingChallengeRepository {

    public int createNonCodingChallenge(int editorId, NonCodingChallenge noncodingChallenge){

        try {
            int codingChallengeId = insertNonCodingChallengeTable(noncodingChallenge);
            insertMakeTable(editorId,codingChallengeId);
            return codingChallengeId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void insertMakeTable(int editorId, int noncodingChallengeId)throws SQLException  {
        String insertCreatesChallenge = "Insert INTO make(user_id,non_challenge_id) VALUES(?,?)";
        PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(insertCreatesChallenge);
        preparedStatement.setInt(1,editorId);
        preparedStatement.setInt(2,noncodingChallengeId);

        preparedStatement.executeUpdate();
    }


    public int insertNonCodingChallengeTable(NonCodingChallenge noncodingChallenge)throws SQLException {

        String insertCodingChallenge = "Insert INTO non_coding_challenge(question,difficulty,title,publicity ) " +
                "VALUES(?,?,?,?)";
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

    public MessageResponse updateDifficultyNonCodingChallenge( int challenge_id, String difficulty)
    {
        try {
            String query = "UPDATE non_coding_challenge SET difficulty = ? where challenge_id = ?";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setString(1,difficulty);
            preparedStatement.setInt(2,challenge_id);
            int result = preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Update is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }

    }


    public MessageResponse addCategoryNonCodingChallenge( int challenge_id, String category)
    {
        try {
            String query = "INSERT INTO non_coding_challenge_categories( non_challenge_id,category) VALUES ( ?,?);";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,challenge_id);
            preparedStatement.setString(2,category);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Insertion is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }
    }




    public MessageResponse removeCategoryNonCodingChallenge( int challenge_id, String category)
    {
        try {
            String query = "DELETE FROM non_coding_challenge_categories where non_challenge_id = ? and category = ?";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,challenge_id);
            preparedStatement.setString(2,category);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Remove is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }
    }






    public List<NonCodingChallengeQueryResponse> getAllPublicNonCodingChallengesWithFiltered(List <String> input )  {

        List<NonCodingChallengeQueryResponse> result = new ArrayList<>();
        String getAllPublicChallengesSql = "Select * From non_coding_challenge where publicity = 1";
        int i = 0;
        if ( input.size() > 0 ) {
            getAllPublicChallengesSql += "AND non_challenge_id IN";
            while (i < input.size() - 1) {
                getAllPublicChallengesSql += "(SELECT non_challenge_id from coding_challenge_categories WHERE category =  ? " +
                        " AND non_challenge_id IN";

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
                int non_challenge_id = rs.getInt("non_challenge_id");

                NonCodingChallengeQueryResponse nccqr = new NonCodingChallengeQueryResponse( title, difficulty,non_challenge_id);

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
                non_challenge_id = rs.getInt("non_challenge_id");
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
                int non_challenge_id = rs.getInt("non_challenge_id");
                NonCodingChallengeQueryResponse nccqr = new NonCodingChallengeQueryResponse(title, difficulty,non_challenge_id);

                result.add(nccqr);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;
    }

    public MessageResponse replyQuestion(Reply reply){

        try {
            String insertReplySql = "Insert into reply VALUES( ?, ?, ?, null, ?)";

            PreparedStatement insertStmt= ConnectionSingle.getConnection().prepareStatement(insertReplySql);
            insertStmt.setInt(1,reply.getNonChallengeId());
            insertStmt.setInt(2, reply.getUserId());
            insertStmt.setString(3,reply.getAnswer());
            insertStmt.setTimestamp(4,reply.getReplyTime());

            insertStmt.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Your answer is submitted");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
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

    public List<OtherAnswerResponse> seeOtherCodersAnswers(int userId, int nonChallengeId){
        List<OtherAnswerResponse> result = new ArrayList<>();

        try {
            String seeOtherCodersAnswersSql = "SELECT username, answer FROM reply R1 NATURAL JOIN users  C " +
                    "WHERE non_challenge_id = ? AND EXISTS (select * from reply R2 where answer IS NOT NULL and " +
                    "R2.user_id = ?  and R2.non_challenge_id = ?)";
            PreparedStatement seeOtherCodersAnswersPrepared = ConnectionSingle.getConnection().prepareStatement(seeOtherCodersAnswersSql);
            seeOtherCodersAnswersPrepared.setInt(1,nonChallengeId);
            seeOtherCodersAnswersPrepared.setInt(2,userId);
            seeOtherCodersAnswersPrepared.setInt(3,nonChallengeId);
            ResultSet rs = seeOtherCodersAnswersPrepared.executeQuery();


            while (rs.next()){

                String username = rs.getString("username");

                String answer = rs.getString("answer");

                OtherAnswerResponse otherAnswerResponse = new OtherAnswerResponse(username, answer);

                result.add(otherAnswerResponse);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;




    }


    public NonCodingChallengeAuthorCategoryResponse getNonCodingChallengeAuthorResponse(int challengeId)
    {
        List<String> categoryList = new ArrayList<>();
        String author = "";
        try {
            String getChallengeSql = "Select * From non_coding_challenge_categories where non_challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
            insertCodingPrepared.setInt(1,challengeId);
            ResultSet rs = insertCodingPrepared.executeQuery();


            while (rs.next()){

                categoryList.add( rs.getString("category"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        try {
            String getChallengeSql = "Select * From make M, users U where U.user_id = M.user_id" +
                    " and non_challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
            insertCodingPrepared.setInt(1,challengeId);
            ResultSet rs = insertCodingPrepared.executeQuery();

            while (rs.next()){

                author = rs.getString("username");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  new NonCodingChallengeAuthorCategoryResponse(categoryList,author);
    }

    public int createAndAddNonQuestionToInterview(int interviewId, int companyId, NonCodingChallenge noncodingChallenge){
        try {
            int nonCodingChallengeId = insertNonCodingChallengeTable(noncodingChallenge);
            addNonCodingQuestionToInterview(interviewId,nonCodingChallengeId,companyId );
            return nonCodingChallengeId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;


    }

    public void addNonCodingQuestionToInterview(int interview_id,int non_challenge_id, int company_id)  {
        try {
            String query = "INSERT INTO made_of(non_challenge_id, interview_id,user_id) VALUES (?,?,?) ";
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


    public List<CategoryandNumberResponse> getCategoriesAndNumbers()
    {
        List<CategoryandNumberResponse> result = new ArrayList<>();
        String category ="";
        int number = -1;
        try {
            String getChallengeSql = "Select * From catnumbersnoncodingView ";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);

            ResultSet rs = insertCodingPrepared.executeQuery();

            while (rs.next()) {
                category = rs.getString("category");
                number = rs.getInt("cat_number");
                CategoryandNumberResponse cr = new CategoryandNumberResponse(category,number);
                result.add(cr);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


}
