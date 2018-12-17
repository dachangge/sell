package com.imooc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private final Logger log = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test(){
        log.debug("debug....");
        log.info("info....");
        log.warn("warn....");
        log.error("error....");
        log.info("name: {}, password: {}", "whc" , "123");
    }

}
