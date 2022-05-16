package cs353.group14.repositories;

import cs353.group14.CodingChallenge;
import cs353.group14.Contest;
import cs353.group14.ContestAuthor;
import cs353.group14.NonCodingChallenge;
import cs353.group14.common.MessageResponse;
import cs353.group14.common.MessageType;
import cs353.group14.db.ConnectionSingle;
import cs353.group14.responses.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class ContestRepository {



    public MessageResponse createContest(int editor_id, Timestamp start_time, String description, String title, int difficulty,
                              int duration, Timestamp deadline) {
        try {
            int contestId = insertContestTable(start_time, description, title, difficulty, duration, deadline);
            insertPrepareTable(editor_id, contestId);
            return new MessageResponse(MessageType.SUCCESS, "" + contestId);
        } catch (SQLException e) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + e.getMessage());
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
    public MessageResponse insertPrepareTable( int editor_id, int contest_id) {
        try {
            String query = "INSERT INTO prepare ( user_id, contest_id) " +
                    "VALUES ( ?, ?)";
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,editor_id);
            preparedStatement.setInt(2,contest_id);


            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "add is successful");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }

    }

    public MessageResponse addQuestionToContest(int contest_id, List<Integer> questionsToAdded)  {
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
            return new MessageResponse(MessageType.SUCCESS, "Questions are added to the contest");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }

    }

    public MessageResponse addCoderToContest( int user_id, int contest_id)
    {
        try {
            String query = "INSERT INTO participate(contest_id, user_id,points) VALUES(?,?,0) ";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);
            preparedStatement.setInt(2,user_id);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Registration is successful. You can see contest in Registered Contests");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
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

    public ContestAuthor getContestWithAuthor(int contest_id){

        Timestamp start_time = null;
        String description = "";
        String title = "";
        int difficulty = 0;
        int duration = 0;
        Timestamp deadline = null;
        String author = "";

        try {
            String query = "SELECT * FROM contest, prepare, users where contest.contest_id = ? and prepare.contest_id = contest.contest_id and prepare.user_id = users.user_id";
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
                author = ( rs.getString("username"));
            }


        }

        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return new ContestAuthor(contest_id,start_time,description,title,difficulty,duration,deadline,author);
    }

    public List<Contest> getFutureContestsRegistered( int userId)
    {
        Timestamp start_time = null; String description = ""; String title = ""; int difficulty = -1;
        int duration = -1; Timestamp deadline = null; int contest_id = -1;
        List<Contest> result = new ArrayList<>();
        try {
            String query = "Select * From participate P,  contest C where C.contest_id = P.contest_id and P.user_id = ? and C.start_time > CURRENT_TIMESTAMP + INTERVAL '03:00' HOUR TO MINUTE ";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                start_time = ( rs.getTimestamp("start_time"));
                description = ( rs.getString("description"));
                title = ( rs.getString("title"));
                difficulty = ( rs.getInt("difficulty"));
                duration = ( rs.getInt("duration"));
                deadline = ( rs.getTimestamp("deadline"));

                Contest contest = new Contest(contest_id,start_time,description,title,difficulty,duration,deadline);

                result.add(contest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return result;

    }



    public List<Contest> getFutureContestsNotRegistered( int userId)
    {
        Timestamp start_time = null; String description = ""; String title = ""; int difficulty = -1;
        int duration = -1; Timestamp deadline = null; int contest_id = -1;
        List<Contest> result = new ArrayList<>();
        try {
            String query = "SELECT * from  contest where start_time > CURRENT_TIMESTAMP  EXCEPT " +
                    "Select C.contest_id,C.start_time,C.description,C.title,C.difficulty,C.duration,C.deadline" +
                    " From participate P,  contest C where C.contest_id = P.contest_id and P.user_id = ? ";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                start_time = ( rs.getTimestamp("start_time"));
                description = ( rs.getString("description"));
                title = ( rs.getString("title"));
                difficulty = ( rs.getInt("difficulty"));
                duration = ( rs.getInt("duration"));
                deadline = ( rs.getTimestamp("deadline"));

                Contest contest = new Contest(contest_id,start_time,description,title,difficulty,duration,deadline);

                result.add(contest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
        return result;

    }



    public List<CodingChallengeQueryResponse> getCodingChallengesOfContest(int contest_id)
    {
        List<CodingChallengeQueryResponse> result = new ArrayList<>();

        int challenge_id = -1;
        int points = -1;
        String difficulty = "";
        int solved_number = -1;
        int attempt_number = -1;
        String title = "";
        try{
            String query = "Select C.challenge_id, points, difficulty, solved_number, attempt_number, title from  consist CS, coding_challenge C  " +
                    "where C.challenge_id = CS.challenge_id and CS.contest_id = ? ";
            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                challenge_id = rs.getInt("challenge_id");
                points = rs.getInt("points");
                difficulty = rs.getString("difficulty");
                solved_number = rs.getInt("solved_number");
                attempt_number = rs.getInt("attempt_number");
                title = rs.getString("title");

                CodingChallengeQueryResponse cc =  new CodingChallengeQueryResponse (challenge_id, title, difficulty, points, solved_number, attempt_number);

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

    public int getContestStatus(int user_id, int contest_id){
        // null sa 0 gönder set yapıyoruz
        // duration geçmemisse sadece 1 yolluyorum
        // duration geçimişse sadece 2
        // register olmamışsa veya contest yoksa -2
        // başka bir hata varsa -1

        Timestamp participate_start_time = null;
        Timestamp participate_end_time = null;

        int duration = -1;
        try {
            String query = "SELECT * FROM participate P, contest C where P.user_id = ? and P.contest_id = ? and P.contest_id = C.contest_id";
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(2,contest_id);

            ResultSet rs = preparedStatement.executeQuery();


            int size =0;
            while (rs.next()){
                participate_start_time =  rs.getTimestamp("participate_start_time");
                duration = rs.getInt("duration");
                size++;
            }
            if(size!=1){
                return -2;
            }else if(participate_start_time==null){
                return 0;
            }else{
                participate_end_time = new Timestamp(participate_start_time.getTime() + TimeUnit.MINUTES.toMillis(duration));

                Timestamp current = new Timestamp(System.currentTimeMillis());

               if(participate_end_time.before(current)){
                   return 2;
               }else{
                   return 1;
               }
            }


        }

        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return -1;


    }

    public MessageResponse startContest(int user_id, int contest_id ){

        int status = getContestStatus(user_id,contest_id);

        if(status == 0){
            try{

                String updateContestSql = "UPDATE participate SET participate_start_time = ? where user_id = ? and contest_id = ? and participate_start_time is null";
                Timestamp now = new Timestamp(System.currentTimeMillis());


                PreparedStatement updateAttemptStmt = ConnectionSingle.getConnection().prepareStatement(updateContestSql);
                updateAttemptStmt.setTimestamp(1,now);
                updateAttemptStmt.setInt(2,user_id);
                updateAttemptStmt.setInt(3, contest_id);

                updateAttemptStmt.executeUpdate();

                return new MessageResponse(MessageType.SUCCESS, "Your time has started. After duration time of contest is up you will not be able to make submissions.");


            } catch (SQLException e) {
                return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + e.getMessage());
            }

        } else if (status == 1){
            return new MessageResponse(MessageType.ERROR, "You have already started to this contest");
        } else if(status == 2) {
            return new MessageResponse(MessageType.ERROR, "Deadline of contest has passed");
        } else {
            return new MessageResponse(MessageType.ERROR,"Something went wrong. Please try again");
        }

    }

    public List<Contest> getAvailableRegisteredContests(int userId){
        List<Contest> result = new ArrayList<>();

        Timestamp start_time = null;
        String description = "";
        String title = "";
        int difficulty = -1;
        int duration = -1;
        Timestamp deadline = null;
        int contest_id = -1;

        try {
            String query = "SELECT * From participate P, contest C where C.contest_id = P.contest_id and P.user_id = ? and C.start_time < CURRENT_TIMESTAMP and C.deadline > CURRENT_TIMESTAMP";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                start_time = ( rs.getTimestamp("start_time"));
                description = ( rs.getString("description"));
                title = ( rs.getString("title"));
                difficulty = ( rs.getInt("difficulty"));
                duration = ( rs.getInt("duration"));
                deadline = ( rs.getTimestamp("deadline"));

                Contest contest = new Contest(contest_id,start_time,description,title,difficulty,duration,deadline);

                result.add(contest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }


    public List<ContestResponse> getOldRegisteredContests(int userId) {

        List<ContestResponse> result = new ArrayList<>();

        Timestamp start_time = null;
        String description = "";
        String title = "";
        int difficulty = -1;
        int duration = -1;
        Timestamp deadline = null;
        int contest_id = -1;

        int points = -1;

        try {
            String query = "SELECT * From participate P, contest C where C.contest_id = P.contest_id and P.user_id = ? and C.deadline < CURRENT_TIMESTAMP";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                start_time = ( rs.getTimestamp("start_time"));
                description = ( rs.getString("description"));
                title = ( rs.getString("title"));
                difficulty = ( rs.getInt("difficulty"));
                duration = ( rs.getInt("duration"));
                deadline = ( rs.getTimestamp("deadline"));
                points = rs.getInt("points");

                ContestResponse contest = new ContestResponse(contest_id,start_time,description,title,difficulty,duration,deadline,points);

                result.add(contest);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public MessageResponse cancelContestParticipation(int userId, int contestId) {
        try {
            String query = "DELETE FROM participate WHERE user_id = ? and contest_id =? ";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,contestId);
            preparedStatement.executeUpdate();
            return new MessageResponse(MessageType.SUCCESS, "Your registration is canceled");
        } catch (SQLException throwables) {
            return new MessageResponse(MessageType.ERROR, "Error occurred in SQL\n" + throwables.getMessage());
        }
    }

    public List<ContestDeadlineResponse> getAllContests() {

        List<ContestDeadlineResponse> result = new ArrayList<>();

        String title = "";
        Timestamp deadline = null;
        int contest_id = -1;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String name = "";

        try{
            String query = "SELECT contest_id,title,deadline, name From contest natural join prepare natural join users ORDER BY deadline ASC";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                title = ( rs.getString("title"));
                deadline = ( rs.getTimestamp("deadline"));
                name = ( rs.getString("name"));
                ContestDeadlineResponse contest = new ContestDeadlineResponse(contest_id,title,timestamp.after(deadline),name);

                result.add(contest);
            }


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return result;

    }

    public List<ContestDeadlineResponse> getSponsoredContests(int companyId) {
        List<ContestDeadlineResponse> result = new ArrayList<>();

        String title = "";
        Timestamp deadline = null;
        int contest_id = -1;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String name = "";

        try{
            String query = "SELECT contest_id,title,deadline, name From contest natural join prepare natural join users" +
                    " where exists (select * from sponsor where sponsor.user_id = ? and " +
                    "sponsor.contest_id = contest.contest_id)";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,companyId);
            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                title = ( rs.getString("title"));
                deadline = ( rs.getTimestamp("deadline"));
                name = ( rs.getString("name"));
                ContestDeadlineResponse contest = new ContestDeadlineResponse(contest_id,title,timestamp.after(deadline),name);

                result.add(contest);
            }


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }



        return result;
    }

    public List<ContestDeadlineResponse> getContestsForEditor(int editorId) {

        List<ContestDeadlineResponse> result = new ArrayList<>();

        String title = "";
        Timestamp deadline = null;
        int contest_id = -1;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String name = "";

        try{
            String query = "SELECT contest_id,title,deadline, name From contest natural join prepare natural join users where user_id = ? ORDER BY deadline ASC";

            PreparedStatement preparedStatement = ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,editorId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                contest_id =( rs.getInt("contest_id"));
                title = ( rs.getString("title"));
                deadline = ( rs.getTimestamp("deadline"));
                name = ( rs.getString("name"));
                ContestDeadlineResponse contest = new ContestDeadlineResponse(contest_id,title,timestamp.after(deadline),name);

                result.add(contest);
            }


        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return result;
    }

    public ContestStatisticResponse getContestStatistic( int contest_id)
    {

        int max = -1;
        int min = -1;
        int avg = -1;
        int userNumber = -1;


        try {
            String query = "SELECT * FROM conteststatisticsView where contest_id = ?";
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                max = ( rs.getInt("maxpoint"));
                min = ( rs.getInt("minpoint"));
                avg = ( rs.getInt("avgpoint"));
                userNumber = ( rs.getInt("usernumber"));

            }
        }

        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return new ContestStatisticResponse(max,min,avg,userNumber);

    }




    public List<String> getContestSponsors( int contest_id)
    {

        List<String> result = new ArrayList<>();


        String element ="";
        try {
            String query = "SELECT * FROM sponsor S,users U where S.user_id = U.user_id " +
                    "and S.contest_id = ?";
            PreparedStatement preparedStatement= ConnectionSingle.getConnection().prepareStatement(query);
            preparedStatement.setInt(1,contest_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                element = ( rs.getString("name"));

                result.add(element);
            }
        }

        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        return result;

    }

}
