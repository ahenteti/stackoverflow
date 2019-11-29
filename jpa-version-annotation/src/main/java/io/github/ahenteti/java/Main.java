package io.github.ahenteti.java;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("persistence-unit").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Template template = createTemplate();
        em.persist(template);
        template.setSubject("update subject");
        transaction.commit();
        System.out.println("done");
    }

    private static Template createTemplate() {
        Template model = new Template();
        model.setSubject("name");
        return model;
    }


}