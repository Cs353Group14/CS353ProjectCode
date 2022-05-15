package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.Company;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.CategoryandNumberResponse;
import cs353.group14.responses.CodingChallengeAuthorCategoryResponse;
import cs353.group14.responses.CodingChallengeQueryResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CodingChallengeRepository {

    public int createCodingChallenge(int editorId, CodingChallenge codingChallenge){

        try {
            int codingChallengeId = insertCodingChallengeTable(codingChallenge);
            insertCreatesTable(editorId,codingChallengeId);
            return codingChallengeId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int createAndAddQuestionToInterview(int interviewId,int company_id, int time_limit, CodingChallenge codingChallenge){
        try {
            int codingChallengeId = insertCodingChallengeTable(codingChallenge);
            addCodingQuestionToInterview(interviewId,codingChallengeId,company_id, time_limit );
            return codingChallengeId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    public void addCodingQuestionToInterview(int interview_id,int challenge_id, int company_id, int time_limit)  {
        try {
            String query = "INSERT INTO includes(challenge_id, interview_id,company_id,time_limit) VALUES (?,?,?,?) ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, challenge_id);
            preparedStatement.setInt(2, interview_id);
            preparedStatement.setInt(3, company_id);
            preparedStatement.setInt(4, time_limit);

            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
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

    public MessageResponse updateDifficultyCodingChallenge(int challenge_id, String difficulty) {
        try {
            String query = "UPDATE coding_challenge SET difficulty = ? where challenge_id = ?";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setString(1, difficulty);
            preparedStatement.setInt(2, challenge_id);
            int result = preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Update is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }
    }

    public MessageResponse addCategoryCodingChallenge( int challenge_id, String category)
    {
        try {
            String query = "INSERT INTO coding_challenge_categories( challenge_id,category) VALUES ( ?,?);";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,challenge_id);
            preparedStatement.setString(2,category);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Add is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }
    }




    public MessageResponse removeCategoryFromChallenge( int challenge_id, String category)
    {
        try {
            String query = "DELETE FROM coding_challenge_categories where challenge_id = ? and category = ?";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,challenge_id);
            preparedStatement.setString(2,category);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Remove is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }
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



    public List<CodingChallengeQueryResponse> getChallengesOfEditor( int editorId)  {

        List<CodingChallengeQueryResponse> result = new ArrayList<>();


        try {
            String getAllPublicChallengesSql = "Select * From creates C, coding_challenge CC where C.challenge_id = CC.challenge_id and C.user_id = ? and publicity = 1";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(getAllPublicChallengesSql);
            preparedStatement.setInt(1,editorId);
            ResultSet rs = preparedStatement.executeQuery();
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



    public List<CodingChallengeQueryResponse> getAllPublicCodingChallengesWithFiltered( List <String> input )  {

        List<CodingChallengeQueryResponse> result = new ArrayList<>();
        String getAllPublicChallengesSql = "Select * From coding_challenge where publicity = 1";
        int i = 0;
        if ( input.size() > 0 ) {
            getAllPublicChallengesSql += "AND challenge_id IN";
            while (i < input.size() - 1) {
                getAllPublicChallengesSql += "(SELECT challenge_id from coding_challenge_categories WHERE category =  ? " +
                        " AND challenge_id IN";

                i++;
            }

            getAllPublicChallengesSql += "(SELECT challenge_id from coding_challenge_categories WHERE category = ?)";
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

    public CodingChallengeAuthorCategoryResponse getCodingChallengeAuthorResponse(int challengeId)
    {
        List<String> categoryList = new ArrayList<>();
        String author = "";
        try {
            String getChallengeSql = "Select * From coding_challenge_categories where challenge_id = ?";
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
            String getChallengeSql = "Select * From creates C, users U where U.user_id = C.user_id" +
                    " and challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
            insertCodingPrepared.setInt(1,challengeId);
            ResultSet rs = insertCodingPrepared.executeQuery();


            while (rs.next()){

                author = rs.getString("username");


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return  new CodingChallengeAuthorCategoryResponse(categoryList,author);
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



    public MessageResponse addTestCaseForCodingChallenge( int challenge_id, String input, String output)
    {
        try {
            String query = "INSERT INTO test_case( challenge_id, inputs, outputs) VALUES ( ?, ?, ?)";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,challenge_id);
            preparedStatement.setString(2,input);
            preparedStatement.setString(3,output);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Add is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }

    }


    public List<String> getInputsForCodingChallenge( int challenge_id )
    {
        List<String> result = new ArrayList<>();

        try {
            String getChallengeSql = "Select * From test_case where challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
            insertCodingPrepared.setInt(1,challenge_id);
            ResultSet rs = insertCodingPrepared.executeQuery();



            while (rs.next()){
                List <String> a;
                result.add(rs.getString("inputs"));


            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }



    public List<String> getOutputsForCodingChallenge( int challenge_id ) {
        List<String> result = new ArrayList<>();

        try {
            String getChallengeSql = "Select * From test_case where challenge_id = ?";
            PreparedStatement insertCodingPrepared = ConnectionSingle.getConnection().prepareStatement(getChallengeSql);
            insertCodingPrepared.setInt(1, challenge_id);
            ResultSet rs = insertCodingPrepared.executeQuery();


            while (rs.next()) {
                List<String> a;
                result.add(rs.getString("outputs"));

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;


    }

    public MessageResponse makeCompanySponsorToContest( int contestId, int companyId)
    {
        try {
            String query = "INSERT INTO sponsor( contest_id,user_id ) VALUES(?,?)";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contestId);
            preparedStatement.setInt(2,companyId);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Your company is now sponsor to the contest");
        } catch (SQLException throwables) {

            return new MessageResponse(MessageType.ERROR, "The company already sponsor to contest\n" );
        }
    }


    public List<Company> getSponsorsOfContest(int contestId, int companyId)
    {
        List<Company> result = new ArrayList<>();
        try {
            String query = "INSERT INTO sponsor( contest_id,user_id ) VALUES(?,?)";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contestId);
            preparedStatement.setInt(2,companyId);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public List<CategoryandNumberResponse> getCategoriesAndNumbers()
    {
        List<CategoryandNumberResponse> result = new ArrayList<>();
        String category ="";
        int number = -1;
        try {
            String getChallengeSql = "Select * From catnumbersView ";
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
