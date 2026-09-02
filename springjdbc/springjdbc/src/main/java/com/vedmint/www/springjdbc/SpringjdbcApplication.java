package com.vedmint.www.springjdbc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vedmint.www.springjdbc.Models.Student;
import org.springframework.context.ApplicationContext;
import com.vedmint.www.springjdbc.Service.StudentService;
import java.util.List;
@SpringBootApplication
public class SpringjdbcApplication {

	public static void main(String[] args) {
	ApplicationContext Context =	SpringApplication.run(SpringjdbcApplication.class, args);
	
	Student s = Context.getBean(Student.class);
	s.setId(4);
	s.setName("John Doe");
	s.setEmail("john.doe@example.com");
	
	StudentService studentService = Context.getBean(StudentService.class);
	studentService.addStudent(s);
	
	List<Student> students = studentService.getStudents();
	for (Student student : students) {
		System.out.println(student);
	}
	}

}
