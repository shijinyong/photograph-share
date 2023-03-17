package com.photograph.system.utils;

import com.photograph.system.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: shijinyong
 * @Date: 2023/3/1 22:16
 * @Description:
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册interceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //拦截路径
        registration.addPathPatterns("");

    }
}
