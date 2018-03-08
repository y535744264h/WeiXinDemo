package com.yanhao.weixindemo.filter;

import com.yanhao.weixindemo.Constant;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 微信 授权拦截器，如果用户没有授权 先进行授权
 */
public class AauthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpSession session=request.getSession();
        String code=request.getParameter("code");
        String openid= (String) session.getAttribute("OPENID");
        if((code==null||"".equals(code))&&(openid==null||"".equals(openid))){
                System.out.println("进入第一步获取Code");
            String url = request.getScheme() + "://"
                    + request.getServerName()
                    + request.getRequestURI()
                    + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            System.out.println("请求的URL="+url);
            StringBuffer stringBuffer=new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize");
            stringBuffer.append("?appid="+ Constant.APPID);
            stringBuffer.append("&redirect_uri="+ URLEncoder.encode(url));
            stringBuffer.append("&response_type=code");
            stringBuffer.append("&scope=snsapi_userinfo");
            stringBuffer.append("&state=STATE#wechat_redirect");
            System.out.println("转发后URL="+stringBuffer);
            ((HttpServletResponse)servletResponse).sendRedirect(stringBuffer.toString());
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

//        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
