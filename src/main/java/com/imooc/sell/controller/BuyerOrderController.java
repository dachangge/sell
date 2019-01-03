package com.imooc.sell.controller;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.form.OrderQueryForm;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    //创建订单

    @PostMapping("/create")
    public ResultVO create(@RequestBody @Valid OrderForm orderForm, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderDTO.builder().orderDetailList(orderForm.getItems()).buyerPhone(orderForm.getPhone()).buyerOpenid(orderForm.getOpenid()).buyerName(orderForm.getName()).buyerAddress(orderForm.getAddress()).build();
        OrderDTO result = orderService.create(orderDTO);
        if(result == null){
            throw new SellException(ResultEnum.ADD_ORDER_FAILED);
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("orderId",result.getOrderId());
        return   ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO list(
            @RequestParam(value="openid", required = true) String openid,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page
           ){
        if(StringUtils.isEmpty(openid)){
            throw new SellException(ResultEnum.OPENID_NOT_EXIST);
        }
        Page<OrderDTO> result = orderService.findListByOPenId(openid, PageRequest.of(page, size));
        if(result == null){
            throw  new SellException(ResultEnum.GET_ORDERLIST_ERROR);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data", result.getContent());
        return  ResultVOUtil.success(map);
    }
}
