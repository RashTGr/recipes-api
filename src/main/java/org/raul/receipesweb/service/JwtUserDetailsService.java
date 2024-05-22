package org.raul.receipesweb.service;

import org.raul.receipesweb.dto.UserDTO;
import org.raul.receipesweb.model.MyUser;
import org.raul.receipesweb.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepository.findByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(myUser.getUsername(), myUser.getPassword(), new ArrayList<>());
    }

    public MyUser save(UserDTO user) {
        MyUser newUser = new MyUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(newUser);
    }
}