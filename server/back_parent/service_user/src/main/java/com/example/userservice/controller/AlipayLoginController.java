package com.example.userservice.controller;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.commonutils.R;
import com.example.userservice.entity.User;
import com.example.userservice.service.AlipayLoginService;
import com.example.userservice.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.reflections.Reflections.log;

@RestController
@RequestMapping("/userservice/alipay_login")
public class AlipayLoginController {

    @Autowired
    private AlipayLoginService alipayLoginService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getAuthInfo")
    public R getAuthInfo(HttpServletRequest request, HttpServletResponse response){
        String authCode = request.getParameter("auth_code");
        String appId = request.getParameter("app_id");
        String scope = request.getParameter("scope");

        log.info("-------------------支付宝登录访问---------------------");
        log.info("auth code:        "+authCode);

        if(StringUtils.isNotEmpty(authCode)) {
            // 获取access token
            String access_token = alipayLoginService.getToken(authCode);
            //获取用户信息
            if (StringUtils.isNotEmpty(access_token)) {
                AlipayUserInfoShareResponse resp = alipayLoginService.getUserInfo(access_token);
                if (resp.isSuccess()) {
                    log.info("调用成功");
                    String userId = resp.getUserId();
                    String gender = resp.getGender();

                    R r = userService.alipayLogin(userId,gender);

                    return R.ok().data("response", resp);
                } else {
                    log.info("调用失败");
                    return R.error().message("获取用户信息失败");
                }
            }else {
                return R.error().message("获取access token失败");
            }
        }
        return R.error().message("授权码为空");
    }

    @PostMapping("register")
    public R alipay_register(@RequestBody User user){
        userService.updateById(user);
        return R.ok();
    }

}
