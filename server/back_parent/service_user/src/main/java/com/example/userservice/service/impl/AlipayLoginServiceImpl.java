package com.example.userservice.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.userservice.service.AlipayLoginService;
import org.springframework.stereotype.Service;
import static org.reflections.Reflections.log;

@Service
public class AlipayLoginServiceImpl implements AlipayLoginService {

    private String APP_ID = "2021001172689365";
    private String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCliGJETIq/8gIehxH9y+LOM8t7Gqrxsy5DMFVGsE/KdToiMFvG0hNHxBwjw35UeNfIoyMT7ZirD7NzNab8XiODuT7ituzSwfIlNZbV/xrakwUTaYp3lVPYslH2paUkE9Q0yWz6LQWI7MVQ+7qPIW3rPVLMab6gyacg+oHrjR7r63QEtG4iVAzi3L8NVPwuaP9v4e9XjSHOcPVMtHM4gLVDe6dkNgLliTNSPh8HgmZo5xn4O6BrxKNVhASG19VZpfhJ1q28AC/wtOGf/rRMOdxEM31J6bv593JsY/Zt+ct94O8AZG/d7phTZ2fjoda9tM4DsxFUvMOV/5Mu6/Jh1ELHAgMBAAECggEAMXpLjQin7Gmk8oUC/q5jk6jtqFU3RVl+eXzH8IIgx240uyVjOVFvi+L1WkdDynH4ZbNFd95r5K2jwMsHp6VnziafwlpU/UYVX8Pd2wcOesi5Gq1QQyAyWLIsR6IJc3gt1L1kQn8UiXHkJKmpgMDIPyksR4dQAHVl7DiUftjGplNVeiRAPbtdUDDtU+glR4pyalnPVdEg/w57wmVJsl552Y0GKQftb3V5uNVc1k00R8LoUfY1XNHwG4t+4kCqN6a4sKrWBExpwmMvD+7aqQdiyTVOaFzbyGWlZqCXjIDCUxgPrHC0YTIAIICGgkWOS2AV+gDd7SHoI5jhUSaDxwSP4QKBgQDoW4FjCAtF9trIbaUdYCgKcrhcZk9iX7p+x5gMx6ExPIQwGDVXjQnGQZz6hQkePI3pw5z3yIFNmC5DcsqenyNv3i5yRCAF/J9igu6oWGkhtwBD102F4ehoniWY6XtADq4J8ObLR8kjIiMi82GraCrzsioS/sC2pQYIakDZvGXW1wKBgQC2YDcM6AcbYHIE/QDd6HWzx4oGKN40u8I6IblTglE/oEMzu1HHnvpH/ER9p5nar2/IY//bwvai8TxD0clvINm/fII7qZvkmeliX84OR7cDIGcz/Ub2k9NRUC80Fsec/MP1dHrL29yzbnH08s+uSDDF3XL8qcGXYg6IAqeboBulkQKBgQCYNKjS6EgKMqQqOri4P267OV7PGC3UMQUHW1Ocu4A5snyczJ7UlQzcE8ryyAarCdukT4EZRvx8ib10bVORCIm8E/y02S9o/IyjiUUwfYmQjkHcLsMXel41ebMDMZ6M3ZDf7jAg5Qs6unftwyQtlfTc4c5x1vMCMaN8C64jcyf6zQKBgGow+9z3/atjLQDakHaltGNJ/IQ5p6js6tsOZ+rIJqb1miu1Fpk1aGYh7X9854j+Pghgsco0a4fVD1IuBSw/5Ioq5Wn4CF3DgLFXN5P5Dgc0BuOuxnMtAA6IsfOSkAhI5ZE2kYnCK3APDyuhdmv0fPG5UArujVqoLe3kHdPwFikBAoGAP7t3BFHMQVYJwu8crs7Kj2+4KyquJlInsO2TwblV5GaRo6LAeW3A1s0NdWc6OKkly2jaQZiO/6J4Ygrm9zSLWWiqay6+a6dNd6AVozliBbYt+lc1zjcVr7xjHVb3CLJcYf9VfXBO2YUZHi+4UvkXSmhtRi4kqoXdgFebU59Wl9g=";
    //private String APP_PRIVATE_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtaeJPz2WOsBOd4dPY6Zatq6m1sxQgDG45PcHizBVMx4TUY3iYujxQICjW08ubyvMdCmiyo6Jj3Xw6SXarPlyeZKOA/PMluCbNL43BgxdRz8Znj67lla/Y6x7gVRzfaI0WV8+wbIsQkak88nlnuT8LmjKYPxwbMFQLYbmfw/fbevP+7g1QcnbxFRjeahO7222iJP1KwZVFWWNIMr98GhsTwm+1NzpZSoW1rbLwUNYFRflOKc41EeBPt6zt5CoOJxe968IcwdyJ6zYjR8JKFhUgMfTNNfGbaA9c7U2zM9XFfqkSxpAMpyZhukYPxaS1LLsc1afMZmoWAgiAGcyCwxPyQIDAQAB";
    private String APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv/h4bNzid0med9qXZw6ugBLS+LcuKH45GTpa/eiOcXn4/iPaeRhfShDRBt6RLARdwKppdYfhDxUGkWzo1Xp6Jd6rv+U1ocetLHd/i1Yejq0sXJHLEbbcP6G31UEtTd0l8wSsb2MsBs/3mG8EmcpaHrNk5uaoyj1oNATFP9S9r1ro9VWI16QfctnOaHiSqmYSVyZQlwsc5UXoz1NQ8p8n+8TaTC67Tw6sn42vSXmjguxxtE6wLO3hWxuFym4MhmZwHlA+g6n3D4wBnmP4bgoWTaz1idsO/cyI3DzUxOerECrA3d4JjR7LgSuFi4PYSNpCZFm07Erj0JBmpYqBDFF8bwIDAQAB";
    private String CHARSET = "utf-8";
    private String SIGN_TYPE = "RSA2";

    @Override
    public String getToken(String authCode) {
        String access_token = null;
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",APP_ID,APP_PRIVATE_KEY,"json",CHARSET,APP_PUBLIC_KEY,SIGN_TYPE);
        AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
        req.setCode(authCode);
        req.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(req);
            access_token = oauthTokenResponse.getAccessToken();
            log.info("access_token:     " + access_token);

        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return access_token;
    }

    @Override
    public AlipayUserInfoShareResponse getUserInfo(String access_token) {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID,APP_PRIVATE_KEY,"json",CHARSET,APP_PUBLIC_KEY,SIGN_TYPE);

        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request, access_token);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }
}
