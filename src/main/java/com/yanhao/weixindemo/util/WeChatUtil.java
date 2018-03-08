package com.yanhao.weixindemo.util;

import com.alibaba.fastjson.JSONObject;
import com.yanhao.weixindemo.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import static com.yanhao.weixindemo.Constant.MENUDELETE;
import static com.yanhao.weixindemo.Constant.TOKEN;

@Component
public class WeChatUtil {

    @Autowired
    RedisUtil redisUtil;

    public String  getAccessToken(){
        String token=redisUtil.get("wxAccessToKen");
        if(StringUtils.isEmpty(token)){
            Map<String,String> map=new HashMap<>();
            map.put("grant_type","client_credential");
            map.put("appid", Constant.APPID);
            map.put("secret",Constant.SECRET);
            String resule=HttpUtils.sendGet(TOKEN,map);
            System.out.println("resule="+resule);
            JSONObject jsonObject=JSONObject.parseObject(resule);
            String newToken=jsonObject.getString("access_token");
            Integer newTime=jsonObject.getInteger("expires_in");
            System.out.println("newToken="+newToken);
            System.out.println("newTime="+newTime);
            redisUtil.set("wxAccessToKen",newToken,newTime.longValue());
            return newToken;
        }else{
            return token;
        }
    }

    public String deleteMene(){
        Map<String,String> map=new HashMap<>();
        map.put("access_token",getAccessToken());
        return HttpUtils.sendGet(MENUDELETE,map);
    }


}
