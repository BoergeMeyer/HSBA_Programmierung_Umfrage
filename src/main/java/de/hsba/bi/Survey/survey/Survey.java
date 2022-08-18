package de.hsba.bi.Survey.survey;

import de.hsba.bi.Survey.survey.Question;
import de.hsba.bi.Survey.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Survey {
    @Id
    @Getter
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @ManyToOne(optional = false)
    private User creator;

    @Setter
    @Getter
    @Basic(optional = false)
    private String title;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    @OrderBy
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(targetEntity = Question.class)
    private List<Question> questions;

    public Survey(final User creator){
        this.creator = creator;
    }

    public boolean isOwnedByCurrentUser() {
        return this.creator != null && this.creator.getName().equals(User.getCurrentUsername());
    }

    public void addQuestion(Question question){
        if(questions == null){
            questions = new ArrayList<>();
            questions.add(question);
        }else{
            questions.add(question);
        }
    }

    public List<String> returnQuestionTitle() {
        List<String> titles = new ArrayList<>();
        if(getQuestions() == null){
            return null;
        }else{
            for (Question question : getQuestions()) {
                titles.add(question.getTitle());
            }
        }
        return titles;
    }
}

