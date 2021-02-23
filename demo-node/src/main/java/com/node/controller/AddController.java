package com.node.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.httpserver.AbstractJsonHandle;
import com.core.httpserver.RequestDisPatch;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/add", method = "POST")
public class AddController extends AbstractJsonHandle {


    @Override
    protected String onHandle(HttpRequest request, JSONObject o, HttpContext context) throws HttpException, IOException {
        System.err.println("add 添加 content：" + o);
        return "收到";
    }
}
