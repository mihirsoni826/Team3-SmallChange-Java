package com.smallchange.implementation;

import com.smallchange.entities.LoginPayload;
import com.smallchange.entities.Users;
import com.smallchange.repository.UserRepository;
import com.smallchange.services.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
    @Override
    public ResponseEntity<?> saveUser(Users user) {
        if(repository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
        }

        user.setPassword(hashPassword(user.getPassword()));
        repository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }
    @Override
    public ResponseEntity<?> authenticateUser(LoginPayload loginPayload) {
        System.err.println(loginPayload);
        Users user = repository.findById(loginPayload.getEmail()).orElse(null);

        if(user != null) {
            if(checkPassword(loginPayload.getPassword(),user.getPassword())) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Invalid credentials!", HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }
}
