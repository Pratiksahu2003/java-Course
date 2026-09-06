package com.vedmint.reat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vedmint.reat.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
