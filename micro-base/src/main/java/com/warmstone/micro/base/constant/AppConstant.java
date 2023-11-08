package com.warmstone.micro.base.constant;

/**
 * @author warmstone
 * @date 2022-04-12 10:42
 * @description 系统常量
 */
public class AppConstant {

    /**
     * JWT签名秘钥
     */
    public static final String JWT_SECRET = "ball-cat";

    /**
     * JWT token过期时间
     */
    public static final Long JWT_EXPIRATION = 3600L * 24;

    /**
     * 普通日期格式
     */
    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 普通日期时间格式
     */
    public static final String SIMPLE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 带毫秒时间格式
     */
    public static final String MILLISECOND_DATE_TIME_FORMAT = "yyyyMMddHHmmssSSS";

    /**
     * 文件后缀分隔符
     */
    public static final String FILE_SEPARATOR = ".";
}
