package com.free.framwork.zookeeper;

import lombok.*;

import java.io.Serializable;

/**
 * com.free.framwork.zookeeper.FtpConfig
 *
 * @author lipeng
 * @dateTime 2017/8/8 22:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FtpConfig implements Serializable{

    private String host;

    private Integer port;

    private String userName;

    private String userPassword;
}
