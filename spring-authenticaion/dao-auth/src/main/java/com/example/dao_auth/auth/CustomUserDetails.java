package com.example.dao_auth.auth;

import com.example.dao_auth.model.Permission;
import com.example.dao_auth.model.Role;
import com.example.dao_auth.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            if(role.getPermissions()!=null){
                for(Permission permission: role.getPermissions()){
                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                }
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
