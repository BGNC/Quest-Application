package com.bgnc.questapp.controller;
import com.bgnc.questapp.model.User;
import com.bgnc.questapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User newUser){

        return userService.addUser(newUser);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long userId){

        //exception do not forget

        return userService.getUserById(userId);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long userId ,@RequestBody User newUser){
       return userService.updateUserById(userId,newUser);

    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long userId){
        userService.deleteById(userId);

    }




}
