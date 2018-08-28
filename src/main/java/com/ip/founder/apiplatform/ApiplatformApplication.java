package com.ip.founder.apiplatform;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan("com.ip.founder.apiplatform.dao")//与dao层的@Mapper二选一写上即可(主要作用是扫包)
/*@EnableConfigurationProperties(ConfigString.class)*/
public class ApiplatformApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ApiplatformApplication.class, args);
	}
}
