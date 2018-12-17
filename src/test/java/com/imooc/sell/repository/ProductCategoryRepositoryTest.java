package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository repository;

    @Test
    public void findOne(){
        Optional<ProductCategory> productCategory = repository.findById(1);
        ProductCategory category = productCategory.get();
        System.out.println(category);
    }

    @Test
    @Transactional
    public void saveTest(){
            ProductCategory productCategory = ProductCategory.builder().categoryName("123123123").categoryType(6).build();

        repository.save(productCategory);

    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = repository.findById(3).get();
        productCategory.setCategoryType(4);
        ProductCategory category = repository.save(productCategory);
        log.info(category.toString());
        Assert.assertNotNull(category);
    }

}