package edu.bjtu.ebookshop.controller;

import edu.bjtu.ebookshop.entity.User;
import edu.bjtu.ebookshop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList=userMapper.queryUserList();
        for(User user:userList){
            System.out.println(user);
        }
        return userList;
    }
    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User("wyhui","wyhui","1234","wyhui"));
        return "ok";
    }
    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User("wyhui","wyhui","nfjkrfnrfm","wyhui1"));
        return "ok";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser("wyhui");
        return "ok";
    }


}
