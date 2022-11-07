package com.smallchange.services;

import com.smallchange.entities.LoginPayload;
import com.smallchange.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {
    Optional<Users> getUserByEmail(String email);

    ResponseEntity<?> saveUser(Users user);

    ResponseEntity<?> authenticateUser(LoginPayload loginPayload);
}
