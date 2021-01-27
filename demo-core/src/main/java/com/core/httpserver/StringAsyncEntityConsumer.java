package com.core.httpserver;

import org.apache.http.*;
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
 */
public class StringAsyncEntityConsumer extends AbstractAsyncRequestConsumer<RequestEntity> {
    private Charset encoding;
    private volatile HttpEntity request;
    private volatile SimpleInputBuffer buf;
    private static final int BUF_CAPACITY = 1024;

    public StringAsyncEntityConsumer() {
        encoding = Charset.forName("UTF-8");
    }

    @Override
    protected void onRequestReceived(HttpRequest httpRequest) throws HttpException, IOException {

    }


    public StringAsyncEntityConsumer(String encoding) {
        this.encoding = Charset.forName(encoding);
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
        this.request = entity;
    }

    @Override
    protected void onContentReceived(ContentDecoder decoder, IOControl ioctrl) throws IOException {
        Asserts.notNull(this.buf, "Content buffer");
        this.buf.consumeContent(decoder);
    }

    @Override
    protected RequestEntity buildResult(HttpContext context) throws Exception {
        String content = null;
        if (buf != null) {
            int length = buf.length();
            if (length > 0) {
                byte[] contentBytes = new byte[length];
                buf.read(contentBytes);

                content = new String(contentBytes, encoding);
            }
        }

        return new RequestEntity(request, content);
    }

    @Override
    protected void releaseResources() {
        this.request = null;
        this.buf = null;
    }

}
