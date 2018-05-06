package com.free.framework.soa.httpCall.client;

import com.free.framework.soa.httpCall.Encode;
import com.free.framework.soa.httpCall.ProtocolUtils;
import com.free.framework.soa.httpCall.Request;
import com.free.framework.soa.httpCall.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * com.free.framework.soa.httpCall.client.HttpRPCClient
 *
 * @author lipeng
 * @dateTime 2018/5/6 下午4:36
 */
public class HttpRPCClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8081);
        OutputStream outputStream = socket.getOutputStream();

        Request request = new Request();
        request.setEncode(new Byte(Encode.ENCODE_UTF_8_BYTE));
        request.setCommand("Hello Http Rpc");
        request.setCommandLength(request.getCommand().length());

        ProtocolUtils.writeRequest(outputStream, request);

        InputStream inputStream = socket.getInputStream();
        Response response = ProtocolUtils.readResponse(inputStream);
        System.out.println("服务器端响应数据:" + response);
    }
}
