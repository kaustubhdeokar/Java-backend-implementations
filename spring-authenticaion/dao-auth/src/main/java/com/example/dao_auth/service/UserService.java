package com.example.dao_auth.service;

import com.example.dao_auth.dto.RegisterUserDto;
import com.example.dao_auth.exception.CustomException;
import com.example.dao_auth.model.User;
import com.example.dao_auth.repo.UserRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean signup(RegisterUserDto registerUserDto) throws DataIntegrityViolationException {

        checkIfUserAlreadyExists(registerUserDto);

        User user = new User(registerUserDto.getUsername(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                null);

        userRepo.save(user);
        return true;
    }

    private void checkIfUserAlreadyExists(RegisterUserDto registerDto) {

        String username = registerDto.getUsername();
        if (userRepo.findByUsername(username).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    String.format("Username %s exists in the database", username));
        }
    }

}
