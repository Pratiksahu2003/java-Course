package com.vedmint.reat.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.vedmint.reat.Dto.UserDto;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public User activate() {
        isActive = true;
        deletedAt = null;
        return this;
    }

    public User deactivate() {
        isActive = false;
        return this;
    }

    public User softDelete() {
        deletedAt = LocalDateTime.now();
        isActive = false;
        return this;
    }

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setName(this.name);
        userDto.setEmail(this.email);
        userDto.setCreatedAt(this.createdAt);
        userDto.setUpdatedAt(this.updatedAt);
        userDto.setDeletedAt(this.deletedAt);
        userDto.setIsActive(this.isActive);
        return userDto;
    }
}