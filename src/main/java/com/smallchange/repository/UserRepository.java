package com.smallchange.repository;

import com.smallchange.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
    boolean existsByEmail(String email);

//    Optional<Users> findByEmail(String email);
}
