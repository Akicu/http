package com.core.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private MessageDigest md5;
	
    public MD5Util() {
    	try{
    		md5=MessageDigest.getInstance("MD5");
    	} catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    
    public byte[] md5(byte[] data) {
        return md5.digest(data);
    }

    public byte[] md5(String data) {
        return md5(data.getBytes());
    }
    
    public String md5Hex(byte[] data) {
    	char[] chars=Hex.encodeHex(md5(data));
        return new String(chars);
    }

    public String md5Hex(String data) {
    	char[] chars=Hex.encodeHex(md5(data));
        return new String(chars);
    }
    
    public void update(byte[] data,int begin,int offset){
    	md5.update(data,begin,offset);
    }
    
    public String getDigestHex(){
    	byte[] digestData=md5.digest();
    	if (digestData!=null&&digestData.length>0){
    		char[] chars=Hex.encodeHex(md5(digestData));
            return new String(chars);
    	}
    	return null;
    }
}
