package com.warmstone.micro.base.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author warmstone
 * @date 2023/11/8 20:13
 * @description
 */
public class JsonRenderUtil {

    public static void render(HttpServletResponse response, String result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
