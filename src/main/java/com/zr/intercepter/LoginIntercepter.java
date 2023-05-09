package com.zr.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.zr.utils.JwtUtils;
import com.zr.utils.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/*
*
 * @author zr
 * @date 2023/4/5 22:30
 */
@Slf4j
@Component
public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("我在拦截器里面Inside preHandle method of LoginInterceptor");
        String url=request.getRequestURI().toString();
        if(url.endsWith("/login")||url.endsWith("/admin")||url.endsWith("/database")){
            log.info(url+"1");
            return true;
        }
        String jwt=request.getHeader("token");


        if(!StringUtils.hasLength(jwt)){
            log.info(url+"2");
            Result err= Result.error("NO_LOGIN!");
            String notlogin= JSONObject.toJSONString(err);
            response.getWriter().write(notlogin);
            return false;
        }
        try {
            log.info(url+"3");
            Claims claims = JwtUtils.parseJWT(jwt);
            int roletype = claims.get("roletype", Integer.class); // 从令牌中获取用户角色类型

            // 将用户角色添加到请求属性中
            request.setAttribute("roletype", roletype);

            return true;

        } catch (Exception e) {
            log.info(url+"4");
            e.printStackTrace();
            Result err= Result.error("NOT_LOGIN");
            String notlogin = JSONObject.toJSONString(err);
            response.getWriter().write(notlogin);
            return false;
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
