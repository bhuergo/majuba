
package com.majuba.majuba.services;

import com.majuba.majuba.entities.User;
import com.majuba.majuba.repositories.UserRepository;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.searchForUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("NO EXISTE EL USUARIO");
        }
        SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(role));
    }
    
}
