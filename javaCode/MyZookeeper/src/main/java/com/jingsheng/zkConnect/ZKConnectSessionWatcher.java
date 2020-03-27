package com.jingsheng.zkConnect;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ZKConnectSessionWatcher implements Watcher {

    private static Logger logger = LoggerFactory.getLogger(ZKConnectSessionWatcher.class);
//    private static CountDownLatch latch = new CountDownLatch(1);

    private static final String zkServerPath = "192.168.43.210:2181";
    private static final Integer timeout = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zk = new ZooKeeper(zkServerPath,timeout,new ZKConnectSessionWatcher());

        TimeUnit.SECONDS.sleep(20);

        long sessionId = zk.getSessionId();
        String ssid = "0x" + Long.toHexString(sessionId);
        System.out.println(ssid);
        byte[] sessionPassword = zk.getSessionPasswd();

        logger.warn("客户端开始连接zookeeper服务器...");
        logger.warn("连接状态，{}",zk.getState());

        TimeUnit.SECONDS.sleep(20);
        logger.warn("连接状态，{}",zk.getState());

        TimeUnit.SECONDS.sleep(1);


        //开始会话重连
        logger.warn("开始会话重连...");


        ZooKeeper zkSession = new ZooKeeper(zkServerPath,timeout,
                new ZKConnectSessionWatcher(),
                sessionId,sessionPassword);

        logger.warn("重新连接状态zkSession：{}",zkSession.getState());

        TimeUnit.SECONDS.sleep(20);

        logger.warn("重新连接状态zkSession：{}",zkSession.getState());

    }

    public void process(WatchedEvent watchedEvent) {
        logger.warn("接收到watch通知：{}",watchedEvent);
    }
}
