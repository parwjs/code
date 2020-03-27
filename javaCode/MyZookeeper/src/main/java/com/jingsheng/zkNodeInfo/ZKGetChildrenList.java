package com.jingsheng.zkNodeInfo;

import com.jingsheng.CreateCallBack.Children2CallBack;
import com.jingsheng.CreateCallBack.ChildrenCallBack;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 获取子节点数据的demo演示
 */
public class ZKGetChildrenList implements Watcher {

    private ZooKeeper zk = null;

    private static final String zkServerPath = "192.168.43.210:2181";
    private static final Integer timeout = 5000;

    public ZKGetChildrenList() {}

    public ZKGetChildrenList(String connectString) {
        try {
            zk = new ZooKeeper(connectString,timeout,new ZKGetChildrenList());
        } catch (IOException e) {
            e.printStackTrace();

            if (zk != null) {
                try {
                    zk.close();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws KeeperException, InterruptedException {
        ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);

        /**
         * 参数：
         * path：父节点路径
         * watch：true或者false，注册一个watch事件
         */

        TimeUnit.SECONDS.sleep(20);
/*        List<String> strChildList = zkServer.getZk().getChildren("/jingsheng",true);


        for (String s : strChildList) {
            System.out.println(s);
        }*/
        //异步调用
        String ctx = "{'callback':'ChildrenCallBack'}";
        zkServer.getZk().getChildren("/jingsheng",true,new ChildrenCallBack(),ctx);
        zkServer.getZk().getChildren("/jingsheng",true,new Children2CallBack(),ctx);

        latch.await();

    }

    public void process(WatchedEvent event) {

            try {
                if (event.getType() == Event.EventType.NodeChildrenChanged) {
                    System.out.println("NodeChildrenChanged");
                    ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);
                    TimeUnit.SECONDS.sleep(20);
                    List<String> strChildList = zkServer.getZk().getChildren(event.getPath(),false);
                    for (String s : strChildList) {
                        System.out.println(s);
                    }
                    latch.countDown();
                }else if (event.getType() == Event.EventType.NodeCreated) {
                    System.out.println("NodeCreated");
                    ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);
                    TimeUnit.SECONDS.sleep(20);
                    List<String> strChildList = zkServer.getZk().getChildren(event.getPath(),false);
                    for (String s : strChildList) {
                        System.out.println(s);
                    }
                    latch.countDown();
                }else if (event.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("NodeDataChanged");
                    ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);
                    TimeUnit.SECONDS.sleep(20);
                    List<String> strChildList = zkServer.getZk().getChildren(event.getPath(),false);
                    for (String s : strChildList) {
                        System.out.println(s);
                    }
                    latch.countDown();
                }else if (event.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("NodeDeleted");
                    ZKGetChildrenList zkServer = new ZKGetChildrenList(zkServerPath);
                    TimeUnit.SECONDS.sleep(20);
                    List<String> strChildList = zkServer.getZk().getChildren(event.getPath(),false);
                    for (String s : strChildList) {
                        System.out.println(s);
                    }
                    latch.countDown();
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public ZooKeeper getZk() {
        return zk;
    }
}
