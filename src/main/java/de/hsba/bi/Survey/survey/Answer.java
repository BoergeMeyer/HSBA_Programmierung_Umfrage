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

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @Getter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = Question.class)
    private Question question;

    @Setter
    @Getter
    @Basic
    private String title;

    public Answer(Question question, String answer){
        this.question = question;
        this.title = answer;
    }


}

