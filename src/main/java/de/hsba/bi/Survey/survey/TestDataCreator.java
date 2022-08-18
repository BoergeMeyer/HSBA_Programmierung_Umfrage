package de.hsba.bi.Survey.survey;

import javax.transaction.Transactional;
import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Transactional
public class TestDataCreator {

    private final UserService userService;
    private final SurveyService surveyService;
    private final PasswordEncoder passwordEncoder;
    private final SurveyRepository surveyRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void init() {
        if (!userService.findAll().isEmpty()) {
            // prevent initialization if DB is not empty
            return;
        }

        // add some users
        User börge = createUser("Börge", "123456");
        User erik = createUser("Erik", "123456");
        User bennett = createUser("Bennett", "123456");

        //Umfrage Nr.1
        Survey survey1 = new Survey(börge);
        survey1.setTitle("Umfrage Nr.1");
        surveyService.saveSurvey(survey1);

        Question q1 = new Question(survey1,"Testfrage Nr.1");
        Question q2 = new Question(survey1,"Testfrage Nr.2");
        surveyService.saveQuestion(q1);
        surveyService.saveQuestion(q2);
        surveyService.getSurvey(survey1.getId()).addQuestion(q1);
        surveyService.getSurvey(survey1.getId()).addQuestion(q2);
        System.out.println(survey1.getId());

        /*
        JUST IDEAS:
        surveyService.getSurvey(survey1.getId()).getQuestions().get(1).getTitle();
        surveyService.getSurvey(survey1.getId()).getQuestions().forEach(question -> question.getTitle());
        */

        //Umfrage Nr.2
        Survey survey2 = new Survey(erik);
        survey2.setTitle("Umfrage Nr.2");
        surveyService.saveSurvey(survey2);

        Question q3 = new Question(survey1,"Testfrage Nr.3");
        Question q4 = new Question(survey1,"Testfrage Nr.4");
        surveyService.saveQuestion(q3);
        surveyService.saveQuestion(q4);
        surveyService.getSurvey(survey2.getId()).addQuestion(q3);
        surveyService.getSurvey(survey2.getId()).addQuestion(q4);
        System.out.println(survey2.getId());

        //Umfrage Nr.3
        Survey survey3 = new Survey(bennett);
        survey3.setTitle("Umfrage Nr.3");
        surveyService.saveSurvey(survey3);

        Question q5 = new Question(survey1,"Testfrage Nr.5");
        Question q6 = new Question(survey1,"Testfrage Nr.6");
        surveyService.saveQuestion(q5);
        surveyService.saveQuestion(q6);
        surveyService.getSurvey(survey3.getId()).addQuestion(q5);
        surveyService.getSurvey(survey3.getId()).addQuestion(q6);
        System.out.println(survey3.getId());

        surveyService.findAllSurvey().forEach(
                survey -> {
                    System.out.println(survey.getTitle());
                    survey.getQuestions().forEach(question -> System.out.println(question.getTitle()));
                }
        );
    }



    private User createUser(String name, String password) {
        return userService.save(new User(name, passwordEncoder.encode(password)));
    }
}