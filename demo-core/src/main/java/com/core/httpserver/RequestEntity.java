package com.core.httpserver;

import org.apache.http.HttpEntity;

public class RequestEntity {
    private final HttpEntity request;
    private String content = null;

    public RequestEntity(HttpEntity request) {
        this.request = request;
    }

    public RequestEntity(HttpEntity request, String content) {
        this.request = request;
        this.content = content;
    }

    public HttpEntity getRequest() {
        return request;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
