package io.github.ahenteti.java;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Question")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({@NamedQuery(name = Question.FIND_ALL, query = "SELECT q FROM Question q")})
public class Question {

    public static final String FIND_ALL = "Question.findAll";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QID")
    private Long id;
    @Column(name = "QText")
    private String question;
    @ManyToOne()
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return getId() == question.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}