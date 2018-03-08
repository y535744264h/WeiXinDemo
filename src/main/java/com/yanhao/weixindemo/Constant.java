package com.yanhao.weixindemo;

//常量类
public class Constant {

    public static final String APPID="wx4539efd94f0002d4";
    public static final String SECRET="6ed807cf6295899bb80b586662d499ce";


    //获取access_token {"errcode":40013,"errmsg":"invalid appid"}
    public static final String TOKEN="https://api.weixin.qq.com/cgi-bin/token";

    //自定义菜单删除接口?access_token=ACCESS_TOKEN对应创建接口，正确的Json返回结果:{"errcode":0,"errmsg":"ok"}
    public static final String MENUDELETE="https://api.weixin.qq.com/cgi-bin/menu/delete";
}
