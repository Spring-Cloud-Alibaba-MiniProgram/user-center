package com.itchen.usercenter.controller;

import com.itchen.usercenter.dao.user.UserMapper;
import com.itchen.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-05
 */
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "/test")
    public User testInsert() {
        User user = new User();
        user.setAvatarUrl("xxx");
        user.setBonus(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        this.userMapper.insertSelective(user);
        return user;
    }

    /**
     * q?id=1&wxId=xxx&...
     *
     * @param user
     * @return
     */
    @GetMapping("/q")
    public User query(User user) {
        return user;
    }

}
