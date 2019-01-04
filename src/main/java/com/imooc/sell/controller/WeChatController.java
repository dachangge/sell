package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeChatController {

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public void verify(HttpServletRequest request, HttpServletResponse response){
        log.info("request= {}",request);
        TreeSet<String> set = new TreeSet<String>();
        set.add("weixin");
        set.add(request.getParameter("timestamp"));
        set.add(request.getParameter("nonce"));
        StringBuilder sBuilder = new StringBuilder();
        for (String item : set) {
            sBuilder.append(item);
        }
        String sign = DigestUtils.sha1Hex(sBuilder.toString());
        String echostr = request.getParameter("echostr");
                log.info("echostr={}",echostr);
        log.info("sign={},signature={}",sign,request.getParameter("signature"));
            try {
                response.getWriter().write(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public void auth(HttpServletRequest request){
        log.info("request = {}", request);
    }
}
