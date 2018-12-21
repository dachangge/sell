package com.imooc.sell.VO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
* @Description: 商品对象（根据商品类目分别）
* @Author: Mr.Deng
* @Date: 2018/12/19 11:26
*/
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    private List<ProductInfoVO> foods;
}
