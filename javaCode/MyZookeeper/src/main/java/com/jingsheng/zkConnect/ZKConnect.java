package com.jingsheng.zkConnect;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class ZKConnect implements Watcher {


    final static Logger logger = LoggerFactory.getLogger(ZKConnect.class);
//    private static final String zkServerPath = "192.168.43.210:2181";
    private static final String zkServerPath = "192.168.1.9:2181";
//    private static final String zkServerPath = "192.168.43.211:2181,192.168.43.211:2182,192.168.43.211:2183";
    private static final Integer timeout = 5000;

    private static CountDownLatch latch = new CountDownLatch(1);




    public static void main(String[] args) throws IOException, InterruptedException {

        /**
         *
         * 客户端和zk服务端链接是一个异步的过程
         * 当链接成功后，客户端会收到一个watch通知
         *
         * 参数：
         * connectString：连接服务器的ip字符串，
         *      比如："192.168.43.211:2181,192.168.43.211:2182,192.168.43.211:2183"
         *      也可以是一个ip，也可以是多个ip，一个ip代表单机，多个ip代表集群
         *      也可以在ip后加路径
         * sessionTimeout：超时时间，心跳收不到了，那就超时
         * watcher：通知事件，如果有对应的事件触发，则会收到一个通知；如果不需要，那就设置为null
         * canBeReadOnly：可读，当这个物理机节点断开后，还是可以读到数据的，只是不能写，
         *                  此时数据被读取到的可能是旧数据，此处建议设置为false，不推荐使用
         * sessionId：会话的id
         * sessionPasswd：会话密码   当会话丢失后，可以依据sessionId 和 sessionPasswd 重新获取会话
         */

        ZooKeeper zk = new ZooKeeper(zkServerPath,timeout,new ZKConnect());
        logger.warn("客户端开始连接zookeeper服务器...");
        logger.warn("连接状态：{}",zk.getState());

//        new Thread().sleep(2000);
        TimeUnit.SECONDS.sleep(20);
//        Thread.sleep(10000);
//        latch.await();

        logger.warn("连接状态：{}",zk.getState());
    }


    public void process(WatchedEvent watchedEvent) {
        logger.warn("接收到watch通知：{}",watchedEvent);
    }



}
