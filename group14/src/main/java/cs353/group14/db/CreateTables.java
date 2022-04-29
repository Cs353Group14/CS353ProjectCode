package cs353.group14.db;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;

@Service
@Component
public class CreateTables {

    @Bean
    public static void createUserTables() {

        int sqlCount = 8;

        String[] createSqls = new String[sqlCount];
        String[] dropSqls = new String[sqlCount];

        dropSqls[0] = "users";
        dropSqls[1] = "admin";
        dropSqls[2] = "editor";
        dropSqls[3] = "company";
        dropSqls[4] = "coder";
        dropSqls[5] = "coding_challenge";
        dropSqls[6] = "creates";
        dropSqls[7] = "test_case";

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
                "  case_id INTEGER NOT NULL," +
                "  inputs VARCHAR(127)," +
                "  outputs VARCHAR(127)," +
                "  PRIMARY KEY (challenge_id, case_id)," +
                " FOREIGN KEY (challenge_id) REFERENCES coding_challenge(challenge_id)" +
                " ON DELETE CASCADE)";

        try {

            Statement statement = ConnectionSingle.getConnection().createStatement();
            for (int i = sqlCount - 1; i > -1; i--) {
                statement.executeUpdate("DROP TABLE IF EXISTS " + dropSqls[i]);
            }

            for (int i = 0; i < sqlCount; i++) {
                statement.executeUpdate(createSqls[i]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
