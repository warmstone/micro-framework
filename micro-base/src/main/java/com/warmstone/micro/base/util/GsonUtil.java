package com.warmstone.micro.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.warmstone.micro.base.common.BaseConstant;

/**
 * @author warmstone
 * @date 2023/8/15 20:23
 * @description
 */
public class GsonUtil {

    private static final Gson GSON = new GsonBuilder().create();

    public static String format(Object o) {
        if (ObjectUtil.isNull(o)) {
            return BaseConstant.EMPTY;
        }
        return GSON.toJson(o);
    }
}
