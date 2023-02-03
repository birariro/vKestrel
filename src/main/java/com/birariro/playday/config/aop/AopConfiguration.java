package com.birariro.playday.config.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
