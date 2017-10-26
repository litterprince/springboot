package com.example1.tools;

public final class ServiceConstant {

    /**
     * cache 在shiro的过期时间是1分钟1*60
     */
    public static final int EXPIRE_TIME_CACHE = 600;
    /**
     * session 在redis的过期时间是10分钟10*60
     */
    public static final int EXPIRE_TIME_SESSION = 600;
    /**
     * 记住我cookie生效时间3天3*24*60*60
     */
    public static final int EXPIRE_TIME_COOKIES = 259200;
}
