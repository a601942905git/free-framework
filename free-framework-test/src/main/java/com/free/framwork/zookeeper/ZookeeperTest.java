package com.free.framwork.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.*;

/**
 * com.free.framwork.zookeeper.ZookeeperTest
 *
 * @author lipeng
 * @dateTime 2017/8/8 22:31
 */
@Slf4j
public class ZookeeperTest {

    public static final String CONFIG_DATA = "/configData";

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


        String serverList = "192.168.1.11:2181";
        int sessionTimeOut = 6000;
        try {
            ZooKeeper zooKeeper = new ZooKeeper(serverList, sessionTimeOut, (watchedEvent) -> {
                System.out.println("触发客户端监听事件......");
            });

            Stat stat = zooKeeper.exists(CONFIG_DATA, true);
            if (null == stat) {
                zooKeeper.create(CONFIG_DATA, strData, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            stat = zooKeeper.exists(CONFIG_DATA, true);
            if (null != stat) {
                byte[] data = zooKeeper.getData(CONFIG_DATA, true, null);
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Object obj = ois.readObject();
                System.out.println("配置信息===============>" + obj.toString());
            }
        } catch (IOException e) {
           log.error("异常:", e);
        } catch (InterruptedException e) {
            log.error("异常:", e);
        } catch (KeeperException e) {
            log.error("异常:", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
