package com.yanhao.weixindemo.controller;

import com.yanhao.weixindemo.util.WeChatUtil;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping
public class OAuther {

    @Autowired
    WeChatUtil weChatUtil;

    @ResponseBody
    @GetMapping("/test")
    public String getTest(String signature,String timestamp,String nonce,String echostr,HttpServletRequest request){
        System.out.println("signature="+signature+"|timestamp"+timestamp+"|nonce"+nonce+"|echostr"+echostr);

        return echostr;
    }

    @PostMapping("/test")
    public void postTest(HttpServletRequest request,HttpServletResponse response){
        ServletInputStream in = null;
        int len = request.getContentLength();
        byte[] b = new byte[len];
        try {
            in = request.getInputStream();
            in.read(b);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String xmlReceivedMessage = null;
        try {
            xmlReceivedMessage = new String(b,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("微信推送的消息数据为：" + xmlReceivedMessage);
    }

    @ResponseBody
    @RequestMapping("/showAccessToken")
    public String demo(){
        return weChatUtil.getAccessToken();
    }
}
