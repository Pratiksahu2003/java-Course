package com.vedmint.www.springjdbc.Repository;
import com.vedmint.www.springjdbc.Models.Student;
import org.springframework.stereotype.Repository;
@Repository
public class StudentRepository {
    
    public void addStudent(Student student) {
        System.out.println("Student added: " + student);
    }

    public void getStudent(int id) {
        System.out.println("Student retrieved: " + id);
    }
}
