package cs353.group14;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ContestAuthor {
    int contest_id;
    Timestamp start_time;
    String description;
    String title;
    int difficulty;
    int duration;
    Timestamp deadline;
    String author;

    public ContestAuthor(int contest_id, Timestamp start_time, String description, String title, int difficulty, int duration, Timestamp deadline, String author) {
        this.contest_id = contest_id;
        this.start_time = start_time;
        this.description = description;
        this.title = title;
        this.difficulty = difficulty;
        this.duration = duration;
        this.deadline = deadline;
        this.author = author;
    }






}
