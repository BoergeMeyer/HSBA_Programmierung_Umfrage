package de.hsba.bi.Survey.survey;

import de.hsba.bi.Survey.result.Result;
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

        //Umfrage zu Tieren für Benutzer "ErikMehling"


        Survey tierSurvey = new Survey(erik);
        tierSurvey.setTitle("Umfrage zu Tieren");
        tierSurvey.setIs_locked(1);
        surveyService.saveSurvey(tierSurvey);

        Question tSQ1 = new Question(tierSurvey,"Welche Merkmal oder Besonderheit hat kein Tier:");
        Answer tSA1 = new Answer(tSQ1,"Tiere benötigen zur Energiegewinnung Photosynthese.");
        Answer tSA2 = new Answer(tSQ1,"Tiere brauchen zum Atmen Sauerstoff.");
        Answer tSA3 = new Answer(tSQ1, "Tiere haben ein zentrales Nervensystem.");
        Answer tSA4 = new Answer(tSQ1, "Tiere gewinnen Energie durch die Aufnahme von Nahrung");

        Question tSQ2 = new Question(tierSurvey,"Welches Säugetier magst du lieber?");
        Answer tSA5 = new Answer(tSQ2, "Wal");
        Answer tSA6 = new Answer(tSQ2,"Delfin");

        Question tSQ3 = new Question(tierSurvey,"Welchen Vogel magst du lieber?");
        Answer tSA7 = new Answer(tSQ3,"Papagei");
        Answer tSA8 = new Answer(tSQ3,"Wellensittich");

        surveyService.saveQuestion(tSQ1);
        surveyService.saveQuestion(tSQ2);
        surveyService.saveQuestion(tSQ3);
        tierSurvey.addQuestion(tSQ1);
        tierSurvey.addQuestion(tSQ2);
        tierSurvey.addQuestion(tSQ3);

        surveyService.saveAnswer(tSA1);
        surveyService.saveAnswer(tSA2);
        surveyService.saveAnswer(tSA3);
        surveyService.saveAnswer(tSA4);
        surveyService.saveAnswer(tSA5);
        surveyService.saveAnswer(tSA6);
        surveyService.saveAnswer(tSA7);
        surveyService.saveAnswer(tSA8);
        tSQ1.addAnswer(tSA1);
        tSQ1.addAnswer(tSA2);
        tSQ1.addAnswer(tSA3);
        tSQ1.addAnswer(tSA4);
        tSQ2.addAnswer(tSA5);
        tSQ2.addAnswer(tSA6);
        tSQ3.addAnswer(tSA7);
        tSQ3.addAnswer(tSA8);

        //Umfrage zu Smartphonemarken für Benutzer "ErikMehling"


        Survey technicSurvey = new Survey(erik);
        technicSurvey.setTitle("Umfrage zu Smartphonemarken");
        technicSurvey.setIs_locked(0);
        surveyService.saveSurvey(technicSurvey);

        Question teSQ1 = new Question(technicSurvey,"Welche Smartphonemarke magst du am meisten?");
        Answer teSA1 = new Answer(tSQ1,"Apple");
        Answer teSA2 = new Answer(tSQ1,"Google");
        Answer teSA3 = new Answer(tSQ1, "Samsung");
        Answer teSA4 = new Answer(tSQ1, "Sonstige");

        Question teSQ2 = new Question(technicSurvey,"Welches Smartphone gefällt die mehr von Apple?");
        Answer teSA5 = new Answer(tSQ2, "iPhone 14");
        Answer teSA6 = new Answer(tSQ2,"iPhone 13");

        Question teSQ3 = new Question(technicSurvey,"Welches Smartphone gefällt die mehr von Google?");
        Answer teSA7 = new Answer(tSQ3,"Google Pixel 7");
        Answer teSA8 = new Answer(tSQ3,"Google Pixel 6");

        Question teSQ4 = new Question(technicSurvey,"Welches Smartphone gefällt die mehr von Samsung?");
        Answer teSA9 = new Answer(tSQ3,"Samsung Galaxy S22");
        Answer teSA10 = new Answer(tSQ3,"Samsung Galaxy S21");

        surveyService.saveQuestion(teSQ1);
        surveyService.saveQuestion(teSQ2);
        surveyService.saveQuestion(teSQ3);
        surveyService.saveQuestion(teSQ4);
        technicSurvey.addQuestion(teSQ1);
        technicSurvey.addQuestion(teSQ2);
        technicSurvey.addQuestion(teSQ3);
        technicSurvey.addQuestion(teSQ4);

        surveyService.saveAnswer(teSA1);
        surveyService.saveAnswer(teSA2);
        surveyService.saveAnswer(teSA3);
        surveyService.saveAnswer(teSA4);
        surveyService.saveAnswer(teSA5);
        surveyService.saveAnswer(teSA6);
        surveyService.saveAnswer(teSA7);
        surveyService.saveAnswer(teSA8);
        surveyService.saveAnswer(teSA9);
        surveyService.saveAnswer(teSA10);
        teSQ1.addAnswer(teSA1);
        teSQ1.addAnswer(teSA2);
        teSQ1.addAnswer(teSA3);
        teSQ1.addAnswer(teSA4);
        teSQ2.addAnswer(teSA5);
        teSQ2.addAnswer(teSA6);
        teSQ3.addAnswer(teSA7);
        teSQ3.addAnswer(teSA8);
        teSQ4.addAnswer(teSA9);
        teSQ4.addAnswer(teSA10);

        //Umfrage zu Schulfächern für Benutzer "ErikMehling"

        Survey subjectSurvey = new Survey(erik);
        subjectSurvey.setTitle("Umfrage zu Smartphonemarken");
        subjectSurvey.setIs_locked(0);
        surveyService.saveSurvey(subjectSurvey);

        Question sSQ1 = new Question(subjectSurvey,"Welchen Bereich preferierst du?");
        Answer sSA1 = new Answer(sSQ1,"Naturwissenschaften");
        Answer sSA2 = new Answer(sSQ1,"Kultur");
        Answer sSA3 = new Answer(sSQ1, "Sprachen");
        Answer sSA4 = new Answer(sSQ1, "Sonstige");

        Question sSQ2 = new Question(subjectSurvey,"Welches Fach magst du am meisten aus der Naturwissenschaft?");
        Answer sSA5 = new Answer(sSQ2, "Mathe");
        Answer sSA6 = new Answer(sSQ2,"Biologie");
        Answer sSA7 = new Answer(sSQ2,"Chemie");
        Answer sSA8 = new Answer(sSQ2,"Physik");

        Question sSQ3 = new Question(subjectSurvey,"Welche Sprache magst du am meiesten?");
        Answer sSA9 = new Answer(sSQ3,"Deutsch");
        Answer sSA10 = new Answer(sSQ3,"Englisch");
        Answer sSA11 = new Answer(sSQ3,"Spanisch");
        Answer sSA12 = new Answer(sSQ3,"Französisch");

        surveyService.saveQuestion(sSQ1);
        surveyService.saveQuestion(sSQ2);
        surveyService.saveQuestion(sSQ3);
        subjectSurvey.addQuestion(sSQ1);
        subjectSurvey.addQuestion(sSQ2);
        subjectSurvey.addQuestion(sSQ3);

        surveyService.saveAnswer(sSA1);
        surveyService.saveAnswer(sSA2);
        surveyService.saveAnswer(sSA3);
        surveyService.saveAnswer(sSA4);
        surveyService.saveAnswer(sSA5);
        surveyService.saveAnswer(sSA6);
        surveyService.saveAnswer(sSA7);
        surveyService.saveAnswer(sSA8);
        surveyService.saveAnswer(sSA9);
        surveyService.saveAnswer(sSA10);
        surveyService.saveAnswer(sSA11);
        surveyService.saveAnswer(sSA12);
        sSQ1.addAnswer(sSA1);
        sSQ1.addAnswer(sSA2);
        sSQ1.addAnswer(sSA3);
        sSQ1.addAnswer(sSA4);
        sSQ2.addAnswer(sSA5);
        sSQ2.addAnswer(sSA6);
        sSQ2.addAnswer(sSA7);
        sSQ2.addAnswer(sSA8);
        sSQ3.addAnswer(sSA9);
        sSQ3.addAnswer(sSA10);
        sSQ3.addAnswer(sSA11);
        sSQ3.addAnswer(sSA12);

        //Umfrage zu Essen für Benutzer "BennetBrockmann"
        Survey foodSurvey = new Survey(bennett);
        foodSurvey.setTitle("Umfrage zu Obst & Gemüse");
        foodSurvey.setIs_locked(0);
        surveyService.saveSurvey(foodSurvey);

        Question fSQ1 = new Question(foodSurvey,"Welche Definition passt besser zu 'Obst'?");
        Answer fSA1 = new Answer(fSQ1,"Obst wächst an Bäumen & Sträuchern und ist meist wasserhaltig.");
        Answer fSA2 = new Answer(fSQ1,"Obst wird ausgegraben und beinhaltet wenig Wasser.");

        Question fSQ2 = new Question(foodSurvey,"Welche Definition passt besser zu 'Gemüse'?");
        Answer fSA3 = new Answer(fSQ2,"Gemüse wächst an Bäumen & Sträuchern und ist meist wasserhaltig.");
        Answer fSA4 = new Answer(fSQ2,"Gemüse ist ein Sammelbegriff für Essen von wild wachsender oder in Kultur angebauter Pflanzen.");

        Question fSQ3 = new Question(foodSurvey,"Welche Obstsorte magst du am meisten?");
        Answer fSA5 = new Answer(fSQ3, "Banane");
        Answer fSA6 = new Answer(fSQ3,"Apfel");

        Question fSQ4 = new Question(foodSurvey,"Welche Gemüsesorte magst du am meisten?");
        Answer fSA7 = new Answer(fSQ4,"Kartoffeln");
        Answer fSA8 = new Answer(fSQ4,"Gurken");

        surveyService.saveQuestion(fSQ1);
        surveyService.saveQuestion(fSQ2);
        surveyService.saveQuestion(fSQ3);
        surveyService.saveQuestion(fSQ4);
        foodSurvey.addQuestion(fSQ1);
        foodSurvey.addQuestion(fSQ2);
        foodSurvey.addQuestion(fSQ3);
        foodSurvey.addQuestion(fSQ4);

        surveyService.saveAnswer(fSA1);
        surveyService.saveAnswer(fSA2);
        surveyService.saveAnswer(fSA3);
        surveyService.saveAnswer(fSA4);
        surveyService.saveAnswer(fSA5);
        surveyService.saveAnswer(fSA6);
        surveyService.saveAnswer(fSA7);
        surveyService.saveAnswer(fSA8);
        fSQ1.addAnswer(fSA1);
        fSQ1.addAnswer(fSA2);
        fSQ2.addAnswer(fSA3);
        fSQ2.addAnswer(fSA4);
        fSQ3.addAnswer(fSA5);
        fSQ3.addAnswer(fSA6);
        fSQ4.addAnswer(fSA7);
        fSQ4.addAnswer(fSA8);

    }

    private User createUser(String name, String password) {
        return userService.save(new User(name, passwordEncoder.encode(password)));
    }
}