package de.hsba.bi.Survey.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository      surveyRepository;
    private final QuestionRepository    questionRepository;
    private final AnswerRepository      answerRepository;

    //methods for survey
    public List<Survey> findAllSurvey(){
        return surveyRepository.findAll();
    }

    public Survey saveSurvey(Survey survey){
        return surveyRepository.save(survey);
    }

    public Survey getSurvey(Long id){
        return surveyRepository.findById(id).orElse(null);
    }

    public void deleteSurvey(Long id){
        surveyRepository.deleteById(id);
    }


    //methods for question

    public List<Question> findAllQuestions(){
        return questionRepository.findAll();
    }

    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    public Question getQuestion(Long id){
        return questionRepository.findById(id).orElse(null);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }

    //https://stackoverflow.com/questions/18852059/java-list-containsobject-with-field-value-equal-to-x
    //https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
}