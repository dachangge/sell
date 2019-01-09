package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    PARAM_ERROR(99,"参数异常"),
    PRODUCT_NOT_EXIST(100,"商品不存在"),
    STOCK_NOT_ENOUGH(101,"库存不够"),
    ORDER_NOT_EXIST(102,"订单不存在"),
    DETAIL_NOT_EXIST(103,"订单详情不存在"),
    ORDER_STATUS_ERROR(104,"订单状态错误"),
    PRODUCT_STOCK_ERROR(105,"修改库存失败"),
    PAY_STATUS_ERROR(106,"支付状态错误"),
    UPDATE_ORDER_FAILED(107,"更新订单状态失败"),
    ADD_ORDER_FAILED(108,"创建订单失败"),
    GET_ORDERLIST_ERROR(109,"获取订单列表失败"),
    OPENID_NOT_EXIST(110,"openid为空"),
    WX_ERROR(999,"微信授權失敗"),
    ;
    private Integer code;
    private String message;
}
