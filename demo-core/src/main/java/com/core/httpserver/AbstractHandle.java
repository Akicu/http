package com.core.httpserver;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.http.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.DefaultNHttpServerConnection;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.nio.protocol.BasicAsyncResponseProducer;
import org.apache.http.nio.protocol.HttpAsyncExchange;
import org.apache.http.nio.protocol.HttpAsyncRequestConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestHandler;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;

/**
 * http请求处理handle
 *基础
 * @author
 */
public abstract class AbstractHandle implements HttpAsyncRequestHandler<RequestBody> {
    private static final Logger log = LoggerFactory.getLogger(AbstractHandle.class);

    private static RateLimiter rateLimiter = null;

    private boolean isCheckSign = false;
    private String method;
    private String uri;

    @Override
    @SuppressWarnings("unused")
    public void handle(RequestBody requestBody, HttpAsyncExchange exchange, HttpContext context)
            throws HttpException, IOException {
        DefaultNHttpServerConnection obj = (DefaultNHttpServerConnection) context.getAttribute("http.connection");
        InetAddress InetAddress = obj.getRemoteAddress();
        int port = obj.getRemotePort();
        Header header = requestBody.getRequest().getLastHeader("x-forwarded-for");
        if (rateLimiter != null) {
            rateLimiter.acquire();
        }
        HttpRequest request = requestBody.getRequest();
//        Content-Type: application/x-www-form-urlencoded; charset=UTF-8
        RequestLine requestLine = request.getRequestLine();
        String methodReq = requestLine.getMethod().toUpperCase(Locale.ENGLISH);
        if (!methodReq.equals(method)) {
            //log.warn("****{} receive,request Method={},handler Method={}",request.getRequestLine().getUri(),methodReq,method);
        }

        String content = requestBody.getContent();


        try {
            if (content != null) {
                log.info("入站uri {} , receive={}", request.getRequestLine().getUri(), content);
            }

        } catch (Exception e) {

        }
        String resposeBody = "";
        try {
            resposeBody = onHandle(request, content, context);
            if (content != null) {
                log.info("出站uri {} , receive={}", request.getRequestLine().getUri(), resposeBody);
            }
        } catch (Throwable e) {
            log.error("handle() content={} e={}", content, e);
            resposeBody = ErrorConstent.ERROR_UNKNOW;
        }
        sendResponse(HttpStatus.SC_OK, resposeBody, exchange);
    }

    @Override
    public HttpAsyncRequestConsumer<RequestBody> processRequest(HttpRequest request, HttpContext context)
            throws HttpException, IOException {
        return new StringAsyncRequestConsumer();
    }

    protected abstract String onHandle(
            final HttpRequest request,
            final String content,
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
