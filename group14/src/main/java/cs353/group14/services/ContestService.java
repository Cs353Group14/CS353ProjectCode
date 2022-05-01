package cs353.group14.services;

import cs353.group14.Contest;
import cs353.group14.repositories.ContestRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContestService {

    private final ContestRepository contestRepository;

    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public void createContest(int editorId, Contest contest)
    {
        contestRepository.createContest(editorId,contest.getStart_time(), contest.getDescription(),
                contest.getTitle(), contest.getDifficulty(), contest.getDuration(), contest.getDeadline());
    }

    public void addQuestionsToContest(int contest_id, List<Integer> questions)
    {
        contestRepository.addQuestionToContest(contest_id,questions);
    }
}
