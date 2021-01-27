package com.node.controller;

import com.core.httpserver.AbstractHandle;
import com.core.httpserver.RequestDisPatch;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/add", method = "POST")
public class AddController extends AbstractHandle {

    @Override
    protected String onHandle(HttpRequest request, String content, HttpContext context) throws HttpException, IOException {
        System.err.println("add 添加 content：" + content);
        return "收到";
    }
}
