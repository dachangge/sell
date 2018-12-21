package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {


    @Autowired
    OrderMasterRepository repository;

    @Test
    public void save(){
        OrderMaster orderMaster = OrderMaster.builder().buyerAddress("杭州").buyerName("老张").buyerOpenid("123456789").buyerPhone("18814867536").orderAmount(new BigDecimal(108.00)).orderStatus(0).payStatus(0).build();
        OrderMaster result = repository.save(orderMaster);
        System.out.println(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest page = PageRequest.of(1, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid("123456789", page);
        System.out.println(result.getTotalElements());

    }

}