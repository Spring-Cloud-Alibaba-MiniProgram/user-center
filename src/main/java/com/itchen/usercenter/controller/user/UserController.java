package com.itchen.usercenter.controller.user;

import com.itchen.usercenter.domain.entity.user.User;
import com.itchen.usercenter.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-05
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("我被请求了...");
        return userService.findById(id);
    }

}
