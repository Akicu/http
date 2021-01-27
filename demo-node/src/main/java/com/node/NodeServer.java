package com.node;

import com.core.httpserver.HttpServerBootstrap;
import com.core.util.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeServer {
    private static final Logger logger = LoggerFactory.getLogger(NodeServer.class);

    public static void main(String[] args) {
        try {
            SystemConfig.init();
            logger.info("初始化配置文件完成");


            HttpServerBootstrap.getInstance().start();
            logger.info("启动Http完成");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
