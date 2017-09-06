package com.free.framwork.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.*;

/**
 * com.free.framwork.zookeeper.ZookeeperTest
 *
 * @author lipeng
 * @dateTime 2017/8/8 22:31
 */
@Slf4j
public class ZookeeperTest {

    public static void main(String[] args) {
        FtpConfig ftpConfig = FtpConfig.builder()
                .host("127.0.0.1")
                .port(80)
                .userName("admin")
                .userPassword("111111")
                .build();
        byte[] strData = null;
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(ftpConfig);

            strData = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }


        String serverList = "192.168.1.13:2181";
        int sessionTimeOut = 6000;
        try {
            ZooKeeper zooKeeper = new ZooKeeper(serverList, sessionTimeOut, (watchedEvent) -> {
                System.out.println("触发客户端监听事件......");
            });
            System.out.println("path chat(0)==========>" + "/root".charAt(0));
            zooKeeper.create("/root", "mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            zooKeeper.create("/appServer", strData, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

            byte[] data = zooKeeper.getData("/appServer", true, null);
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();
            System.out.println("===============>" + obj.toString());
        } catch (IOException e) {
           log.error("异常:", e.fillInStackTrace());
        } catch (InterruptedException e) {
            log.error("异常:", e.fillInStackTrace());
        } catch (KeeperException e) {
            log.error("异常:", e.fillInStackTrace());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
