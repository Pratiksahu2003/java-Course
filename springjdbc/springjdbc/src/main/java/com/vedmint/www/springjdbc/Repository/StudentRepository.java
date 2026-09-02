package com.vedmint.www.springjdbc.Repository;
import com.vedmint.www.springjdbc.Models.Student;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Repository
public class StudentRepository {
    
    private JdbcTemplate jdbcTemplate;
    
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    
    public void save(Student student) {
        String sql = "INSERT INTO students (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student.getId(), student.getName(), student.getEmail());
    }

    public List<Student> getAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }
}
