package com.imooc.sell.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
* @Description: 返回的视图对象 ViewObject
* @Author: Mr.Deng
* @Date: 2018/12/19 11:24
*/
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
