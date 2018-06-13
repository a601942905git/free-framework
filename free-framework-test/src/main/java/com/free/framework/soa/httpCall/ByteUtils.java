package com.free.framework.soa.httpCall;

/**
 * com.free.framework.soa.httpCall.ByteUtils
 *
 * @author lipeng
 * @dateTime 2018/5/6 下午4:19
 */
public class ByteUtils {

    public static byte[] IntToByteArray2(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static int ByteArrayToInt2(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        return (int) ((((bArr[0] & 0xff) << 24)
                | ((bArr[1] & 0xff) << 16)
                | ((bArr[2] & 0xff) << 8)
                | ((bArr[3] & 0xff) << 0)));
    }
}
