package com.imooc.sell.controller;

import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return  "hello";
    }

    @GetMapping("/authorise")
    public String authorise(@RequestParam(value = "returnUrl") String returnUrl, HttpServletResponse response){

        String url = "http://a74272a5.ngrok.io/sell/wechat/userinfo";
        log.info("wxMpService = {}",wxMpService);
        String url1 = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, returnUrl);
        return "redirect:" + url1;
    }

    @GetMapping("/userinfo")
    public String userinfo(@RequestParam(value = "code") String code, @RequestParam("state") String returnUrl, HttpServletResponse response){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
            throw  new SellException(ResultEnum.WX_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;

    }


}
