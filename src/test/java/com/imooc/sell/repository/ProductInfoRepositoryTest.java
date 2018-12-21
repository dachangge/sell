package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    ProductInfoRepository repository;



    @Test
    public void save(){
        ProductInfo product = ProductInfo.builder().categoryType(5).productDescription("香脆爽！！！").productIcon("http://xxx.png").productName("麦辣鸡翅").productPrice(new BigDecimal(10.80)).productStatus(0).productStock(200).build();
        ProductInfo result = repository.save(product);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,result.size());

    }
}