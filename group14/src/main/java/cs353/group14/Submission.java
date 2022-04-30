package cs353.group14;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Submission {

    int submission_id;
    String answer;
    int pass_result;
    int fail_result;
    String programming_language;
    Timestamp submission_time;

    public Submission(int submission_id, String answer, int pass_result, int fail_result, String programming_language, Timestamp submission_time) {
        this.submission_id = submission_id;
        this.answer = answer;
        this.pass_result = pass_result;
        this.fail_result = fail_result;
        this.programming_language = programming_language;
        this.submission_time = submission_time;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "submission_id=" + submission_id +
                ", answer='" + answer + '\'' +
                ", pass_result=" + pass_result +
                ", fail_result=" + fail_result +
                ", programming_language='" + programming_language + '\'' +
                ", submission_time=" + submission_time +
                '}';
    }
}
