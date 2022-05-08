package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContestDeadlineResponse {
    int contest_id;
    String title;
    boolean isDeadlinePassed;
    String editorName;

    public ContestDeadlineResponse(int contest_id, String title, boolean isDeadlinePassed, String editorName) {
        this.contest_id = contest_id;
        this.title = title;
        this.isDeadlinePassed = isDeadlinePassed;
        this.editorName = editorName;
    }
}
