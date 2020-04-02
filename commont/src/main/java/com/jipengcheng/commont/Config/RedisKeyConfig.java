package com.jipengcheng.commont.Config;

public interface RedisKeyConfig {
    public static  String RANDOM_KEY="restaurants:random:";
    public static long RANDOM_TIME=3;
    public static String LOGIN_KEY="restaurants:login:";
    public static int LOGIN_TIME=10;
    public static String LOGIN_NUM="restaurants:login:loginNum:";
    public static String LOGIN_NUM_TIME="restaurants:login:loginNum:time:";
    public static int LOGIN_NUM_OUT=24;
}
