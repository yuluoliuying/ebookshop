package com.example.userservice.controller;

import com.example.commonutils.R;
import com.example.userservice.entity.LoginVO;
import com.example.userservice.entity.Store;
import com.example.userservice.entity.User;
import com.example.userservice.service.StoreService;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/userservice/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    // 用户使用账号、密码进行登录
    @PostMapping("login")
    public R login(@RequestBody LoginVO loginVO){
        R response = userService.login(loginVO);
        return response;
    }

    // 用户输入账号、密码等个人信息进行注册
    @PostMapping("register")
    public R register(@RequestBody User user){

        R response  = userService.register(user);
//        if(ok){
//            return R.ok();
//        }else {
//            return R.error();
//        }
        return response;
    }

    // 用户进入信息中心查询个人信息
    @GetMapping("select/{token}")
    public R select(@PathVariable String token){
        User user = userService.select(token);
        return R.ok().data("userinfo", user);
    }

    // 修改用户个人信息
    @PostMapping("update")
    public R update(@RequestBody User user){
        boolean ok = userService.updateUser(user);
        if(ok){
            return R.ok();
        }else {
            return R.error();
        }
    }

    // 根据店铺id 查询店铺信息
    @GetMapping("getStoreInfo/{store_id}")
    public R getStoreInfo(@PathVariable Integer store_id){
        Store store = storeService.getStoreInfo(store_id);
        return R.ok().data("storeInfo",store);
    }

    // 根据userid 获取用户信息
    @GetMapping("getUserInfoById/{user_id}")
    public R getUserInfoById(@PathVariable Integer user_id){
        User user = userService.getById(user_id);
        return R.ok().data("name",user.getUsername()).data("address",user.getDetailAddress()).data("phone",user.getPhone());
    }

    @GetMapping("logout/{token}")
    public R logout(@PathVariable String token){
        userService.logout(token);
        return R.ok();
    }

}

