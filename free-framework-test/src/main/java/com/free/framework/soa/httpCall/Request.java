package com.free.framework.soa.httpCall;

import lombok.*;

/**
 * com.free.framework.soa.httpCall.Request
 * http RPC请求对象
 * @author lipeng
 * @dateTime 2018/5/6 下午4:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Request {

    private byte encode;

    private String command;

    private int commandLength;
}
