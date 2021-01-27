package com.core.httpserver;


public class HttpServerBootstrap {
    private NHttpServer nHttpServer = new NHttpServer();

    public static final HttpServerBootstrap INSTANCE = new HttpServerBootstrap();

    public static HttpServerBootstrap getInstance() {
        return INSTANCE;
    }


    public void start() {
        Thread t = new Thread(() -> {
            try {
                nHttpServer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t.start();
    }
}
