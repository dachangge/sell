package com.imooc.sell.dataobject;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Table(name="product_category")
@DynamicUpdate
public class ProductCategory {

    //类目ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    // 类目名字
    @Column(name = "category_name")
    private String categoryName;

    //类目编号
    @Column(name = "category_type")
    private  Integer categoryType;

}
