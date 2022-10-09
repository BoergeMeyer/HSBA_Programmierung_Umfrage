package de.hsba.bi.Survey.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT COUNT(r.selectedAnswerID) AS COUNT_AID FROM Result r WHERE r.selectedAnswerID = :aid")
    Integer countAnswerID(@RequestParam("aid") Long aid);

    @Query("SELECT COUNT(r.questionID) AS COUNT_QID FROM Result r WHERE r.questionID = :qid ")
    Integer countQuestionID(@RequestParam("qid") Long qid);

    @Query("SELECT 1 FROM Result r WHERE r.user.name = :username AND r.surveyID = :surveyID")
    Integer getVoteForSurveyAndUser(@RequestParam("username") String username, @RequestParam("surveyID") Long surveyID);
}
