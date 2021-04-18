package edu.bjtu.ebookshop.mapper;

import edu.bjtu.ebookshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();
    User queryUserById(String id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(String id);

}
