package com.example.sms.config;

import com.example.sms.interceptor.InterceptorTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InterceptorTest interceptorTest(){

        return  new InterceptorTest();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(interceptorTest());
    }
}
