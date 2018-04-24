package com.free.framework.soa.socketCall.service.impl;

import com.free.framework.soa.socketCall.service.SayHelloService;

import java.util.Objects;

/**
 * com.free.framework.soa.socketCall.service.impl.SayHelloServiceImpl
 *
 * @author lipeng
 * @dateTime 2018/4/24 上午10:56
 */
public class SayHelloServiceImpl implements SayHelloService {

    @Override
    public String say(String param) {
        return Objects.equals(param, "hello") ? "hello" : "bye";
    }
}
