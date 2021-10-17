package com.identity.security;

import com.identity.users.domain.entity.AppUser;
import com.identity.users.infrastructure.JpaUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    private JpaUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = repository.getByUser(username).get();
        if(user == null){
            System.err.println("User: "+username+" not found");
            throw new UsernameNotFoundException("User: "+username+" not found");
        }else{
            System.out.println("User Found: "+username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRolesList()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));


        return new User(user.getUser(), user.getPassword(), authorities);
    }
}
