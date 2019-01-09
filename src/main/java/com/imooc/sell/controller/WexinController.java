package com.imooc.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WexinController {

    private final RestTemplate restTemplate = new RestTemplate();
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
        log.info("request = {},code= {}", request,request.getParameter("code"));
//        https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbc82b94527bce23d&secret=d3ac61b9e0e5f7238b31ab49f25104e4&code=");
        stringBuilder.append(request.getParameter("code"));
        stringBuilder.append("&grant_type=authorization_code");
        log.info(stringBuilder.toString());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(stringBuilder.toString(), String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        log.info(result.toString());
      
    }
}
