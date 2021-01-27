package com.core.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemConfig {
    private static Logger logger = LoggerFactory.getLogger(SystemConfig.class);
    private static final String IO_REACTOR_CONFIG = "ioReactor.properties";
    private static final String HTTP_SERVER_CONFIG = "httpServer.properties";
    private static PropertiesConfiguration ioReactorProperties = null;
    private static PropertiesConfiguration httpServerProperties = null;

    public static void init() {
        ioReactorConfig();
        httpServerConfig();
    }


    public static Configuration getIoReactorConfig() {
        return ioReactorProperties;
    }

    public static Configuration getHttpServerConfig() {
        return httpServerProperties;
    }


    private static void ioReactorConfig() {
        try {
            ioReactorProperties = new PropertiesConfiguration(IO_REACTOR_CONFIG);
        } catch (ConfigurationException e) {
            logger.error("读取ioReactor.properties文件异常e={}", e);
            e.printStackTrace();
        }
    }

    private static void httpServerConfig() {
        try {
            httpServerProperties = new PropertiesConfiguration(HTTP_SERVER_CONFIG);
        } catch (ConfigurationException e) {
            logger.error("读取httpServer.properties文件异常e={}", e);
            e.printStackTrace();
        }
    }


}
