package com.vedmint.reat.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.vedmint.reat.Service.UserService;
import com.vedmint.reat.Response.ApiResponse;
import com.vedmint.reat.Dto.UserDto;
import com.vedmint.reat.Dto.CreateUserDto;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(
                ApiResponse.success("Users retrieved successfully", userService.getAllUsers()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED.value(), "User created successfully",
                        userService.createUser(createUserDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("User retrieved successfully", userService.getUserById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Long id,
            @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(
                ApiResponse.success("User updated successfully", userService.updateUser(id, userDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ApiResponse<UserDto>> activateUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("User activated successfully", userService.activateUser(id)));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<UserDto>> deactivateUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("User deactivated successfully", userService.deactivateUser(id)));
    }

    @PatchMapping("/{id}/soft-delete")
    public ResponseEntity<ApiResponse<UserDto>> softDeleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("User soft deleted successfully", userService.softDeleteUser(id)));
    }
}
