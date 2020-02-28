package com.itchen.usercenter.configuration;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置类 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2020-02-28
 */
@Configuration
public class WxConfiguration {

    @Bean
    public WxMaConfig wxMaConfig() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid("wxe7617a13921f4517");
        config.setSecret("b1276458e00f4f7b3ab81caae5582179");
        return config;
    }

    @Bean
    public WxMaService wxMaService(WxMaConfig wxMaConfig) {
        WxMaServiceImpl service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaConfig);
        return service;
    }

}
