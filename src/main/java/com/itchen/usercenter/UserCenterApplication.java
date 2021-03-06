package com.itchen.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 用户中心启动类 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-05
 */
@MapperScan("com.itchen.usercenter.dao")
@SpringBootApplication
@EnableBinding({Sink.class})
public class UserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class, args);
    }

}
