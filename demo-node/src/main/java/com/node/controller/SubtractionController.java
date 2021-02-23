package com.node.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.httpserver.AbstractJsonHandle;
import com.core.httpserver.RequestDisPatch;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/subtraction", method = "POST")
public class SubtractionController extends AbstractJsonHandle {

    @Override
    protected String onHandle(HttpRequest request, JSONObject o, HttpContext context) throws HttpException, IOException {
        System.err.println("subtraction 减少 content：" + o);
        return "收到";
    }
}
