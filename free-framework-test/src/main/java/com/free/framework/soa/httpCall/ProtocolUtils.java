package com.free.framework.soa.httpCall;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * com.free.framework.soa.httpCall.ProtocolUtils
 *
 * @author lipeng
 * @dateTime 2018/5/6 下午4:13
 */
public class ProtocolUtils {

    public static Request readRequest(InputStream inputStream) throws IOException {
        // 读取编码
        byte[] encodeByte = new byte[1];
        inputStream.read(encodeByte);
        byte encode = encodeByte[0];

        // 读取命令长度
        byte[] commandLengthBytes = new byte[4];
        inputStream.read(commandLengthBytes);
        int commandLength = ByteUtils.ByteArrayToInt2(commandLengthBytes);

        byte[] commandByte = new byte[commandLength];
        inputStream.read(commandByte);
        String command = "";
        if (encode == new Byte(Encode.ENCODE_GBK_BYTE)) {
            command = new String(commandByte, Encode.ENCODE_GBK);
        } else {
            command = new String(commandByte, Encode.ENCODE_UTF_8);
        }

        Request request = Request.builder()
                .encode(encode)
                .command(command)
                .commandLength(commandLength)
                .build();

        return request;
    }

    public static Response readResponse(InputStream inputStream) throws IOException {
        // 读取编码
        byte[] encodeByte = new byte[1];
        inputStream.read(encodeByte);
        byte encode = encodeByte[0];

        // 读取命令长度
        byte[] responseLengthBytes = new byte[4];
        inputStream.read(responseLengthBytes);
        int responseLength = ByteUtils.ByteArrayToInt2(responseLengthBytes);

        byte[] commandByte = new byte[responseLength];
        inputStream.read(commandByte);
        String command = "";
        if (encode == new Byte(Encode.ENCODE_GBK_BYTE)) {
            command = new String(commandByte, Encode.ENCODE_GBK);
        } else {
            command = new String(commandByte, Encode.ENCODE_UTF_8);
        }

        Response response = Response.builder()
                .encode(encode)
                .response(command)
                .responseLength(responseLength)
                .build();

        return response;
    }

    public static void writeResponse(OutputStream outputStream, Response response) throws IOException {
        byte encode = response.getEncode();

        outputStream.write(encode);
        outputStream.write(ByteUtils.IntToByteArray2(response.getResponseLength()));
        if (encode == new Byte(Encode.ENCODE_GBK_BYTE)) {
            outputStream.write(response.getResponse().getBytes(Encode.ENCODE_GBK));
        } else {
            outputStream.write(response.getResponse().getBytes(Encode.ENCODE_UTF_8));
        }
        outputStream.flush();
    }

    public static void writeRequest(OutputStream outputStream, Request request) throws IOException {
        byte encode = request.getEncode();

        outputStream.write(encode);
        outputStream.write(ByteUtils.IntToByteArray2(request.getCommandLength()));
        if (encode == new Byte(Encode.ENCODE_GBK_BYTE)) {
            outputStream.write(request.getCommand().getBytes(Encode.ENCODE_GBK));
        } else {
            outputStream.write(request.getCommand().getBytes(Encode.ENCODE_UTF_8));
        }
        outputStream.flush();
    }
}
