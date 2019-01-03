package com.imooc.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderQueryForm {

    @NotEmpty(message = "openid不能为空")
    private String openid;

    @NotEmpty(message = "页数必传")
    private Integer page;

    @NotEmpty(message = "记录数必传")
    private Integer size;
}
