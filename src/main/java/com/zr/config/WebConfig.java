package com.zr.config;

import com.zr.intercepter.LoginIntercepter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zr
 * @date 2023/4/11 19:01
 */
//@Component
@Configuration

public class WebConfig implements WebMvcConfigurer {
    @Resource
    private LoginIntercepter loginIntercepter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepter).addPathPatterns("/**").excludePathPatterns("/login");

    }
}
