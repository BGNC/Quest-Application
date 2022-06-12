package com.bgnc.questapp.service;

import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.UserRepository;
import com.bgnc.questapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JwtUserDetails.create(user);
    }

    /**
     * helper method
     * @param userId
     * @return
     */
    public UserDetails loadUserById(Long userId){
        User user = userRepository.findById(userId).get();
        return JwtUserDetails.create(user);
    }
}
