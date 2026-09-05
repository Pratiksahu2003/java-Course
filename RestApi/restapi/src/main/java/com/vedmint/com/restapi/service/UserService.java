package com.vedmint.com.restapi.service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.vedmint.com.restapi.dto.UserDto;
import com.vedmint.com.restapi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll();
    }
    public UserDto getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    
}
