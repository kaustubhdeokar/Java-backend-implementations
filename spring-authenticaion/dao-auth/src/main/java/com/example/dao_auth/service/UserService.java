package com.example.dao_auth.service;

import com.example.dao_auth.dto.RegisterUserDto;
import com.example.dao_auth.exception.CustomException;
import com.example.dao_auth.model.Role;
import com.example.dao_auth.model.User;
import com.example.dao_auth.repo.RoleRepo;
import com.example.dao_auth.repo.UserRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    public boolean signup(RegisterUserDto registerUserDto) throws DataIntegrityViolationException {

        checkIfUserAlreadyExists(registerUserDto);

        User user = new User();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());

        Role userRole = roleRepo.findByName("ROLE_USER")
                        .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.getRoles().add(userRole);

        userRepo.save(user);
        return true;
    }

    public void addRoleToUser(String username, String roleName){
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepo.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepo.save(user);
    }

    private void checkIfUserAlreadyExists(RegisterUserDto registerDto) {

        String username = registerDto.getUsername();
        if (userRepo.findByUsername(username).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    String.format("Username %s exists in the database", username));
        }
    }

}
