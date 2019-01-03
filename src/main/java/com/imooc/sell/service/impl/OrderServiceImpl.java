package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.dto.OrderMasterDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductInfoService productInfoService;



    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtils.UniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            //订单详情入库
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            BeanUtils.copyProperties(productInfo, orderDetail);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtils.UniqueKey());
            //订单详情入库
            orderDetailRepository.save(orderDetail);
            orderAmount =  orderAmount.add(orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
        }
        //订单主题入库
        OrderMaster orderMaster = OrderMaster.builder().orderAmount(orderAmount).buyerPhone(orderDTO.getBuyerPhone()).buyerOpenid(orderDTO.getBuyerOpenid()).buyerName(orderDTO.getBuyerName()).buyerAddress(orderDTO.getBuyerAddress()).orderId(orderId).orderStatus(OrderStatusEnum.NEW.getCode()).orderId(orderId).payStatus(PayStatusEnum.NOPAY.getCode()).build();
        OrderMaster master = orderMasterRepository.save(orderMaster);
        orderDTO.setOrderId(master.getOrderId());

        //商品减少库存

        productInfoService.reduceStock(orderDTO.getOrderDetailList());
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if(orderMaster == null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetails)){
            throw new SellException(ResultEnum.DETAIL_NOT_EXIST);
        }
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findListByOPenId(String openId, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(openId, pageable);
        List<OrderDTO> orderDTOS = orderMasters.getContent().stream().map(e -> {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(e, orderDTO);
            return orderDTO;
        }).collect(Collectors.toList());

        PageImpl<OrderDTO> result = new PageImpl<>(orderDTOS, pageable, orderMasters.getTotalElements());
        return result;
    }


    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //先判断状态 是否未新下单
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("订单状态错误,orderId={},orderStatus={}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //更新狀態
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            throw  new SellException(ResultEnum.UPDATE_ORDER_FAILED);
        }

        //返回庫存
        productInfoService.increaseStock(orderDTO.getOrderDetailList());

        //如果订单已付款，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.PAYED)){
            //TODO:退款操作
        }
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //先判断状态 是否未新下单
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("订单状态错误,orderId={},orderStatus={}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //更新狀態
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            throw  new SellException(ResultEnum.UPDATE_ORDER_FAILED);
        }

        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {

        //先判断状态 是否未新下单
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("订单状态错误,orderId={},orderStatus={}",orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判斷是否未支付
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.NOPAY.getCode())){
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }

        //更新状态
        orderDTO.setPayStatus(PayStatusEnum.PAYED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            throw  new SellException(ResultEnum.UPDATE_ORDER_FAILED);
        }

        return orderDTO;
    }
}
