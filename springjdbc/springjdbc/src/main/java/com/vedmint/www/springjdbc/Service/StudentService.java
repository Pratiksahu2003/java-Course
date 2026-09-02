package com.vedmint.www.springjdbc.Service;
import org.springframework.stereotype.Service;
import com.vedmint.www.springjdbc.Repository.StudentRepository;
import com.vedmint.www.springjdbc.Models.Student;
import java.util.List;
@Service
public class StudentService {
    
    private StudentRepository studentRepository;
    
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.getAll();
    }
}
