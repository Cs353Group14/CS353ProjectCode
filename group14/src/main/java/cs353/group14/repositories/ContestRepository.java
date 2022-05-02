package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.Contest;
import cs353.group14.NonCodingChallenge;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.NonCodingChallengeQueryResponse;
import cs353.group14.responses.UserNameAndPointResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContestRepository {



    public void createContest(int editor_id, Timestamp start_time, String description, String title, int difficulty,
                              int duration, Timestamp deadline) {
        try {
            int contestId = insertContestTable(start_time, description, title, difficulty, duration, deadline);
            insertPrepareTable(editor_id, contestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int insertContestTable( Timestamp start_time, String description, String title, int difficulty,
                                   int duration, Timestamp deadline) throws SQLException {


            String query = "INSERT INTO contest ( start_time, description, title, difficulty, duration, deadline ) " +
                    "VALUES ( ?, ?, ?,?, ?, ?)";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, start_time);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, title);
            preparedStatement.setInt(4, difficulty);
            preparedStatement.setInt(5, duration);
            preparedStatement.setTimestamp(6, deadline);

            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
        keys.next();

        return keys.getInt(1);

    }
    public void insertPrepareTable( int editor_id, int contest_id) {
        try {
            String query = "INSERT INTO prepare ( user_id, contest_id) " +
                    "VALUES ( ?, ?)";
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,editor_id);
            preparedStatement.setInt(2,contest_id);


            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public void addQuestionToContest(int contest_id, List<Integer> questionsToAdded)  {
        try {
            String query = "INSERT INTO consist(challenge_id, contest_id) VALUES ";
            if (questionsToAdded.size() > 0) {

                int q = 0;
                while (q < questionsToAdded.size() - 1) {
                    query = query + "(?,?),";
                    q++;
                }
                query = query + "(?,?)";
            }
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            for (int i = 0; i < questionsToAdded.size(); i++) {

                preparedStatement.setInt(2 * i+1, questionsToAdded.get(i));
                preparedStatement.setInt(2 * i+2, contest_id);
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public void addCoderToContest( int user_id, int contest_id)
    {
        try {
            String query = "INSERT INTO participate(contest_id, user_id,points) VALUES(?,?,0) ";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);
            preparedStatement.setInt(2,user_id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public List<UserNameAndPointResponse> getOrder( int contestId)
    {
        List<UserNameAndPointResponse> result = new ArrayList<>();

        try {
            String query = "Select * from participate P, users U where P.user_id = U.user_id and P.contest_id = ? order by P.points DESC";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contestId);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){

                String username = rs.getString("username");

                int points = rs.getInt("points");

                UserNameAndPointResponse userNameAndPointResponse = new UserNameAndPointResponse(points,username );

                result.add(userNameAndPointResponse);
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return result;
    }




    public Contest getContest(int contest_id)
    {
        Timestamp start_time = null; String description = ""; String title = ""; int difficulty = 0;
        int duration = 0; Timestamp deadline = null;
        try {
            String query = "SELECT * FROM contest where contest_id = ?";
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                start_time = ( rs.getTimestamp("start_time"));
                description = ( rs.getString("description"));
                title = ( rs.getString("title"));
                difficulty = ( rs.getInt("difficulty"));
                duration = ( rs.getInt("duration"));
                deadline = ( rs.getTimestamp("deadline"));
            }
        }

        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return new Contest(contest_id,start_time,description,title,difficulty,duration,deadline);
    }
    public List<CodingChallenge> getCodingChallengesOfContest( int contest_id)
    {
        List<CodingChallenge> result = new ArrayList<>();

        int challenge_id = -1;
        String question = "";
        int points = -1;
        String difficulty = "";
        int solved_number = -1;
        int attempt_number = -1;
        String title = "";
        String solution = "";
        int publicity = -1;
        try{
            String query = "Select * from  consist CS, coding_challenge C  " +
                    "where C.challenge_id = CS.challenge_id and CS.contest_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);

            ResultSet rs = preparedStatement.executeQuery();

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

                CodingChallenge cc =  new CodingChallenge (challenge_id, question, points, difficulty,  solved_number, attempt_number, title, solution, publicity);

                result.add(cc);
            }

        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return  result;
    }



    public void deleteContest( int contest_id)
    {

    }
}
