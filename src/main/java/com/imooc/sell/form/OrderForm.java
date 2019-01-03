package com.imooc.sell.form;

import com.imooc.sell.dataobject.OrderDetail;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderForm {

    //买家名称
    @NotEmpty(message = "姓名不能为空")
    private String name;

    //手机号
    @NotEmpty(message = "手机号必填")
    private String phone;

    //地址
    @NotEmpty(message = "地址必须输入")
    private String address;

    //用户openid
    @NotEmpty(message = "openid不能为空")
    private String openid;

    //购物车内容
    @NotEmpty(message = "购物车不能为空")
    private List<OrderDetail> items;
}
