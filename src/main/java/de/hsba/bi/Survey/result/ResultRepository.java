package de.hsba.bi.Survey.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query("SELECT r FROM Result r WHERE r.surveyID = :id")
    List<Result> findAllResultBySurveyID(@RequestParam("id") Long id);

    @Query("SELECT r FROM Result r WHERE r.questionID = :id")
    List<Result> findAllResultByQuestionID(@RequestParam("id") Long id);

    @Query("SELECT r FROM Result r WHERE r.selectedAnswerID = :id")
    List<Result> findAllResultByAnswerID(@RequestParam("id") Long id);

   @Query("SELECT COUNT(r.selectedAnswerID) AS COUNT_AID FROM Result r WHERE r.selectedAnswerID = :aid")
    Integer countAnswerID(@RequestParam("aid") Long aid);

    @Query("SELECT COUNT(r.questionID) AS COUNT_QID FROM Result r WHERE r.questionID = :qid ")
    Integer countQuestionID(@RequestParam("qid") Long qid);

    @Query("SELECT DISTINCT r.user, r.surveyID FROM Result r")
    Integer countResult();
}
