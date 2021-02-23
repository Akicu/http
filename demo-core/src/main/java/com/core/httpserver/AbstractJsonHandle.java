package com.core.httpserver;

import com.alibaba.fastjson.JSONObject;
import com.core.enums.EnumReturn;
import com.core.util.MsgUtil;
import com.core.util.SignUtil;
import com.core.util.ToolUtil;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.http.*;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.nio.protocol.BasicAsyncResponseProducer;
import org.apache.http.nio.protocol.HttpAsyncExchange;
import org.apache.http.nio.protocol.HttpAsyncRequestConsumer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

/**
 * http请求处理handle
 *
 * @author
 */
public abstract class AbstractJsonHandle extends AbstractHandle {
    private static final Logger log = LoggerFactory.getLogger(AbstractJsonHandle.class);

    private static RateLimiter rateLimiter = null;

    private boolean isCheckSign = false;
    private String method;
    private String uri;

    @Override
    public String onHandle(HttpRequest request, String content, HttpContext context) throws IOException, HttpException {
        JSONObject json = MsgUtil.returnJson(EnumReturn.ERROR);

        //检测是否是json
        JSONObject dataJson = MsgUtil.checkJson(content);
        if (dataJson == null) {
            //当前消息格式异常
            json = MsgUtil.returnJson(EnumReturn.IS_NOT_JSON);
            return json.toJSONString();
        }

        boolean checkSign = SignUtil.checkSign(dataJson, "sign", BasicConstent.SIGN_SECRET);
        if (!checkSign) {
            //签名异常
            json = MsgUtil.returnJson(EnumReturn.SIGN_ERROR);
            return json.toJSONString();
        }
//        String data = json.getString("data");
        String result = onHandle(request, dataJson, context);
        System.err.println(result);

        return "";
    }


    @Override
    public HttpAsyncRequestConsumer<RequestBody> processRequest(HttpRequest request, HttpContext context)
            throws HttpException, IOException {
        return new StringAsyncRequestConsumer();
    }

    protected abstract String onHandle(
            final HttpRequest request,
            final JSONObject o,
            final HttpContext context) throws HttpException, IOException;


    protected String getIP(HttpContext context) {
        HttpInetConnection connection = (HttpInetConnection) context.getAttribute(HttpCoreContext.HTTP_CONNECTION);
        InetAddress ia = connection.getRemoteAddress();
        String ip = ia.getHostAddress();

        return ip;
    }

    public void setCheckSign(boolean isCheckSign) {
        this.isCheckSign = isCheckSign;
    }

    public boolean isCheckSign() {
        return isCheckSign;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    protected void sendResponse(int statusCode, String resposeBody, HttpAsyncExchange exchange) {
        HttpResponse response = exchange.getResponse();
        response.setStatusCode(statusCode);

        NStringEntity body;

        if (resposeBody != null) {
            body = new NStringEntity(resposeBody, ContentType.APPLICATION_JSON);
            response.setEntity(body);
            response.addHeader("Access-Control-Allow-Origin", "*");
            //log.debug("---send={}",resposeBody);
        } else {
            log.debug("---send=");
        }

        exchange.submitResponse(new BasicAsyncResponseProducer(response));
    }

    public static void setRateLimiter(RateLimiter limiter) {
        rateLimiter = limiter;
    }
}
