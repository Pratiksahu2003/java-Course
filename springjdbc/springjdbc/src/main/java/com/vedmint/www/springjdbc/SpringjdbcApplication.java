package com.vedmint.www.springjdbc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vedmint.www.springjdbc.Models.Student;
import org.springframework.context.ApplicationContext;
import com.vedmint.www.springjdbc.Service.StudentService;
@SpringBootApplication
public class SpringjdbcApplication {

	public static void main(String[] args) {
	ApplicationContext Context =	SpringApplication.run(SpringjdbcApplication.class, args);
	
	Student s = Context.getBean(Student.class);
	s.setId(1);
	s.setName("John Doe");
	s.setEmail("john.doe@example.com");
	
	StudentService studentService = Context.getBean(StudentService.class);
	studentService.addStudent(s);
	}

}
