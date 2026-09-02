package com.vedmint.www.springjdbc.Service;
import org.springframework.stereotype.Service;
import com.vedmint.www.springjdbc.Repository.StudentRepository;
import com.vedmint.www.springjdbc.Models.Student;
@Service
public class StudentService {
    
    private StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public void getStudent(int id) {
        studentRepository.getStudent(id);
    }
}
