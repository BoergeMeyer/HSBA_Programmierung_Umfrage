package de.hsba.bi.Survey.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    //methods for survey
    public List<Survey> findAllSurvey() {
        return surveyRepository.findAll();
    }

    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Survey getSurvey(Long id) {
        return surveyRepository.findById(id).orElse(null);
    }

    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    public List<Survey> findCreatorJournals(String search) {
        return surveyRepository.findByUsername(search.trim());
    }
}