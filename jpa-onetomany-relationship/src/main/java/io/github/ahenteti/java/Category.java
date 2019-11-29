package io.github.ahenteti.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Category")
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({@NamedQuery(name = Category.FIND_ALL, query = "SELECT c FROM Category c")})
public class Category {

    public static final String FIND_ALL = "Category.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CID")
    private Long categoryId;

    @Column(name = "CNAME")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    public List<Question> questions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return categoryId == category.categoryId && getCategoryName().equals(category.getCategoryName()) && questions
                .equals(category.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryName());
    }
}
