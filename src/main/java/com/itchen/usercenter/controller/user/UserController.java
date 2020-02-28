package com.itchen.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.itchen.usercenter.domain.dto.user.JwtTokenRespDTO;
import com.itchen.usercenter.domain.dto.user.LoginRespDTO;
import com.itchen.usercenter.domain.dto.user.UserLoginDTO;
import com.itchen.usercenter.domain.dto.user.UserRespDTO;
import com.itchen.usercenter.domain.entity.user.User;
import com.itchen.usercenter.service.user.UserService;
import com.itchen.usercenter.util.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private JwtOperator jwtOperator;


    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        log.info("我被请求了...");
        return userService.findById(id);
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO loginDTO) throws WxErrorException {
        // 微信小程序服务端校验是否已经登录的结果
        WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                .getSessionInfo(loginDTO.getCode());
        // 微信的 openId，用户在微信的唯一标识
        String openid = result.getOpenid();
        // 看用户是否注册，如果没有注册就（插入）
        User user = this.userService.login(loginDTO, openid);
        // 颁发 token
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());

        String token = jwtOperator.generateToken(userInfo);

        log.info(
                "【用户 {} 登录成功，生成的 token = {}，有效期到：{}】",
                user.getWxNickname(),
                token,
                jwtOperator.getExpirationDateFromToken(token)
        );
        // 构建响应
        return LoginRespDTO.builder()
                .user(
                        UserRespDTO.builder()
                                .id(user.getId())
                                .avatarUrl(user.getAvatarUrl())
                                .wxNickname(user.getWxNickname())
                                .bonus(user.getBonus())
                                .build()
                )
                .token(
                        JwtTokenRespDTO.builder()
                                .expirationTime(jwtOperator.getExpirationDateFromToken(token).getTime())
                                .token(token)
                                .build()
                )
                .build();

    }

}
