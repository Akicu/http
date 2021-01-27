package com.core.httpserver;

import java.nio.charset.Charset;

public final class ErrorConstent {
	public static final String SIGN_KEY="sign";
	
	public static final String ERROR_SIGN="{\"errorCode\":"+HttpAPIError.ERROR_SIGN.getErrorCode()
											+",\"errorMsg\":\""+HttpAPIError.ERROR_SIGN.getErrorMsg()+"\"}";
	public static final String ERROR_METHOD="{\"errorCode\":"+HttpAPIError.ERROR_METHOD.getErrorCode()
											+",\"errorMsg\":\""+HttpAPIError.ERROR_METHOD.getErrorMsg()+"\"}";
	public static final String ERROR_JSON="{\"errorCode\":"+HttpAPIError.ERROR_JSON.getErrorCode()
											+",\"errorMsg\":\""+HttpAPIError.ERROR_JSON.getErrorMsg()+"\"}";
	public static final String ERROR_EXPECT="{\"errorCode\":"+HttpAPIError.ERROR_EXPECT.getErrorCode()
											+",\"errorMsg\":\""+HttpAPIError.ERROR_EXPECT.getErrorMsg()+"\"}";
	public static final String ERROR_UNKNOW="{\"errorCode\":"+HttpAPIError.ERROR_UNKNOW.getErrorCode()
			+",\"errorMsg\":\""+HttpAPIError.ERROR_UNKNOW.getErrorMsg()+"\"}";
	public static final String ERROR_API="{\"errorCode\":"+HttpAPIError.ERROR_API.getErrorCode()
											+",\"errorMsg\":\""+HttpAPIError.ERROR_API.getErrorMsg()+"\"}";
	public static final String SUCCESS="{\"errorCode\":"+HttpAPIError.SUCCESS.getErrorCode()
											+",\"errorMsg\":\""+HttpAPIError.SUCCESS.getErrorMsg()+"\"}";
	public static final Charset UTF_8 = Charset.forName("UTF-8");
}
