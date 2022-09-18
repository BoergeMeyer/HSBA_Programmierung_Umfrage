package de.hsba.bi.Survey.survey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
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



