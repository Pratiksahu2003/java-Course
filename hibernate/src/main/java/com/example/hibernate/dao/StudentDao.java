package com.example.hibernate.dao;

import com.example.hibernate.entity.Student;
import com.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class StudentDao {

    public Long save(Student student) {
        return HibernateUtil.getSessionFactory().fromTransaction(session -> {
            session.persist(student);
            return student.getId();
        });
    }

    public Student findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createSelectionQuery(
                            "SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id",
                            Student.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    public List<Student> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createSelectionQuery(
                            "SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses",
                            Student.class)
                    .list();
        }
    }

    public void update(Student student) {
        HibernateUtil.getSessionFactory().inTransaction(session -> session.merge(student));
    }

    public void delete(Long id) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
            }
        });
    }
}
