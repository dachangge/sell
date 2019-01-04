package com.imooc.sell.utils;

import com.imooc.sell.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object obj){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(obj);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

}
