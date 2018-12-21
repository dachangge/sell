package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    PRODUCT_NOT_EXIST(100,"商品不存在"),
    STOCK_NOT_ENOUGH(101,"库存不够"),
    ORDER_NOT_EXIST(102,"订单不存在"),
    DETAIL_NOT_EXIST(103,"订单详情不存在"),
    ORDER_STATUS_ERROR(104,"订单状态错误"),
    PRODUCT_STOCK_ERROR(105,"修改库存失败")
    ;
    private Integer code;
    private String message;
}
