package com.smallchange.implementation;

import com.smallchange.entities.Users;
import com.smallchange.repository.UserRepository;
import com.smallchange.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository repository;

    @Override
    public Optional<Users> getUserByEmail(String email) {
        return repository.findById(email);
    }
}
