package com.core.util;

import com.alibaba.fastjson.JSONObject;
import com.core.enums.EnumReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgUtil {
    private static final Logger log = LoggerFactory.getLogger(MsgUtil.class);

    public static JSONObject returnJson(EnumReturn enumReturn) {
        JSONObject json = new JSONObject();
        json.put("return_code", enumReturn.getCode());
        json.put("return_msg", enumReturn.getMsg());
        return json;
    }

    /**
     * 检测字符串是否是json
     */
    public static JSONObject checkJson(String content) {
        JSONObject json = null;
        try {
            json = JSONObject.parseObject(content);
        } catch (Exception e) {
            log.error("check is json error  content={},errorMsg={}", content, e.getMessage());
        }
        return json;
    }


}
