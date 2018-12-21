package com.imooc.sell.service;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String productId);

    List<ProductInfo> findAll();

    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);


    //减库存

    void reduceStock(List<OrderDetail> orderDetailList);

    //加庫存

    void increaseStock(List<OrderDetail> orderDetailList);

}
