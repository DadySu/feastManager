package com.up.feast.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description 项目启动时执行类
 *
 * @author liub
 * @date 2019-01-08 14:15
 */
@Order(1)
@Component
@Slf4j
public class HelloRunner implements ApplicationRunner {

    @Value("${tom.age}")
    private int age;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("say hello to me ,today Tom is {} years old", age);
    }
}
