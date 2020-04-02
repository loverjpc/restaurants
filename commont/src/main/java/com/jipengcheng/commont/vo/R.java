package com.jipengcheng.commont.vo;

import lombok.Data;

@Data
public class R {
    private int code;
    private String msg;
    private Object obj;

    public R(int code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public R() {
    }
    public static R ok(){
        return new R(0,"ok",null);
    }
    public static R ok(Object obj){
        return new R(0,"ok",obj);
    }
    public static R fail(){
        return new R(1,"ERROR",null);
    }
    public static R fail(String msg){
        return new R(1,msg,null);
    }
}
