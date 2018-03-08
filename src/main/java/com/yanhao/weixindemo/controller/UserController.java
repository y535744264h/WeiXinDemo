package com.yanhao.weixindemo.controller;

import com.yanhao.weixindemo.util.HttpUtils;
import com.yanhao.weixindemo.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    WeChatUtil weChatUtil;

    @RequestMapping("/sayHi")
    public String sayHi(Map<String,Object> map,HttpSession session){
        map.put("openId",session.getAttribute("OPENID"));
        System.out.println("OPENID="+session.getAttribute("OPENID"));
        return "sayHi";
    }

    @RequestMapping("/userInfo")
    public String showUserInfo(Map<String,Object> map,HttpSession session){
        Map<String,String> map1=new HashMap<>();
        map1.put("access_token",weChatUtil.getAccessToken());
        map1.put("openid",session.getAttribute("OPENID")+"");
        String result=HttpUtils.sendGet("https://api.weixin.qq.com/cgi-bin/user/info",map1);
        map.put("result",result);
        return "userInfo";
    }
}
