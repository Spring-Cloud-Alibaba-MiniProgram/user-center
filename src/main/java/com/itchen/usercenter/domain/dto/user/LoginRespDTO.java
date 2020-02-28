package com.itchen.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login 响应 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDTO {

    /**
     * token
     */
    private JwtTokenRespDTO token;
    /**
     * 用户信息
     */
    private UserRespDTO user;

}
