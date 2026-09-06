package com.vedmint.reat.Service;

import org.springframework.stereotype.Service;
import com.vedmint.reat.Repository.UserRepository;
import com.vedmint.reat.Dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;

import com.vedmint.reat.Dto.CreateUserDto;
import com.vedmint.reat.Exception.DuplicateResourceException;
import com.vedmint.reat.Exception.ResourceNotFoundException;
import com.vedmint.reat.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        if (userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + createUserDto.getEmail());
        }

        User user = createUserDto.toEntity();
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!user.getEmail().equalsIgnoreCase(userDto.getEmail())
                && userRepository.existsByEmail(userDto.getEmail())) {
            throw new DuplicateResourceException("Email already exists: " + userDto.getEmail());
        }

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return UserDto.fromEntity(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(UserDto::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserDto activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.activate();
        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.deactivate();
        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto softDeleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.softDelete();
        return UserDto.fromEntity(userRepository.save(user));
    }
    
   
}
