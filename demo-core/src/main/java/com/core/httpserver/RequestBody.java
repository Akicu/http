package com.core.httpserver;

import org.apache.http.HttpRequest;

public class RequestBody {
	private final HttpRequest request;
	private String content=null;
	
	public RequestBody(HttpRequest request) {
		this.request = request;
	}
	public RequestBody(HttpRequest request, String content) {
		this.request = request;
		this.content = content;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
