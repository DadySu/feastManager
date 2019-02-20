package com.up.feast.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author liub
 * @date 2019-01-08 14:16
 */
@Order(2)
@Component
@Slf4j
public class GoodByeRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("say goodBye to me");
    }
}
