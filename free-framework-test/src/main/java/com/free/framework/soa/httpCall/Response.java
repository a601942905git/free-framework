package com.free.framework.soa.httpCall;

import lombok.*;

/**
 * com.free.framework.soa.httpCall.Response
 *
 * @author lipeng
 * @dateTime 2018/5/6 下午4:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response{

    private byte encode;

    private String response;

    private int responseLength;
}
