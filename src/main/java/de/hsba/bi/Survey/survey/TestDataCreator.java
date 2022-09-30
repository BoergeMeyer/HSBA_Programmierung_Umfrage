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
        User börge = createUser("BörgeMeyer", "1234567890");
        User erik = createUser("ErikMehling", "1234567890");
        User bennett = createUser("BennettBrockmann", "1234567890");

        userService.save(börge);
        userService.save(erik);
        userService.save(bennett);

        System.out.println("Folgende Benutzer wurden erstellt:");
        userService.findAll().forEach(
                user -> System.out.println(user.getName())
        );

        //Umfrage zu Markenbekanntheit für Benutzer "BörgeMeyer"
        Survey markenSurvey = new Survey(börge);
        markenSurvey.setIs_locked(0);
        markenSurvey.setTitle("Umfrage zur Markenbekanntheit von Apple");
        markenSurvey.setDescription("Das ist eine Umfrage zur Markenbekanntheit von Apple und der angebotenen Produkte.");
        surveyService.saveSurvey(markenSurvey);

        Question mSQ1   = new Question(markenSurvey,"Wann haben Sie zuletzt ein Produkt des Unternehmens gekauft?");
        Answer mSA1     = new Answer(mSQ1,"Innerhalb der letzten 3 Monaten");
        Answer mSA2     = new Answer(mSQ1, "Innerhalb der letzten 3 bis 6 Monaten");
        Answer mSA3     = new Answer(mSQ1, "Innerhalb der letzten 6 bis 12 Monaten");
        Answer mSA4     = new Answer(mSQ1,"Mein letzter Kauf ist länger als 12 Monate her");

        Question mSQ2   = new Question(markenSurvey,"Von welcher Marken sind Sie am meisten überzeugt?");
        Answer mSA5     = new Answer(mSQ2,"Apple");
        Answer mSA6     = new Answer(mSQ2,"Google");
        Answer mSA7     = new Answer(mSQ2,"Samsung");
        Answer mSA8     = new Answer(mSQ2,"Huawei");

        Question mSQ3   = new Question(markenSurvey,"Welches Produkt haben Sie von Apple zuletzt erworben?");
        Answer mSA9     = new Answer(mSQ2,"iPhone");
        Answer mSA10    = new Answer(mSQ2,"Apple Watch");
        Answer mSA11    = new Answer(mSQ2,"iPad");
        Answer mSA12    = new Answer(mSQ2,"iMac oder Macbook");
        Answer mSA13    = new Answer(mSQ2,"sonstiges");

        Question mSQ4   = new Question(markenSurvey,"Wie viel haben Sie dieses Jahr für die Produkte bezahlt?");
        Answer mSA14    = new Answer(mSQ2,"weniger als 500€");
        Answer mSA15    = new Answer(mSQ2,"zwischen 501€ und 1000€");
        Answer mSA16    = new Answer(mSQ2,"zwischen 1001€ und 2000€");
        Answer mSA17    = new Answer(mSQ2,"mehr als 2000€");

        surveyService.saveQuestion(mSQ1);
        surveyService.saveQuestion(mSQ2);
        surveyService.saveQuestion(mSQ3);
        surveyService.saveQuestion(mSQ4);
        markenSurvey.addQuestion(mSQ1);
        markenSurvey.addQuestion(mSQ2);
        markenSurvey.addQuestion(mSQ3);
        markenSurvey.addQuestion(mSQ4);

        surveyService.saveAnswer(mSA1);
        surveyService.saveAnswer(mSA2);
        surveyService.saveAnswer(mSA3);
        surveyService.saveAnswer(mSA4);
        surveyService.saveAnswer(mSA5);
        surveyService.saveAnswer(mSA6);
        surveyService.saveAnswer(mSA7);
        surveyService.saveAnswer(mSA8);
        surveyService.saveAnswer(mSA9);
        surveyService.saveAnswer(mSA10);
        surveyService.saveAnswer(mSA11);
        surveyService.saveAnswer(mSA12);
        surveyService.saveAnswer(mSA13);
        surveyService.saveAnswer(mSA14);
        surveyService.saveAnswer(mSA15);
        surveyService.saveAnswer(mSA16);
        surveyService.saveAnswer(mSA17);
        mSQ1.addAnswer(mSA1);
        mSQ1.addAnswer(mSA2);
        mSQ1.addAnswer(mSA3);
        mSQ1.addAnswer(mSA4);
        mSQ2.addAnswer(mSA5);
        mSQ2.addAnswer(mSA6);
        mSQ2.addAnswer(mSA7);
        mSQ2.addAnswer(mSA8);
        mSQ3.addAnswer(mSA9);
        mSQ3.addAnswer(mSA10);
        mSQ3.addAnswer(mSA11);
        mSQ3.addAnswer(mSA12);
        mSQ3.addAnswer(mSA13);
        mSQ4.addAnswer(mSA14);
        mSQ4.addAnswer(mSA15);
        mSQ4.addAnswer(mSA16);
        mSQ4.addAnswer(mSA17);

        //Umfrage zur Appbenutzung für Benutzer "BörgeMeyer"
        Survey appSurvey = new Survey(börge);
        appSurvey.setIs_locked(0);
        appSurvey.setTitle("Umfrage zur App-Benutzung");
        appSurvey.setDescription("Diese Umfrage beschäftigt sich mit der Verwendung von Apps, um das Benutzerverhalten zu analysieren.");
        surveyService.saveSurvey(appSurvey);

        Question aSQ1   = new Question(appSurvey,"Wie viele Apps haben Sie aktuell installiert");
        Answer aSA1     = new Answer(mSQ1,"0 bis 20");
        Answer aSA2     = new Answer(mSQ1, "21 bis 30");
        Answer aSA3     = new Answer(mSQ1, "31 bis 40");
        Answer aSA4     = new Answer(mSQ1,"mehr als 40");

        Question aSQ2   = new Question(appSurvey,"Welches Betriebssystem verwenden Sie auf ihrem Smartphone?");
        Answer aSA5     = new Answer(aSQ2,"iOS");
        Answer aSA6     = new Answer(aSQ2,"Android");
        Answer aSA7     = new Answer(aSQ2,"Windows");
        Answer aSA8     = new Answer(aSQ2,"Sonstiges");

        Question aSQ3   = new Question(appSurvey,"Welche Art von Apps verwendet Sie am meisten?");
        Answer aSA9     = new Answer(aSQ2,"Neuigkeiten");
        Answer aSA10    = new Answer(aSQ2,"Unterhaltung");
        Answer aSA11    = new Answer(aSQ2,"Soziale Netzwerke");
        Answer aSA12    = new Answer(aSQ2,"Reiseapps");
        Answer aSA13    = new Answer(aSQ2,"Sonsitges");

        Question aSQ4   = new Question(appSurvey,"Wie lange nutzen Sie ihr Smartphone pro Tag?");
        Answer aSA14    = new Answer(aSQ2,"< 1 Stunde");
        Answer aSA15    = new Answer(aSQ2,"1 bis 2 Stunden");
        Answer aSA16    = new Answer(aSQ2,"2 bis 4 Stunden");
        Answer aSA17    = new Answer(aSQ2,"4 bis 6 Stunden");
        Answer aSA18    = new Answer(aSQ2,"6 bis 8 Stunden");
        Answer aSA19    = new Answer(aSQ2,"> 8 Stunden");

        surveyService.saveQuestion(aSQ1);
        surveyService.saveQuestion(aSQ2);
        surveyService.saveQuestion(aSQ3);
        surveyService.saveQuestion(aSQ4);
        appSurvey.addQuestion(aSQ1);
        appSurvey.addQuestion(aSQ2);
        appSurvey.addQuestion(aSQ3);
        appSurvey.addQuestion(aSQ4);

        surveyService.saveAnswer(aSA1);
        surveyService.saveAnswer(aSA2);
        surveyService.saveAnswer(aSA3);
        surveyService.saveAnswer(aSA4);
        surveyService.saveAnswer(aSA5);
        surveyService.saveAnswer(aSA6);
        surveyService.saveAnswer(aSA7);
        surveyService.saveAnswer(aSA8);
        surveyService.saveAnswer(aSA9);
        surveyService.saveAnswer(aSA10);
        surveyService.saveAnswer(aSA11);
        surveyService.saveAnswer(aSA12);
        surveyService.saveAnswer(aSA13);
        surveyService.saveAnswer(aSA14);
        surveyService.saveAnswer(aSA15);
        surveyService.saveAnswer(aSA16);
        surveyService.saveAnswer(aSA17);
        surveyService.saveAnswer(aSA18);
        surveyService.saveAnswer(aSA19);
        aSQ1.addAnswer(aSA1);
        aSQ1.addAnswer(aSA2);
        aSQ1.addAnswer(aSA3);
        aSQ1.addAnswer(aSA4);
        aSQ2.addAnswer(aSA5);
        aSQ2.addAnswer(aSA6);
        aSQ2.addAnswer(aSA7);
        aSQ2.addAnswer(aSA8);
        aSQ3.addAnswer(aSA9);
        aSQ3.addAnswer(aSA10);
        aSQ3.addAnswer(aSA11);
        aSQ3.addAnswer(aSA12);
        aSQ3.addAnswer(aSA13);
        aSQ4.addAnswer(aSA14);
        aSQ4.addAnswer(aSA15);
        aSQ4.addAnswer(aSA16);
        aSQ4.addAnswer(aSA17);
        aSQ4.addAnswer(aSA18);
        aSQ4.addAnswer(aSA19);



        //Umfrage Nr.2
        Survey survey2 = new Survey(erik);
        survey2.setTitle("Umfrage Nr.2");
        survey2.setIs_locked(0);
        surveyService.saveSurvey(survey2);

        Question q3 = new Question(survey2,"Testfrage Nr.3");
        Question q4 = new Question(survey2,"Testfrage Nr.4");
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

        Question q5 = new Question(survey3,"Testfrage Nr.5");
        Question q6 = new Question(survey3,"Testfrage Nr.6");
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

        surveyService.findSurveyByUsername("Börge").forEach(
                survey -> System.out.println(survey.getId())
        );
    }



    private User createUser(String name, String password) {
        return userService.save(new User(name, passwordEncoder.encode(password)));
    }
}