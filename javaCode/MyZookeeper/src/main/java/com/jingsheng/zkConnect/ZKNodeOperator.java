package com.jingsheng.zkConnect;

import com.jingsheng.CreateCallBack.*;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public class ZKNodeOperator implements Watcher {

    private ZooKeeper zk = null;

    private static final String zkServerPath = "192.168.43.210:2181";
    private static final Integer timeout = 5000;

    public ZKNodeOperator() {}

    public ZKNodeOperator(String connectString) {
        try {
            zk = new ZooKeeper(connectString,timeout,new ZKNodeOperator());
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

    //创建节点
    private void createZKNode(String path, byte[] data, List<ACL> acls) {
        String result = "";

        try {
            /**
             * 同步或异步创建节点，都不支持子节点的递归创建，异步有一个callback函数
             * 参数：
             * path：创建的路径
             * data：存储的数据的byte[]
             * acl：控制权限策略
             *          Ids.OPEN_ACL_UNSAFE --> world:anyone:cdrwa
             *          CREATOR_ALL_ACL --> auth:user:password:cdrwa
             * createMode：节点类型，是一个类型
             *          PERSISTENT：持久节点
             *          PERSISTENT_SEQUENTIAL：持久顺序节点
             *          EPHEMERAL：临时节点
             *          EPHEMERAL_SEQUENTIAL：临时顺序节点
              */
//            result = zk.create(path,data,acls, CreateMode.EPHEMERAL);

            String ctx = "{'create':'success'}";
            zk.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(),ctx);


            System.out.println("创建节点：\t" + result + "\t成功...");
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    //修改ZK节点
    private void modifyZKNode(String path,byte[] data,int version) {
        Stat result = null;
        try {

            //同步
//            result = zk.setData(path,data,version);
            //异步
            String ctx = "{'modify':'success'}";
            zk.setData(path,data,version,new StatusCallBack(),ctx);

//            System.out.println("修改节点：\t" + result + "\t成功...");
//            System.out.println("修改后的版本：\t" + result.getVersion());
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    //删除ZK节点
    private void deleteZKNode(String path,int version) {

        try {
            //同步
//            zk.delete(path,version);
            //异步
            String ctx = "{'delete':'success'}";
            zk.delete(path,version,new DeleteCallBack(),ctx);

            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询ZK节点
    private void selectZKNode() {


    }


    public static void main(String[] args) throws KeeperException, InterruptedException {

        ZKNodeOperator zkServer = new ZKNodeOperator(zkServerPath);

        //创建zk节点
//        zkServer.createZKNode("/testnode","testnode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        /**
         * 参数：
         * path：节点路径
         * data：数据
         * version：数据状态
         */

//        zkServer.modifyZKNode("/testnode","hello".getBytes(),2);

        zkServer.deleteZKNode("/testnode",0);




    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void process(WatchedEvent watchedEvent) {

    }
}
