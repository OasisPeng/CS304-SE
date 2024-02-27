package com.example.cs304.common;

import lombok.Data;

//后端返回的数据封装,List<user>->Result
@Data
public class Result {
    private int code;
    private String msg;//成功/失败
    private Object data;

    public static Result fail(){
        return result(400,"失败",null);
    }
    public static Result suc(Object data){
        return result(200,"成功",data);
    }
    private static Result result(int code,String msg,Object data){//相当于构造器
        Result res=new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }
}
