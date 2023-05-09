package com.zr.aop;

import com.alibaba.fastjson.JSONObject;
import com.zr.mapper.LogsMapper;
import com.zr.pojo.Logs;
import com.zr.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author zr
 * @date 2023/4/15 10:36
 */
@Slf4j
@Aspect
@Component
public class MyLogAspect {


    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogsMapper logsMapper;
    @Around("@annotation(com.zr.anno.MyLogs)")
    public Object recordLogs(ProceedingJoinPoint joinPoint) throws Throwable {
        String token = request.getHeader("token");
        Claims claims=JwtUtils.parseJWT(token);
        Integer id= (Integer) claims.get("id");
        String roletype=(Integer)claims.get("roletype")== 3 ? "业务管理员":"超级管理员";
        String classname = joinPoint.getTarget().getClass().getName();
        String methodname = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String methodparams = Arrays.toString(args);
        Object result=joinPoint.proceed();
        String results = JSONObject.toJSONString(result);
        LocalDateTime createtime=LocalDateTime.now();

        Logs log=new Logs(null,id,roletype,createtime,classname,methodname,methodparams,results);
        System.out.println(log);
        logsMapper.insertLogs(log);
        return result;
    }
}
