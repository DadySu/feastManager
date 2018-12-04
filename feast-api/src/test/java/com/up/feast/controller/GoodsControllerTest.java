package com.up.feast.controller;

import com.up.feast.FeastApiApplication;
import com.up.feast.model.GoodCar;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeastApiApplication.class)
@Slf4j
public class GoodsControllerTest {

    @Test
    public void queryGoodsByKindId() {
    }

    @Test
    public void addShoppingCar() {
        GoodCar goodCar = new GoodCar();

        goodCar.setAmount(10);
        goodCar.setGoodId(123L);
        goodCar.setMobile("13138921829");

        log.info(goodCar.toString());
    }
}