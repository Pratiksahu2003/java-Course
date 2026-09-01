package com.example.hibernate;

import com.example.hibernate.dao.StudentDao;
import com.example.hibernate.entity.Course;
import com.example.hibernate.entity.Student;
import com.example.hibernate.util.HibernateUtil;

import java.util.List;

/**
 * Demo CRUD using official Hibernate ORM APIs.
 */
public class App {

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        String suffix = String.valueOf(System.currentTimeMillis());

        try {
            System.out.println("========== CREATE ==========");
            Student alice = new Student("Alice", "Sharma", "alice+" + suffix + "@example.com");
            alice.addCourse(new Course("Hibernate Basics", 3));
            alice.addCourse(new Course("JPA Annotations", 2));

            Student bob = new Student("Bob", "Patel", "bob+" + suffix + "@example.com");
            bob.addCourse(new Course("Maven Build Tools", 2));

            Long aliceId = studentDao.save(alice);
            Long bobId = studentDao.save(bob);
            System.out.println("Saved Alice id=" + aliceId);
            System.out.println("Saved Bob id=" + bobId);

            System.out.println("\n========== READ (all) ==========");
            List<Student> students = studentDao.findAll();
            students.forEach(System.out::println);

            System.out.println("\n========== READ (by id) ==========");
            Student found = studentDao.findById(aliceId);
            System.out.println(found);
            if (found != null) {
                found.getCourses().forEach(c -> System.out.println("  -> " + c));
            }

            System.out.println("\n========== UPDATE ==========");
            found.setEmail("alice.updated+" + suffix + "@example.com");
            found.addCourse(new Course("Advanced Mapping", 4));
            studentDao.update(found);
            System.out.println(studentDao.findById(aliceId));

            System.out.println("\n========== DELETE ==========");
            studentDao.delete(bobId);
            System.out.println("Deleted Bob (id=" + bobId + ")");
            System.out.println("Remaining students:");
            studentDao.findAll().forEach(System.out::println);

            System.out.println("\nHibernate demo completed successfully.");
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
