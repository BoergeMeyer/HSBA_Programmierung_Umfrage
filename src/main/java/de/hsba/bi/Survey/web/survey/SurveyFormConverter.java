package de.hsba.bi.Survey.web.survey;


import de.hsba.bi.Survey.survey.Answer;
import de.hsba.bi.Survey.survey.Question;
import de.hsba.bi.Survey.survey.Survey;
import de.hsba.bi.Survey.user.User;
import org.springframework.stereotype.Component;

@Component
public class SurveyFormConverter {

    Survey create(Survey survey, CreateSurveyForm form, User user){
        survey.setTitle(form.getTitle());
        survey.setCreator(user);
        return survey;
    }

}
