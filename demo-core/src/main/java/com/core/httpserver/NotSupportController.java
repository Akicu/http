package com.core.httpserver;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/*", isCheckSign = false)
public class NotSupportController extends AbstractJsonHandle {

    public NotSupportController() {
        super.setMethod("POST");
    }


    @Override
    protected String onHandle(HttpRequest request, JSONObject o, HttpContext context) throws HttpException, IOException {
        return ErrorConstent.ERROR_API;
    }

}
