package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl service;
    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = service.findUpAll();
        Assert.assertEquals(1,result.size());
    }

    @Test
    public void save() {
    }
}