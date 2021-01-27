package com.node.controller;

import com.core.httpserver.AbstractHandle;
import com.core.httpserver.RequestDisPatch;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/subtraction", method = "POST")
public class SubtractionController extends AbstractHandle {
    @Override
    protected String onHandle(HttpRequest request, String content, HttpContext context) throws HttpException, IOException {
        System.err.println("subtraction 减少 content：" + content);
        return "收到";
    }
}
