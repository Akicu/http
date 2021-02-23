package com.node.controller;

import com.core.httpserver.AbstractFormHandle;
import com.core.httpserver.RequestDisPatch;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/multiply", method = "POST")
public class MultiplyController extends AbstractFormHandle {
    @Override
    protected Object onHandle(HttpRequest request, Object o, HttpContext context) throws HttpException, IOException {
        System.err.println("乘");
        return null;
    }
}
