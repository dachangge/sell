package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.utils.serializer.Date2LongSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;


    //买家名字
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信openid
    private String buyerOpenid;


    //订单总金额
    private BigDecimal orderAmount;

    //订单状态, 默认为新下单
    private  Integer orderStatus;

    //支付状态, 默认未支付
    private Integer payStatus ;

    //创建时间
    @JsonSerialize(using = Date2LongSerialize.class)
    private Date createTime;

    //修改时间
    @JsonSerialize(using = Date2LongSerialize.class)
    private Date updateTime;


    //购买的商品
    List<OrderDetail> orderDetailList;
}
