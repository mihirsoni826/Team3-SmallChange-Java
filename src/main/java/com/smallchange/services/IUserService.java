package com.smallchange.services;

import com.smallchange.entities.Users;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {
    Optional<Users> getUserByEmail(String email);
}
