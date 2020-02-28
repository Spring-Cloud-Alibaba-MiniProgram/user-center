package com.itchen.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDTO {

    /**
     * 用户 ID
     */
    private Integer id;
    /**
     * 微信昵称
     */
    private String wxNickname;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;

}
