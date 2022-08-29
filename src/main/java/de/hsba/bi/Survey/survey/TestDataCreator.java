package de.hsba.bi.Survey.survey;

import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


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
        User börge = createUser("Börge", "1234567890");
        User erik = createUser("Erik", "1234567890");
        User bennett = createUser("Bennett", "1234567890");
        User egröb = createUser("Egröb", "1234567890");

        userService.save(börge);
        userService.save(erik);
        userService.save(bennett);
        userService.save(egröb);

        userService.findAll().forEach(
                user -> System.out.println(user.getId() + " " + user.getName())
        );

        //Umfrage Nr.1
        Survey survey1 = new Survey(börge);
        survey1.setTitle("Umfrage Nr.1");
        survey1.setDescription("Optionale Testbeschreibung");
        surveyService.saveSurvey(survey1);

        Question q1 = new Question(survey1,"Testfrage Nr.1");
        Question q2 = new Question(survey1,"Testfrage Nr.2");
        surveyService.saveQuestion(q1);
        surveyService.saveQuestion(q2);
        surveyService.getSurvey(survey1.getId()).addQuestion(q1);
        surveyService.getSurvey(survey1.getId()).addQuestion(q2);

        Answer a1 = new Answer(q1,"Antwort 1");
        Answer a2 = new Answer(q1,"Antwort 2");
        Answer a3 = new Answer(q2,"Antwort 3");
        Answer a4 = new Answer(q2,"Antwort 4");
        surveyService.saveAnswer(a1);
        surveyService.saveAnswer(a2);
        surveyService.saveAnswer(a3);
        surveyService.saveAnswer(a4);
        surveyService.getQuestion(q1.getId()).addAnswer(a1);
        surveyService.getQuestion(q1.getId()).addAnswer(a2);
        surveyService.getQuestion(q2.getId()).addAnswer(a3);
        surveyService.getQuestion(q2.getId()).addAnswer(a4);


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

        Answer a5 = new Answer(q3,"Antwort 5");
        Answer a6 = new Answer(q3,"Antwort 6");
        Answer a7 = new Answer(q4,"Antwort 7");
        Answer a8 = new Answer(q4,"Antwort 8");
        surveyService.saveAnswer(a5);
        surveyService.saveAnswer(a6);
        surveyService.saveAnswer(a7);
        surveyService.saveAnswer(a8);
        surveyService.getQuestion(q3.getId()).addAnswer(a5);
        surveyService.getQuestion(q3.getId()).addAnswer(a6);
        surveyService.getQuestion(q4.getId()).addAnswer(a7);
        surveyService.getQuestion(q4.getId()).addAnswer(a8);


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

        Answer a9 = new Answer(q3,"Antwort 9");
        Answer a10 = new Answer(q3,"Antwort 10");
        Answer a11 = new Answer(q4,"Antwort 11");
        Answer a12 = new Answer(q4,"Antwort 12");
        surveyService.saveAnswer(a9);
        surveyService.saveAnswer(a10);
        surveyService.saveAnswer(a11);
        surveyService.saveAnswer(a12);
        surveyService.getQuestion(q5.getId()).addAnswer(a9);
        surveyService.getQuestion(q5.getId()).addAnswer(a10);
        surveyService.getQuestion(q6.getId()).addAnswer(a11);
        surveyService.getQuestion(q6.getId()).addAnswer(a12);
    }



    private User createUser(String name, String password) {
        return userService.save(new User(name, passwordEncoder.encode(password)));
    }
}