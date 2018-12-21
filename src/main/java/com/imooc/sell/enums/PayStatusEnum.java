package com.imooc.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayStatusEnum {
    NOPAY(0,"未付款"),
    PAYED(1,"已付款"),
    ;
    private Integer code;
    private String message;
}
