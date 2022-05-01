package cs353.group14.requests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UpdateAttendRequest {
    int interviewId;
    String interviewResult;

}
