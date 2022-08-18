package de.hsba.bi.Survey.survey;

import javax.transaction.Transactional;
import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


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

        //Umfrage Nr.2
        Survey survey2 = new Survey(erik);
        survey2.setTitle("Umfrage Nr.2");
        surveyService.saveSurvey(survey2);

        //Umfrage Nr.3
        Survey survey3 = new Survey(bennett);
        survey3.setTitle("Umfrage Nr.3");
        surveyService.saveSurvey(survey3);
    }

    private User createUser(String name, String password) {
        return userService.save(new User(name, passwordEncoder.encode(password)));
    }
}