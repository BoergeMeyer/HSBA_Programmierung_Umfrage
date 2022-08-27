package de.hsba.bi.Survey.survey;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select distinct s from Survey s join s.questions q where s.title like %:search%")
    List<Survey> findByEntryDescription(@Param("search") String search);

    @Query("select s from Survey s where s.creator.name like %:search%")
    List<Survey> findByUsername(@Param("search") String search);
}
