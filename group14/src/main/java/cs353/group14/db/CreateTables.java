package cs353.group14.db;

import cs353.group14.*;
import cs353.group14.services.CodingChallengeService;
import cs353.group14.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;

@Service
@Component
public class CreateTables {


    private final UserService userService;
    private final CodingChallengeService codingChallengeService;

    public CreateTables(UserService userService, CodingChallengeService codingChallengeService) {
        this.userService = userService;
        this.codingChallengeService = codingChallengeService;
    }

    //@Bean
    public void createUserTables() {

        System.out.println("create table");

        int sqlCount = 28;
        int viewCount = 4;
        int indexCount = 3;
        String[] createSqls = new String[sqlCount];
        String[] dropSqls = new String[sqlCount];

        String[] dropViews = new String[viewCount ];
        String[] dropIndex = new String[indexCount];

        String[] createViews = new String[viewCount];
        String[] createIndex = new String[indexCount];

        createIndex[0] = "CREATE INDEX coderPointIndex ON coder(points)";
        createIndex[1] = "CREATE INDEX coderRatingIndex ON coder(rating)";
        createIndex[2] = "CREATE INDEX usersUsernameIndex ON users(username)";

        dropIndex[0] = " DROP INDEX IF EXISTS coderPointIndex";
        dropIndex[1] = " DROP INDEX IF EXISTS coderRatingIndex";
        dropIndex[2] = " DROP INDEX IF EXISTS usersUsernameIndex";

        createViews[0] = " CREATE VIEW userCodersView AS SELECT U.user_id, U.username, U.mail, U.name,U.profile_photo,U.information,\n" +
                "C.rating, C.position, C.points, C.place, C.birth_year FROM coder C, users U where U.user_id = C.user_id;";

        createViews[1] = " CREATE VIEW catNumbersView AS SELECT Count(*) as cat_number, category FROM coding_challenge_categories \n" +
                "group by category order by cat_number desc;";

        createViews[2] = " CREATE VIEW catNumbersNonCodingView AS SELECT Count(*) as cat_number, category FROM non_coding_challenge_categories \n" +
                "group by category order by cat_number desc;";

        createViews[3] = " CREATE VIEW contestStatisticsView AS SELECT contest_id, count(*) as userNumber, max(points) as maxPoint, avg(points) as avgPoint, min(points) as minPoint\n" +
                "FROM participate  group by contest_id;";


        dropViews[0] = " DROP VIEW IF EXISTS userCodersView" ;
        dropViews[1] = " DROP VIEW IF EXISTS catNumbersView" ;
        dropViews[2] = " DROP VIEW IF EXISTS catNumbersNonCodingView" ;
        dropViews[3] = " DROP VIEW IF EXISTS contestStatisticsView" ;


        dropSqls[0] = "users";
        dropSqls[1] = "admin";
        dropSqls[2] = "editor";
        dropSqls[3] = "company";
        dropSqls[4] = "coder";
        dropSqls[5] = "coding_challenge";
        dropSqls[6] = "creates";
        dropSqls[7] = "test_case";
        dropSqls[8] = "submission";
        dropSqls[9] = "submit";
        dropSqls[10] = "notification";
        dropSqls[11] = "notify";
        dropSqls[12] = "refer";
        dropSqls[13] = "suggest";
        dropSqls[14] = "non_coding_challenge";
        dropSqls[15] = "reply";
        dropSqls[16] = "make";
        dropSqls[17] = "contest";
        dropSqls[18] = "participate";
        dropSqls[19] = "sponsor";
        dropSqls[20] = "consist";
        dropSqls[21] = "interview";
        dropSqls[22] = "prepare";
        dropSqls[23] = "includes";
        dropSqls[24] = "made_of";
        dropSqls[25] = "attend";
        dropSqls[26] = "coding_challenge_categories";
        dropSqls[27] = "non_coding_challenge_categories";

        // enum yerine int koydum

        createSqls[0] = "CREATE TABLE users( " +
                "user_id SERIAL PRIMARY KEY, " +
                "username VARCHAR(31) NOT NULL UNIQUE, " +
                "password VARCHAR(31) NOT NULL, " +
                "mail VARCHAR(255) NOT NULL UNIQUE, " +
                "name VARCHAR(31) NOT NULL, " +
                "usertype INTEGER NOT NULL, " +
                "profile_photo BYTEA, " +
                "information VARCHAR(1023)) ";

        createSqls[1] = "CREATE TABLE admin( " +
                "user_id INTEGER REFERENCES users (user_id), " +
                "PRIMARY KEY (user_id) )";

        createSqls[2] = "CREATE TABLE editor(" +
                "user_id INTEGER REFERENCES users (user_id)," +
                "position VARCHAR(31)," +
                "place VARCHAR(31)," +
                "PRIMARY KEY (user_id))";

        createSqls[3] = "CREATE TABLE company(" +
                "user_id INTEGER REFERENCES users (user_id)," +
                "location VARCHAR(255)," +
                "web_page_link VARCHAR(63)," +
                "PRIMARY KEY (user_id))";

        createSqls[4] = "CREATE TABLE coder(" +
                "   user_id INTEGER REFERENCES users (user_id)," +
                "   rating INTEGER NOT NULL," +
                "   points INTEGER NOT NULL," +
                "   position VARCHAR(31)," +
                "   place VARCHAR(255)," +
                "   birth_year INTEGER NOT NULL," +
                "   PRIMARY KEY (user_id))";

        createSqls[5] = "CREATE TABLE coding_challenge (" +
                " challenge_id SERIAL NOT NULL ," +
                "  question VARCHAR(1023) NOT NULL," +
                "  points INTEGER NOT NULL," +
                "  difficulty VARCHAR(31) NOT NULL," +
                "  solved_number INTEGER NOT NULL," +
                "  attempt_number INTEGER NOT NULL," +
                "  title VARCHAR(31) NOT NULL," +
                "  solution VARCHAR(1023) NOT NULL," +
                "  publicity INTEGER NOT NULL," +
                " PRIMARY KEY(challenge_id)) ";

        createSqls[6] = "CREATE TABLE creates (" +
                " user_id INTEGER NOT NULL," +
                " challenge_id INTEGER NOT NULL," +
                " PRIMARY KEY(challenge_id,user_id)," +
                " FOREIGN KEY(challenge_id) REFERENCES coding_challenge(challenge_id)," +
                " FOREIGN KEY(user_id) REFERENCES editor(user_id))";

        createSqls[7] = "CREATE TABLE test_case (" +
                "  challenge_id INTEGER NOT NULL," +
                "  case_id SERIAL NOT NULL," +
                "  inputs VARCHAR(127)," +
                "  outputs VARCHAR(127)," +
                "  PRIMARY KEY (challenge_id, case_id)," +
                " FOREIGN KEY (challenge_id) REFERENCES coding_challenge(challenge_id)" +
                " ON DELETE CASCADE)";

        createSqls[8] = "CREATE TABLE submission (" +
                " submission_id SERIAL NOT NULL," +
                "  answer VARCHAR(1023)," +
                "  pass_result INTEGER NOT NULL," +
                "  fail_result INTEGER NOT NULL," +
                "  programming_language varchar(31) NOT NULL," +
                "submission_time TIMESTAMP NOT NULL," +
                "PRIMARY KEY(submission_id))";

        createSqls[9] = "CREATE TABLE submit (" +
                "challenge_id INTEGER NOT NULL," +
                "submission_id INTEGER NOT NULL," +
                "user_id INTEGER NOT NULL," +
                "PRIMARY KEY( challenge_id, submission_id, user_id)," +
                " FOREIGN KEY(challenge_id) REFERENCES coding_challenge(challenge_id)," +
                " FOREIGN KEY(submission_id) REFERENCES submission(submission_id)," +
                "FOREIGN KEY(user_id) REFERENCES coder(user_id))";

        createSqls[10] = "CREATE TABLE notification(" +
                "   n_id SERIAL PRIMARY KEY," +
                "   n_info VARCHAR(1023) NOT NULL," +
                "   notif_date TIMESTAMP NOT NULL," +
                "   type VARCHAR(31) NOT NULL)";

        createSqls[11] = "CREATE TABLE notify(" +
                "   user_id INTEGER REFERENCES users (user_id)," +
                "   n_id INTEGER REFERENCES notification(n_id)," +
                "   PRIMARY KEY (user_id, n_id))";

        createSqls[12] = "CREATE TABLE refer(" +
                "user_id INTEGER REFERENCES coder(user_id)," +
                "referred_id INTEGER REFERENCES coder(user_id)," +
                "refer_reason VARCHAR(255)," +
                "accepted INTEGER," +
                "PRIMARY KEY (user_id, referred_id))";

        createSqls[13] = "CREATE TABLE suggest(" +
                "   user_id INTEGER REFERENCES editor(user_id)," +
                "   coder_id INTEGER REFERENCES coder(user_id)," +
                "   suggest_reason VARCHAR(255)," +
                "   accepted INTEGER," +
                "   PRIMARY KEY (user_id, coder_id))";

        createSqls[14] = "CREATE TABLE non_coding_challenge (" +
                " non_challenge_id SERIAL NOT NULL ," +
                "  question VARCHAR(1023) NOT NULL," +
                "  difficulty VARCHAR(31) NOT NULL," +
                "  title VARCHAR(31) NOT NULL," +
                "  publicity INTEGER NOT NULL," +
                "PRIMARY KEY(non_challenge_id)) ";

        createSqls[15] = "CREATE TABLE reply (" +
                "  non_challenge_id INTEGER NOT NULL," +
                "  user_id INTEGER NOT NULL," +
                " answer VARCHAR(1023)," +
                "  the_result VARCHAR(31)," +
                "  reply_time TIMESTAMP NOT NULL," +
                "   PRIMARY KEY (non_challenge_id,user_id)," +
                " FOREIGN KEY (non_challenge_id) REFERENCES non_coding_challenge(non_challenge_id)," +
                "  FOREIGN KEY (user_id) REFERENCES coder(user_id))";

        createSqls[16] = "CREATE TABLE make (" +
                "  non_challenge_id INTEGER not null," +
                "  user_id INTEGER not null," +
                "   PRIMARY KEY (non_challenge_id,user_id)," +
                " FOREIGN KEY (non_challenge_id) REFERENCES non_coding_challenge(non_challenge_id)," +
                "  FOREIGN KEY (user_id) REFERENCES editor(user_id))";

        createSqls[17] = "CREATE TABLE contest(" +
                "contest_id SERIAL PRIMARY KEY," +
                "start_time TIMESTAMP NOT NULL ," +
                "description VARCHAR(255) NOT NULL," +
                "title VARCHAR(63) NOT NULL," +
                "difficulty INTEGER NOT NULL," +
                "duration INTEGER NOT NULL," +
                "deadline TIMESTAMP NOT NULL," +
                "CHECK ( start_time > CURRENT_TIMESTAMP + INTERVAL '03:00' HOUR TO MINUTE )," +
                " CHECK ( deadline > start_time))";

        createSqls[18] = "CREATE TABLE participate(" +
                "contest_id INTEGER REFERENCES contest(contest_id)," +
                "user_id INTEGER REFERENCES coder(user_id)," +
                "points INTEGER NOT NULL ," +
                "participate_start_time TIMESTAMP ,"+
                "PRIMARY KEY (contest_id, user_id))";

        createSqls[19] = "CREATE TABLE sponsor(" +
                "contest_id INTEGER REFERENCES contest(contest_id)," +
                "user_id INTEGER REFERENCES company(user_id)," +
                "PRIMARY KEY (contest_id, user_id))";

        createSqls[20] = "CREATE TABLE consist (" +
                "  challenge_id INTEGER NOT NULL," +
                "  contest_id INTEGER NOT NULL," +
                "   PRIMARY KEY (challenge_id, contest_id)," +
                " FOREIGN KEY (challenge_id) REFERENCES coding_challenge(challenge_id)," +
                "  FOREIGN KEY (contest_id) REFERENCES contest(contest_id))";

        createSqls[21] = "CREATE TABLE interview (" +
                "  user_id INTEGER NOT NULL," +
                "  interview_id SERIAL NOT NULL," +
                "  duration INTEGER NOT NULL," +
                "  position VARCHAR(31) NOT NULL," +
                "   PRIMARY KEY (user_id, interview_id)," +
                " FOREIGN KEY (user_id) REFERENCES company(user_id)" +
                " ON DELETE CASCADE)";

        createSqls[22] = "CREATE TABLE prepare(" +
                "   contest_id INTEGER REFERENCES contest(contest_id)," +
                "   user_id INTEGER REFERENCES editor(user_id)," +
                "    PRIMARY KEY (contest_id, user_id))";

        createSqls[23] = "CREATE TABLE includes (" +
                "  challenge_id INTEGER NOT NULL," +
                "  interview_id INTEGER NOT NULL," +
                "  company_id INTEGER NOT NULL," +
                "  time_limit INTEGER NOT NULL," +
                "   PRIMARY KEY (challenge_id, interview_id,company_id)," +
                " FOREIGN KEY (challenge_id) REFERENCES coding_challenge(challenge_id)," +
                "  FOREIGN KEY (interview_id,company_id) REFERENCES interview(interview_id,user_id))";

        createSqls[24] = "CREATE TABLE made_of (" +
                "  interview_id INTEGER NOT NULL," +
                "  user_id INTEGER NOT NULL," +
                "  non_challenge_id INTEGER NOT NULL," +
                "  PRIMARY KEY ( non_challenge_id,interview_id,user_id)," +
                "  FOREIGN KEY(interview_id,user_id) REFERENCES interview(interview_id,user_id)," +
                "FOREIGN KEY(non_challenge_id) REFERENCES non_coding_challenge(non_challenge_id))";

        createSqls[25] = "CREATE TABLE attend (" +
                "  interview_id INTEGER NOT NULL," +
                "  coder_id INTEGER NOT NULL," +
                "  company_id INTEGER NOT NULL," +
                "  start_time TIMESTAMP NOT NULL," +
                "  end_time TIMESTAMP NOT NULL," +
                "  interview_result VARCHAR(31)," +
                "  invitation_code VARCHAR(127) NOT NULL," +
                "   PRIMARY KEY (interview_id,coder_id,company_id)," +
                " FOREIGN KEY (coder_id) REFERENCES coder(user_id)," +
                "  FOREIGN KEY (interview_id,company_id) REFERENCES interview(interview_id,user_id)," +
                "CHECK ( start_time > CURRENT_TIMESTAMP + INTERVAL '03:00' HOUR TO MINUTE )," +
                "CHECK ( end_time > start_time))";

        createSqls[26] = "CREATE TABLE coding_challenge_categories(" +
                "challenge_id INTEGER NOT NULL," +
                "category VARCHAR(31) NOT NULL," +
                "PRIMARY KEY(challenge_id,category)," +
                "FOREIGN KEY( challenge_id) REFERENCES coding_challenge(challenge_id))";

        createSqls[27] = "CREATE TABLE non_coding_challenge_categories(" +
                "non_challenge_id INTEGER NOT NULL," +
                "  category VARCHAR(31) NOT NULL," +
                "  PRIMARY KEY(non_challenge_id,category)," +
                "  FOREIGN key( non_challenge_id) REFERENCES non_coding_challenge(non_challenge_id))";

        /*
        try{
            Statement statement = ConnectionSingle.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS " + dropSqls[18]);
            statement.executeUpdate(createSqls[18]);


        }catch (SQLException e) {
            e.printStackTrace();
        }
         */

        try {

            Statement statement = ConnectionSingle.getConnection().createStatement();

            for ( int i = 0; i < indexCount; i++)
            {
                statement.executeUpdate( dropIndex[i]);
            }

            for ( int i = 0; i < viewCount; i++)
            {
                statement.executeUpdate( dropViews[i]);
            }
            for (int i = sqlCount - 1; i > -1; i--) {
                statement.executeUpdate("DROP TABLE IF EXISTS " + dropSqls[i]);
            }

            for (int i = 0; i < sqlCount; i++) {
                statement.executeUpdate(createSqls[i]);
            }

            for ( int i = 0; i < indexCount; i++)
            {
                statement.executeUpdate( createIndex[i]);
            }

            for ( int i = 0; i < viewCount; i++)
            {
                statement.executeUpdate( createViews[i]);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        //createTestUsers();


    }

    public void createTestUsers()  {

        System.out.println("create test users");

        String username = "admin";
        String password = "admin";
        String mail = "admin@admin.com";
        String name = "admin";
        int userId = -1;

        userService.registerAdmin(new Admin(userId,username,mail,password, UserType.Admin, name, "", ""));

        username = "akin";
        password = "1234";
        mail = "akin@gmail.com";
        name = "akin kutlu";

        String position = "position1";
        String place = "place1";
        int year = 1990;
        userService.registerCoder(new Coder(userId,username,mail,password,UserType.Coder,name,"","",0,0,position,place,year));

        username = "b";
        password = "1234";
        mail = "b@gmail.com";
        name = "b c";

        String location = "location1";
        String link = "web_page_link1";

        userService.registerCompany(new Company(userId,username,mail,password,UserType.Company,name,"","",location,link));

        username = "q";
        password = "1234";
        mail = "q@gmail.com";
        name = "q m";

        position = "position1";
        place = "place1";

        userService.registerEditor(new Editor(userId,username,mail,password,UserType.Editor,name,"","",position,place));

        for(int i=0;i<10;i++){
            codingChallengeService.createAtCodingChallenge(4,new CodingChallenge(-1,"q1",100,"zor",10,10,"title1","solution1",i%2));
        }

    }

}
