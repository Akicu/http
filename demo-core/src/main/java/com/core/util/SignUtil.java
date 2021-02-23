package com.core.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class SignUtil {
    private static final Logger log = LoggerFactory.getLogger(SignUtil.class);

    public static boolean checkSign(JSONObject json, String signName, String secret) {
        String sign = json.getString(signName);
        if (sign == null || sign.trim().isEmpty()) {
            return false;
        }
        String createSign = createSign(json, signName, secret);
        //签名一致
        if (sign.equals(createSign)) {
            return true;
        }
        return false;
    }

    /**
     * 创建拼凑sign
     *
     * @param body     消息体
     * @param signName 签名字段名称
     * @param secret   与前端一致对应
     * @return 返回加密数据
     */
    public static String createSign(JSONObject body, String signName, String secret) {
        //设置容量大小
        StringBuffer stringBuffer = new StringBuffer(256);
        Set<String> keySet = body.keySet();
        keySet.stream().forEach(key -> {
            if (!key.equals(signName)) {
                String value = body.getString(key);
                //获取不到值或者值为空
                if (value == null || value.trim().length() <= 0) {
                    return;
                }
                stringBuffer.append(key).append("=").append(value).append("&");
            }
        });
        stringBuffer.append("secret=").append(secret);

        return new MD5Util().md5Hex(stringBuffer.toString());
    }


}
