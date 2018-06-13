package com.free.framework.soa.httpCall.server;

import com.free.framework.soa.httpCall.ProtocolUtils;
import com.free.framework.soa.httpCall.Request;
import com.free.framework.soa.httpCall.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * com.free.framework.soa.httpCall.server.HttpRPCServer
 *
 * @author lipeng
 * @dateTime 2018/5/6 下午4:45
 */
public class HttpRPCServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();

            Request request = ProtocolUtils.readRequest(inputStream);
            System.out.println("客户端请求数据：" + request);
            byte encode = request.getEncode();
            String command = request.getCommand();

            String responseContent = "";
            if (Objects.equals(command, "Hello Http Rpc")) {
                responseContent = "Hello http rpc";
            } else {
                responseContent = "Hello tcp rpc";
            }
            Response response = Response.builder()
                    .encode(encode)
                    .response(responseContent)
                    .responseLength(responseContent.length())
                    .build();

            OutputStream outputStream = socket.getOutputStream();
            ProtocolUtils.writeResponse(outputStream, response);
        }
    }
}
