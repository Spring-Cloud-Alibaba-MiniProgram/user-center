package com.itchen.usercenter.service.user;

import com.itchen.usercenter.dao.user.UserMapper;
import com.itchen.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-05
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

}
