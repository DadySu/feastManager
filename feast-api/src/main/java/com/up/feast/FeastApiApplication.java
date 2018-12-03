package com.up.feast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类1
 *
 * @author liub
 * @date 2018/11/28
 */
@SpringBootApplication
@ComponentScan("com.up.feast")
public class FeastApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeastApiApplication.class, args);
    }
}
