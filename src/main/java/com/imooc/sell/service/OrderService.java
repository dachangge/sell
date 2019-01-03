package com.imooc.sell.service;


import com.imooc.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService  {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //根据id找订单
    OrderDTO findOne(String orderId);

    //openid 分页订单列表
    Page<OrderDTO> findListByOPenId(String openId, Pageable pageable);

    //取消列表
    OrderDTO cancel(OrderDTO orderDTO);

    //完結訂單
    OrderDTO finish(OrderDTO orderDTO);

    //支付訂單
    OrderDTO paid(OrderDTO orderDTO);


}
