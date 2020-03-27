package com.jingsheng.curator.zkConnect;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.*;

/**
 *
 */
public class CuratorOperator {

    private CuratorFramework client = null;
    private static final String zkServerPath = "192.168.43.210:2181";

    /**
     * 实例化zk客户端
     */

    public CuratorOperator() {
        /**
         * 同步创建zk示例，原生api是异步的
         *
         * curator连接zookeeper的策略：ExponentialBackoffRetry
         * baseSleepTimeMs：初始sleep的时间
         * maxRetries：最大重试次数
         * maxSleepMs：最大重试时间
         */

//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,5);

        /**
         * curator连接zookeeper的策略：RetryNTimes
         * n：重试的次数
         * sleepMsBetweenRetries：每次重试间隔的时间
         */

        RetryPolicy retryPolicy = new RetryNTimes(3,5000);

        /**
         * curator连接zookeeper的策略：RetryOneTime
         * sleepMsBetweenRetry：每次重试间隔的时间
         */
//        RetryPolicy retryPolicy2 = new RetryOneTime(3000);

        /**
         * 永远重试，不推荐使用
         */
//        RetryPolicy retryPolicy3 = new RetryForever(retryIntervalMs);

        /**
         * curator连接zookeeper的策略：RetryUntilElapsed
         * maxElapsedTimeMs：最大重试时间
         * sleepMsBetweenRetries：每次重试间隔
         * 重试时间超过maxElapsedTimeMs后，就不再重试
         */

//        RetryPolicy retryPolicy4 = new RetryUntilElapsed(2000,3000);

        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(50000).retryPolicy(retryPolicy)
                .build();
        client.start();

    }

    /**
     * 关闭zk客户端连接
     */
    private void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        //实例化
        CuratorOperator cto = new CuratorOperator();
        boolean isZkCuratorStarted = cto.client.isStarted();
        System.out.println("当前客户的状态：" + (isZkCuratorStarted ? "连接中" : "已关闭"));

        Thread.sleep(3000);

        cto.closeZKClient();
        boolean isZkCuratorStarted2 = cto.client.isStarted();
        System.out.println("当前客户的状态：" + (isZkCuratorStarted2 ? "连接中" : "已关闭"));
    }


}
