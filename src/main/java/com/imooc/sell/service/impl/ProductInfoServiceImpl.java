package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return null;
    }

    @Override
    public List<ProductInfo> findAll() {
        return null;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> result = repository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return result;
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return null;
    }
}
