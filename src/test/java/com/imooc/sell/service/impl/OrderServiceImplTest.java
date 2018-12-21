package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    private final String ORDERID = "154537819397078122";
    private final String OPENID = "123456789";

    @Test
    public void create() {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        OrderDTO orderDTO = OrderDTO.builder().buyerAddress("浙江杭州").buyerName("波哥").buyerOpenid("123456789").buyerPhone("18814867536").orderDetailList(orderDetails).build();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("4028b88167c0a87a0167c0a883550000");
        orderDetail.setProductQuantity(4);
        orderDetails.add(orderDetail);
        orderService.create(orderDTO);
    }

    @Test
    public void findOneTest(){
        OrderDTO one = orderService.findOne(ORDERID);
        log.info("测试结果：{}",one);
    }

    @Test
    public void findListByOPenIdTest(){
        Page<OrderDTO> result = orderService.findListByOPenId(OPENID, PageRequest.of(1, 1));
    }

    @Test
    public void cancelTest(){
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }
}