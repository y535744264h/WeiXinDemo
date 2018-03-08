package com.yanhao.weixindemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("receive")
public class ReceiveController {

    @RequestMapping("messageReceive")
    public void messageReceive(){
        System.out.println("111111111111");
    };
}
