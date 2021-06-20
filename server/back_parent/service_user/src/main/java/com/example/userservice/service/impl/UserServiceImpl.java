package com.example.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.commonutils.JwtUtils;
import com.example.commonutils.MD5;
import com.example.commonutils.R;
import com.example.servicebase.handler.MyHandler;
import com.example.userservice.entity.LoginVO;
import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.StoreService;
import com.example.userservice.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static org.reflections.Reflections.log;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StoreService storeService;

    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";   //秘钥

    // 用户使用账号、密码进行登录
    @Override
    public R login(LoginVO loginVO) {
        R response = null;
        String id = loginVO.getUsername();
        String password = loginVO.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", id);
        if(baseMapper.selectCount(wrapper)>1){
            response = R.error().message("不支持的用户名");
            return response;
        }
        User user = baseMapper.selectOne(wrapper);
        if(user == null){
            response = R.error().message("用户名不存在");
        }
        else if(!user.getPassword().equals(MD5.encrypt(password))){
//            throw new MyHandler(20001, "密码错误");
            response = R.error().message("密码错误");
        }else {
            String identity = user.getIdentity();
            String token = JwtUtils.getJwtToken(id, password);
            if(user.getActive().equals("1")) {
                user.setActive("0");
                baseMapper.updateById(user);
                if (identity.equals("business")) {
                    Integer user_id = user.getUserId();
                    Integer store_id = storeService.getStoreIdByUserId(user_id);
                    response = R.ok().data("token", token).data("identity", identity).data("store_id", store_id);
                } else {
                    response = R.ok().data("token", token).data("identity", identity);
                }
            }else {
                response = R.error().message("该账号已在其他地方登陆");
            }
        }
        return response;
    }

    // 用户输入账号、密码等个人信息进行注册
    @Override
    public R register(User user) {
        String username = user.getEmail();
        String password = user.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", username);
        if(baseMapper.selectCount(wrapper)>0){
//            throw new MyHandler(20001, "用户名已存在");
            R.error().message("用户名已存在");
        }
        user.setPassword(MD5.encrypt(password));
        user.setActive("1");
        user.setIdentity("ordinary");
        baseMapper.insert(user);
        return R.ok();
    }

    // 用户进入信息中心查询个人信息
    @Override
    public User select(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String id = (String)claims.get("id");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", id);
        log.info("查询用户信息的email:    " + id);
        User user = baseMapper.selectOne(wrapper);
        if(user == null){
            throw new MyHandler(20001, "用户不存在");
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        baseMapper.updateById(user);
        return true;
    }

    @Override
    public R alipayLogin(String userId, String gender) {
        R response = null;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("code", userId);
        User user = baseMapper.selectOne(wrapper);
        if(user != null){
            String email = user.getEmail();
            String identity = user.getIdentity();
            String token = JwtUtils.getJwtToken(email, identity);
            user.setActive("0");
            baseMapper.updateById(user);
            if(identity.equals("business")){
                Integer user_id = user.getUserId();
                Integer store_id = storeService.getStoreIdByUserId(user_id);
                response = R.ok().data("token", token).data("identity", identity).data("store_id",store_id).data("state",1);
            }else {
                response = R.ok().data("token", token).data("identity", identity).data("state",1);
            }
        }else {
            User user1 = new User();
            user1.setGender(gender);
            user1.setCode(userId);
            baseMapper.insert(user1);
            response = R.ok().data("state",0).data("gender",gender).data("user_id",user1.getUserId()).data("code",userId);
        }
        return response;
    }

    @Override
    public void logout(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String id = (String)claims.get("id");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", id);
        log.info("用户退出登陆:    " + id);
        User user = baseMapper.selectOne(wrapper);
        user.setActive("1");
        baseMapper.updateById(user);
    }


}
