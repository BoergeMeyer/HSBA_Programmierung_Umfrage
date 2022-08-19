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

        Answer a1 = new Answer(q1,"Testantwort Nr.1 zu Frage Nr.1");
        Answer a2 = new Answer(q1,"Testantwort Nr.2 zu Frage Nr.1");
        Answer a3 = new Answer(q2,"Testantwort Nr.3 zu Frage Nr.2");
        Answer a4 = new Answer(q2,"Testantwort Nr.4 zu Frage Nr.2");
        surveyService.saveAnswer(a1);
        surveyService.saveAnswer(a2);
        surveyService.saveAnswer(a3);
        surveyService.saveAnswer(a4);


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

        Answer a5 = new Answer(q3,"Weitere Frage 1");
        Answer a6 = new Answer(q3,"Weitere Frage 2");
        Answer a7 = new Answer(q4,"Weitere Frage 3");
        Answer a8 = new Answer(q4,"Weitere Frage 4");
        surveyService.saveAnswer(a5);
        surveyService.saveAnswer(a6);
        surveyService.saveAnswer(a7);
        surveyService.saveAnswer(a8);

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

        Answer a9 = new Answer(q3,"Weitere Frage 5");
        Answer a10 = new Answer(q3,"Weitere Frage 6");
        Answer a11 = new Answer(q4,"Weitere Frage 7");
        Answer a12 = new Answer(q4,"Weitere Frage 8");
        surveyService.saveAnswer(a9);
        surveyService.saveAnswer(a10);
        surveyService.saveAnswer(a11);
        surveyService.saveAnswer(a12);

        //print all
        surveyService.findAllSurvey().forEach(
                survey -> {
                    System.out.println(survey.getTitle());
                    survey.getQuestions().forEach(question -> System.out.println(question.getTitle()));
                    survey.getQuestions().forEach(question ->
                        System.out.println(question.getTitle())
                    );
                }
        );

        //test
        //surveyService.findAllSurvey().get(4).getQuestions().get(5).getAnswers().get(5);


    }



    private User createUser(String name, String password) {
        return userService.save(new User(name, passwordEncoder.encode(password)));
    }
}