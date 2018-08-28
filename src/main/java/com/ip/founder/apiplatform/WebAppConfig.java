package com.ip.founder.apiplatform;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
	 /** 
     * 配置拦截器 
     */  
    public void addInterceptors(InterceptorRegistry registry) {  
        //registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
