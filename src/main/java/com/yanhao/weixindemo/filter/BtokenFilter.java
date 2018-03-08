package com.yanhao.weixindemo.filter;

import com.alibaba.fastjson.JSONObject;
import com.yanhao.weixindemo.Constant;
import com.yanhao.weixindemo.util.HttpUtils;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BtokenFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        String code=request.getParameter("code");
        String openid= (String) session.getAttribute("OPENID");
        if(openid==null||"".equals(openid)&&code!=null&&!"".equals(code)){
            String url = request.getScheme() + "://"
                    + request.getServerName()
                    + request.getRequestURI()
                    + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            System.out.println("请求的URL="+url);
            System.out.println("进入第二步获取OPENID");
            StringBuffer stringBuffer=new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token");
            Map<String,String> map=new HashMap<>();
            map.put("appid",Constant.APPID);
            map.put("secret",Constant.SECRET);
            map.put("code",code);
            map.put("grant_type","authorization_code");
            String result=HttpUtils.sendGet(stringBuffer.toString(),map);
            System.out.println(result);
            JSONObject jsonObject= (JSONObject) JSONObject.parse(result);
            String openId=jsonObject.getString("openid");
            System.out.println("OpenId="+openId);
            session.setAttribute("OPENID",openId);
            System.out.println("获取OPENID完成");
            ((HttpServletResponse)servletResponse).sendRedirect(url);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
