package de.hsba.bi.Survey.survey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.core.annotation.Order;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false, targetEntity = Survey.class)
    private Survey survey;

    @Setter
    @Getter
    @Basic(optional = false)
    private String title;

    @Setter
    @Getter
    @OrderBy
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(targetEntity = Answer.class)
    private List<Answer> answers;

    public Question(Survey survey, String question){
        this.survey = survey;
        this.title = question;

        this.getAnswers();
    }

    public List<Answer> getAnswers(){
        return this.answers;
    }


    public void addAnswer(Answer answer){
        if(answers == null){
            answers = new ArrayList<>();
            answers.add(answer);
        }else{
            answers.add(answer);
        }
    }




}



