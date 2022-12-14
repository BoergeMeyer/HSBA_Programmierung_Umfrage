package de.hsba.bi.Survey.survey;

import de.hsba.bi.Survey.user.User;
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

    @Setter
    @Getter
    private int is_locked;

    public Survey(final User creator){
        this.creator = creator;
        this.is_locked = 1;
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

}

