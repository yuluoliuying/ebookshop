package com.example.userservice.service;


import com.alipay.api.response.AlipayUserInfoShareResponse;

public interface AlipayLoginService {

    String getToken(String authCode);

    AlipayUserInfoShareResponse getUserInfo(String access_token);
}
