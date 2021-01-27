package com.core.httpserver;

import org.apache.http.ContentTooLongException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.protocol.AbstractAsyncRequestConsumer;
import org.apache.http.nio.util.HeapByteBufferAllocator;
import org.apache.http.nio.util.SimpleInputBuffer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Asserts;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 构建http请求对象
 * 
 * @author 
 *
 */
public class StringAsyncRequestConsumer extends AbstractAsyncRequestConsumer<RequestBody> {
	private Charset encoding;
	private volatile HttpRequest request;
    private volatile SimpleInputBuffer buf;
    private static final int BUF_CAPACITY=1024;
    
    public StringAsyncRequestConsumer(){
    	encoding= Charset.forName("UTF-8");
    }
    public StringAsyncRequestConsumer(String encoding){
    	this.encoding=Charset.forName(encoding);
    }
    
	@Override
	protected void onRequestReceived(HttpRequest request) throws HttpException, IOException {
		this.request = request;
	}

	@Override
	protected void onEntityEnclosed(HttpEntity entity, ContentType contentType) throws IOException {
		long len = entity.getContentLength();
        if (len > Integer.MAX_VALUE) {
            throw new ContentTooLongException("Entity content is too long: " + len);
        }
        if (len < 0) {
            len = BUF_CAPACITY;
        }
        this.buf = new SimpleInputBuffer((int) len, new HeapByteBufferAllocator());
	}

	@Override
	protected void onContentReceived(ContentDecoder decoder, IOControl ioctrl) throws IOException {
		Asserts.notNull(this.buf, "Content buffer");
        this.buf.consumeContent(decoder);
	}

	@Override
	protected RequestBody buildResult(HttpContext context) throws Exception {
		String content=null;
		if (buf!=null){
			int length=buf.length();
			if (length>0){
				byte[] contentBytes=new byte[length];
				buf.read(contentBytes);
				
				content=new String(contentBytes,encoding);
			}
		}
		
		return new RequestBody(request, content);
	}

	@Override
	protected void releaseResources() {
		this.request = null;
        this.buf = null;
	}

}
