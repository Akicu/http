package com.core.httpserver;


public class HttpConstant {
    public static String HANDLER_PACKAGE = "com.node.controller";
    public static Integer IO_THREAD_COUNT = 40;
    public static Integer SO_TIME_OUT = 3000;
    public static boolean SO_REUSE_ADDRESS = true;
    public static Integer SO_LINGER = -1;
    public static boolean SO_KEEP_ALIVE = true;
    public static boolean TCP_NO_DELAY = true;
    public static int CONNECT_TIME_OUT = 3000;
    public static int SND_BUF_SIZE = 2048;
    public static int RCV_BUF_SIZE = 1024;
    public static int BACK_LOG_SIZE = 128;
    public static int PORT = 8991;
    public static String SERVER_INFO = "demo/1.0";
}
