package com.example.dao_auth.controller;

import com.example.dao_auth.auth.JwtUtil;
import com.example.dao_auth.dto.LoginRequestDto;
import com.example.dao_auth.dto.RegisterUserDto;
import com.example.dao_auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public UserController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        //this.userService = userService;
        //UserService userService,
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDto loginRequestDto){
//        try{
//            //create authentication token.
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
//            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return ResponseEntity.status(HttpStatus.OK).body("Logged in.");
//        }
//        catch (AuthenticationException e){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Auth failed."+e.getMessage());
//        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(jwt);

    }

    @GetMapping("/check")
    public void checkAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()){
            String username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterUserDto registerUserDto) {

//        try {
//            userService.signup(registerUserDto);
//        } catch (DataIntegrityViolationException e) {
//            return new ResponseEntity<>("Duplicate username/email.", HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>("Registration done. Check mail to verify signup.", HttpStatus.OK);


    }

}
