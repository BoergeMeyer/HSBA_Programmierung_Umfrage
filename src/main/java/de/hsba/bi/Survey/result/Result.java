package de.hsba.bi.Survey.result;


import de.hsba.bi.Survey.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Result {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false)
    private Long surveyID;

    @Basic(optional = false)
    private Long questionID;

    @Basic(optional = false)
    private Long selectedAnswerID;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Result (Long surveyID, Long questionID, Long answerID, User user){
        this.surveyID = surveyID;
        this.questionID = questionID;
        this.selectedAnswerID = answerID;
        this.user = user;
    }

}
