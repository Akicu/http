package com.core.httpserver;

public enum HttpAPIError {
	SUCCESS(0,"成功"),
	
	ERROR_UNKNOW(1,"未知错误"),
	ERROR_API(3,"API接口不被支持"),
	ERROR_LIMIT(4,"应用对API接口的调用请求数达到上限"),
	ERROR_IP(5,"API调用端的IP未被授权"),
	ERROR_PARAM(100,"参数无效或缺失"),
	ERROR_SIGN(101,"签名无效"),
	ERROR_JSON(102,"参数JSON格式错误"),
	ERROR_METHOD(103,"Http Method 错误！"),
	ERROR_EXPECT(104,"Expect请求头不支持！");
	
	private final int errorCode;
	private final String errorMsg;
	
	HttpAPIError(int errorCode, String errorMsg){
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
