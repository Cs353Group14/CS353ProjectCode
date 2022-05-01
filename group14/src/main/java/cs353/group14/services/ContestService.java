package cs353.group14.services;

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

    public void createContest(int editorId, Timestamp start_time, String description, String title, int difficulty,
                              int duration, Timestamp deadline)
    {
        contestRepository.createContest(editorId,start_time, description, title, difficulty, duration, deadline);

    }

    public void addQuestionsToContest(int contest_id, List<Integer> questions)
    {
        contestRepository.addQuestionToContest(contest_id,questions);
    }
}
