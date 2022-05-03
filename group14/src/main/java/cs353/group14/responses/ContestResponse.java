package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ContestResponse {
    int contest_id;
    Timestamp start_time;
    String description;
    String title;
    int difficulty;
    int duration;
    Timestamp deadline;
    int points;

    public ContestResponse(int contest_id, Timestamp start_time, String description, String title, int difficulty, int duration, Timestamp deadline, int points) {
        this.contest_id = contest_id;
        this.start_time = start_time;
        this.description = description;
        this.title = title;
        this.difficulty = difficulty;
        this.duration = duration;
        this.deadline = deadline;
        this.points = points;
    }
}
