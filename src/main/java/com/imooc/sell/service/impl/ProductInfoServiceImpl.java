package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        return productInfo;
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
    @Transactional
    public void increaseStock(List<OrderDetail> orderDetailList) {
        orderDetailList.forEach(e -> {
            ProductInfo productInfo = repository.findById(e.getProductId()).get();
            productInfo.setProductStock(productInfo.getProductStock() + e.getProductQuantity());
            ProductInfo result = repository.save(productInfo);
            if(result == null){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
        });
    }

    @Override
    @Transactional
    public void reduceStock(List<OrderDetail> orderDetailList) {
        orderDetailList.forEach(f->{
            ProductInfo productInfo = repository.findById(f.getProductId()).get();
            if(productInfo.getProductStock() - f.getProductQuantity() < 0){
                throw  new SellException(ResultEnum.STOCK_NOT_ENOUGH);
            }
            productInfo.setProductStock(productInfo.getProductStock() - f.getProductQuantity());
            ProductInfo result = repository.save(productInfo);
            if(result == null){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
        });
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return null;
    }
}
