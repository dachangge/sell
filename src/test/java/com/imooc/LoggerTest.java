package com.imooc;

import com.imooc.sell.SellApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
@Slf4j
public class LoggerTest {

    @Test
    public void test(){

        String name = "wanhc";
        int age = 24;
        log.info("name: {} , age : {}" , name , age);
        log.debug("debug....");
        log.info("info....");
        log.warn("warn....");
        log.error("error....");
    }
}
