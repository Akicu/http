package com.core.httpserver;


import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

@RequestDisPatch(uri = "/*", isCheckSign = false)
public class NotSupportController extends AbstractHandle {

    public NotSupportController() {
        super.setMethod("POST");
    }

    @Override
    protected String onHandle(final HttpRequest request,
                              final String content,
                              final HttpContext context) throws HttpException, IOException {
        return  ErrorConstent.ERROR_API;
    }

}
