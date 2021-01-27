package com.node;

import com.core.httpserver.NHttpServer;

public class Test {
    public static void main(String[] args) {
        NHttpServer nHttpServer = new NHttpServer();
        nHttpServer.rigisterController("com.node.controller");
    }

}
