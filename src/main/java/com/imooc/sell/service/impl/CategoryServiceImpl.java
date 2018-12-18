package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.repository.ProductCategoryRepository;
import com.imooc.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        ProductCategory result = repository.findById(categoryId).get();
        return result;
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> result = repository.findAll();
        return result;
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryIdList) {
        List<ProductCategory> result = repository.findByCategoryTypeIn(categoryIdList);
        return result;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        ProductCategory result = repository.save(productCategory);
        return result;
    }
}
