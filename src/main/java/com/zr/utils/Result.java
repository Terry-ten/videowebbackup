package com.zr.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(1,"操作成功",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(200,"操作成功",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }

    public static Result error(Integer code){
        return new Result(code,"权限不足",null);
    }
}
