package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISH(1,"订单结束"),
    CANCEL(2,"订单取消")
    ;
    private Integer code;
    private String message;
}
