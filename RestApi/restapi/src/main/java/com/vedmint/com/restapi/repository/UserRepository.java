package com.vedmint.com.restapi.repository;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.vedmint.com.restapi.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        users.add(new UserDto(1L, "John Doe", "john.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(2L, "Jane Doe", "jane.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(3L, "Jim Doe", "jim.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(4L, "Jill Doe", "jill.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(5L, "Jack Doe", "jack.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(6L, "Jill Doe", "jill.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(7L, "Jack Doe", "jack.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        users.add(new UserDto(8L, "Jill Doe", "jill.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE"));
        return users;
    }
    public UserDto findById(Long id) {
        return new UserDto(id, "John Doe", "john.doe@example.com", "password", "USER", new Date().toString(), new Date().toString(), "ACTIVE");
    }
}