package com.bgnc.questapp.service;

import com.bgnc.questapp.model.User;
import com.bgnc.questapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(Long userId) {

        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserById(Long userId, User newUser) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            User foundedUser = user.get();
            foundedUser.setUserName(newUser.getUserName());
            foundedUser.setPassword(newUser.getPassword());
            userRepository.save(foundedUser);
            return foundedUser;
        }
        else
            return null;
    }

    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
