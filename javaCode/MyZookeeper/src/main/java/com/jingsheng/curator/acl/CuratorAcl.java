package com.jingsheng.curator.acl;

import com.jingsheng.utils.AclUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class CuratorAcl {

    private CuratorFramework client = null;
    private static final String zkServerPath = "192.168.43.210:2181";

    public CuratorAcl() {
        RetryPolicy retryPolicy = new RetryNTimes(3,5000);
        client = CuratorFrameworkFactory.builder().authorization("digest","red1:123456".getBytes())
                .connectString(zkServerPath)
                .sessionTimeoutMs(100000).retryPolicy(retryPolicy)
                .namespace("workspace").build();
        client.start();
    }

    private void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }

    public static void main(String[] args) throws Exception {

        //实例化
        CuratorAcl cto = new CuratorAcl();
        boolean isZKCurtorStarted = cto.client.isStarted();
        System.out.println("当前客户的状态：" + (isZKCurtorStarted ? "连接中" : "已关闭"));

        String nodePath = "/acl/father/child/sub";

        List<ACL> acls = new ArrayList<ACL>();
        Id red1 = new Id("digest", AclUtils.getDigestUserPwd("red1:123456"));
        Id red2 = new Id("digest", AclUtils.getDigestUserPwd("red2:123456"));
        acls.add(new ACL(Perms.ALL,red1));
        acls.add(new ACL(Perms.READ,red2));
        acls.add(new ACL(Perms.DELETE | ZooDefs.Perms.CREATE,red2));

        //创建节点
//        byte[] data = "spiderman".getBytes();
//        cto.client.create().creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(acls,true)
//                .forPath(nodePath,data);

//        cto.client.setACL().withACL(acls).forPath("/curatorNode");

        //更新节点数据
//        byte[] newData = "orange".getBytes();
//        cto.client.setData().withVersion(0).forPath(nodePath,newData);

        //删除节点
//        cto.client.delete().guaranteed().deletingChildrenIfNeeded().withVersion(1).forPath(nodePath);

        //读取节点数据
        Stat stat = new Stat();
        byte[] data = cto.client.getData().storingStatIn(stat).forPath(nodePath);
        System.out.println("节点" + nodePath + "的数据为：" + new String(data));
        System.out.println("该节点的版本号：" + stat.getVersion());



        cto.closeZKClient();
        boolean isZKCurtorStarted2 = cto.client.isStarted();
        System.out.println("当前客户的状态：" + (isZKCurtorStarted2 ? "连接中" : "已关闭"));

    }

}
