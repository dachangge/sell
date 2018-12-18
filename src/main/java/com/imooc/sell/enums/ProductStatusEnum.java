package com.imooc.sell.enums;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum ProductStatusEnum {
    UP(0,"在架"),Down(1,"下架")
    ;
    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
