package de.hsba.bi.Survey.survey;

import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SurveyRepositoryIntegrationTest {

    private final User testUser = new User("test1", "password");

    @Autowired
    private SurveyService serviceToTest;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        surveyRepository.deleteAll();
        userService.save(testUser);
    }

    @Test
    void shouldFindSurveys(){
        Survey s1 = buildSurvey();
        Survey s2 = buildSurvey();

        //when
        List<Survey> surveys = serviceToTest.findAllSurvey();
        //then
        assertThat(surveys).containsExactlyInAnyOrder(s1,s2);

        //when
        surveys = serviceToTest.findSurveyByUsername(testUser.getName());
        //then
        assertThat(surveys).hasSize(2);
    }

    private Survey buildSurvey(){
        Survey survey = new Survey(testUser);
        survey.setTitle("Test");

        return serviceToTest.saveSurvey(survey);
    }

}
