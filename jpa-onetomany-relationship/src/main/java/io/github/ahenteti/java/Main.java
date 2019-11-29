package io.github.ahenteti.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("persistence-unit").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(createCategory("C1"));
        em.persist(createCategory("C2"));
        em.persist(createCategory("C3"));
        transaction.commit();
        System.out.println("number of categories in database: " + getAllCategories(em).size());
        System.out.println("number of questions in database: " + getAllQuestions(em).size());
    }

    private static List<Question> getAllQuestions(EntityManager em) {
        TypedQuery<Question> getAllQuestions = em.createNamedQuery(Question.FIND_ALL, Question.class);
        return getAllQuestions.getResultList();
    }

    private static List<Category> getAllCategories(EntityManager em) {
        TypedQuery<Category> getAllCategories = em.createNamedQuery(Category.FIND_ALL, Category.class);
        return getAllCategories.getResultList();
    }

    private static Category createCategory(String category) {
        Category res = new Category();
        res.setCategoryName(category);
        for (int i = 0; i < 5; i++) {
            res.getQuestions().add(createQuestion(res, category + " - Q" + i));
        }
        return res;
    }

    private static Question createQuestion(Category category, String question) {
        Question q1 = new Question();
        q1.setQuestion(question);
        q1.setCategory(category);
        return q1;
    }

}