package cs353.group14.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContestStatisticResponse {
    int maxPoint;
    int minPoint;
    int avgPoint;
    int userNumber;

    public ContestStatisticResponse(int maxPoint, int minPoint, int avgPoint, int userNumber) {
        this.maxPoint = maxPoint;
        this.minPoint = minPoint;
        this.avgPoint = avgPoint;
        this.userNumber = userNumber;
    }
}
