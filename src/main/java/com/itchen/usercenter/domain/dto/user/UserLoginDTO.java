package com.itchen.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    /**
     * 授权码
     */
    private String code;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 头像地址
     */
    private String avatarUrl;

}
