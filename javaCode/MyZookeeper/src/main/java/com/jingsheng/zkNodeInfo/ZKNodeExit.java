package com.jingsheng.zkNodeInfo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ZKNodeExit implements Watcher {

    private ZooKeeper zk = null;
    private static final String zkServerPath = "192.168.43.210:2181";
    private static final Integer timeout = 5000;

    public ZKNodeExit() {}

    public ZKNodeExit(String connectString) {
        try {
            zk = new ZooKeeper(connectString,timeout,new ZKNodeExit());
        }catch (IOException e) {
            e.printStackTrace();
            if (zk != null) {
                try {
                    zk.close();
                }catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZKNodeExit zkServer = new ZKNodeExit(zkServerPath);
        /**
         * 参数：
         * path：节点路径
         * watch：watch
         */
        TimeUnit.SECONDS.sleep(20);
        Stat stat = zkServer.getZk().exists("/jingsheng",true);
        if (stat != null) {
            System.out.println("查询的节点版本为dataVersion：" + stat.getVersion());
        }else {
            System.out.println("该节点不存在...");
        }
        latch.await();

    }


    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeCreated) {
            System.out.println("节点创建");
            latch.countDown();
        }else if (event.getType() == Event.EventType.NodeDataChanged) {
            System.out.println("节点数据改变");
            latch.countDown();
        }else if (event.getType() == Event.EventType.NodeDeleted) {
            System.out.println("节点删除");
            latch.countDown();
        }
    }

    public ZooKeeper getZk() {
        return zk;
    }
}
