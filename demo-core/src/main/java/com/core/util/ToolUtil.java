package com.core.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class ToolUtil {
    private static final Logger log = LoggerFactory.getLogger(ToolUtil.class);






    /**
     * 检测消息是否存在消息头
     *
     * @return 消息头value
     */
    public static String checkHeaders(HttpRequest request, String headersName) {
        Header[] headers = request.getHeaders(headersName);
        if (headers.length > 0) {
            Header header = headers[0];
            return header.getValue();
        }
        return null;
    }

}
