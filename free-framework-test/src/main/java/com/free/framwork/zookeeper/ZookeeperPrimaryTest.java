package com.free.framwork.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * com.free.framwork.zookeeper.ZookeeperPrimaryTest
 *
 * @author lipeng
 * @dateTime 2017/8/13 13:43
 */
public class ZookeeperPrimaryTest {

    public static final String ROOT_PATH = "/root";

    public static void main(String[] args) {
        String serverList = "192.168.1.11:2181";
        int sessionTimeOut = 6000;

        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(serverList, sessionTimeOut, (watchedEvent) -> {
                System.out.println("触发事件===>" + watchedEvent.getType());
            });

            Stat stat = zooKeeper.exists(ROOT_PATH, true);
            if (null == stat) {
                // 创建节点
                zooKeeper.create(ROOT_PATH, "root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            // 获取节点数据
            byte[] rootData = zooKeeper.getData(ROOT_PATH, true, null);
            System.out.println("新增后根节点数据:======>" + new String(rootData, StandardCharsets.UTF_8));

            // 修改节点数据
            zooKeeper.setData(ROOT_PATH, "modifyRootData".getBytes(), -1);
            byte[] modifyRootData = zooKeeper.getData(ROOT_PATH, true, null);
            System.out.println("修改后根节点数据:======>" + new String(modifyRootData, StandardCharsets.UTF_8));

            // 删除节点数据
            zooKeeper.delete(ROOT_PATH, -1);
            byte[] deleteRootData = zooKeeper.getData(ROOT_PATH, true, null);
            System.out.println("删除后根节点数据:======>" + new String(deleteRootData, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            if (null != zooKeeper) {
                try {
                    zooKeeper.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
