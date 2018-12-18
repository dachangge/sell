package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    ProductInfo findOne(String productId);

    List<ProductInfo> findAll();

    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);
}
