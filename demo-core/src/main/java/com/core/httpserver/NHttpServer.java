package com.core.httpserver;


import com.core.util.ScanPackage;
import org.apache.http.ExceptionLogger;
import org.apache.http.impl.nio.bootstrap.HttpServer;
import org.apache.http.impl.nio.bootstrap.ServerBootstrap;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.protocol.UriHttpAsyncRequestHandlerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class NHttpServer {
    //日志
    public static final Logger log = LoggerFactory.getLogger(NHttpServer.class);
    //
    private UriHttpAsyncRequestHandlerMapper handlerReqistry =
            new UriHttpAsyncRequestHandlerMapper();

    public NHttpServer() {
        super();
        //优先注册接口控制器
        rigisterController(HttpConstant.HANDLER_PACKAGE);
    }


    /**
     * 注册控制器接口
     *
     * @param controllerPackage 控制器路径
     */
    public void rigisterController(String controllerPackage) {

        handlerReqistry.register("*", new NotSupportController());
        ScanPackage scanPackage = new ScanPackage(controllerPackage);
        scanPackage.init();
        Set<Class<?>> packetClasses = scanPackage.getClasses();
        packetClasses.stream().forEach(packetClass -> {
            try {
                RequestDisPatch annotation = packetClass.getAnnotation(RequestDisPatch.class);
                if (annotation != null) {
                    AbstractHandle handle = (AbstractHandle) packetClass.newInstance();
                    handle.setCheckSign(annotation.isCheckSign());
                    //方法替换
                    handle.setMethod(annotation.method());
                    //uri替换
                    handle.setUri(annotation.uri());
                    //注册到handler
                    handlerReqistry.register(annotation.uri(), handle);

                }
            } catch (Exception e) {
                log.error("获取类异常e={}", e);
            }
        });

    }


    //开启
    public void start() throws Exception {
        //多路复用配置
        IOReactorConfig reactorConfig = IOReactorConfig.custom()
                .setIoThreadCount(HttpConstant.IO_THREAD_COUNT)
                .setSoTimeout(HttpConstant.SO_TIME_OUT)
                .setSoReuseAddress(HttpConstant.SO_REUSE_ADDRESS)
                .setSoLinger(HttpConstant.SO_LINGER)
                .setSoKeepAlive(HttpConstant.SO_KEEP_ALIVE)
                .setTcpNoDelay(HttpConstant.TCP_NO_DELAY)
                .setConnectTimeout(HttpConstant.CONNECT_TIME_OUT)
                .setSndBufSize(HttpConstant.SND_BUF_SIZE)
                .setRcvBufSize(HttpConstant.RCV_BUF_SIZE)
                .setBacklogSize(HttpConstant.BACK_LOG_SIZE)
                .build();
        //
        ServerBootstrap bootstrap = ServerBootstrap.bootstrap()
                .setListenerPort(HttpConstant.PORT)
                .setIOReactorConfig(reactorConfig)
                .setExceptionLogger(ExceptionLogger.STD_ERR)
                .setHandlerMapper(handlerReqistry)
                .setServerInfo(HttpConstant.SERVER_INFO);
        //创建服务
        HttpServer httpServer = bootstrap.create();
        httpServer.start();


    }


}
