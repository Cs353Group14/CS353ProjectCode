package cs353.group14.responses;

import cs353.group14.Contest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContestAndOrderPoint {
    Contest contest;
    int order;
    int point;

    public ContestAndOrderPoint(Contest contest, int order, int point) {
        this.contest = contest;
        this.order = order;
        this.point = point;
    }
}
