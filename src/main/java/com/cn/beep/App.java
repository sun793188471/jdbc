package com.cn.beep;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * this is description
 *
 * @author YCKJ3465
 * @date 2023/2/23
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.cn.beep.**.mapper.**"})
public class App {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App.class);
        application.run(args);
    }
}
