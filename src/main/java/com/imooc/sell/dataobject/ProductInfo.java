package com.imooc.sell.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    private String productId;

    //名字
    private String productName;

    //单价
    private BigDecimal productPrice;


    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //小图地址
    private String productIcon;

    //状态 ,0正常1下架
    private Integer productStatus;

    //类目编号
    private Integer categoryType;

}