package de.hsba.bi.Survey.survey;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    //methods for answer

    public List<Answer> findAllAnswers(){
        return answerRepository.findAll();
    }

    public Answer saveAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    public Answer getAnswer(Long id){
        return answerRepository.findById(id).orElse(null);
    }

    public void deleteAnswer(Long id){
        answerRepository.deleteById(id);
    }

    public String returnNumberOfSurveys(){
        return Integer.toString(surveyRepository.findAll().size());
    }

    public List<Survey>findSurveyByUserId(Long id){
        return surveyRepository.findSurveyByUserId(id);
    }

    public List<Survey>findSurveyByUsername(String username){
        return surveyRepository.findSurveyByUsername(username);
    }

    public List<Survey>findSurveyByIdAndUsername(String username, Long id){
        return surveyRepository.findSurveyByIdAndUsername(id, username);
    }

    public List<Survey>findSurveyById(Long id){
        return surveyRepository.findSurveyById(id);
    }

    public List<Survey>findSurveyByAnswerId(Long id){
        return surveyRepository.findSurveyByAnswerId(id);
    }

    public List<Question>findQuestionByAnswerId(Long id){
        return surveyRepository.findQuestionByAnswerId(id);
    }
}