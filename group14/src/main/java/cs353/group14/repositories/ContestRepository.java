package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.Contest;
import cs353.group14.db.ConnectionSingle;
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
            String query = "INSERT INTO prepare ( editor_id, contest_id) " +
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
                }
                query = query + "(?,?)";
            }
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            for (int i = 0; i < questionsToAdded.size(); i++) {

                preparedStatement.setInt(2 * i, contest_id);
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

    }

    public void getContest()
    {

    }

    public void deleteContest( int contest_id)
    {

    }
}
