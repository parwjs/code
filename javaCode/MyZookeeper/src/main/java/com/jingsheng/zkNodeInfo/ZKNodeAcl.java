package com.jingsheng.zkNodeInfo;

import com.jingsheng.utils.AclUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;

/**
 * zookeeper 操作节点acl演示
 */
public class ZKNodeAcl implements Watcher {

    private ZooKeeper zk = null;
    private static final String zkServerPath = "192.168.43.210:2181";
    private static final Integer timeout = 5000;

    public ZKNodeAcl() {}

    public ZKNodeAcl(String connectString) {
        try {
            zk = new ZooKeeper(connectString,timeout,new ZKNodeExit());
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
            result = zk.create(path,data,acls, CreateMode.PERSISTENT);

            System.out.println("创建节点：\t" + result + "\t成功...");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ZKNodeAcl zkServer = new ZKNodeAcl(zkServerPath);

        /**
         * =============== 创建node start ==============
         */
        //acl 任何人都可以访问
//        zkServer.createZKNode("/aliyun","mayun".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE);

        // 自定义用户认证访问
//        List<ACL> acls = new ArrayList<ACL>();
//        Id redred1 = new Id("digest", AclUtils.getDigestUserPwd("redred1:123456"));
//        Id redred2 = new Id("digest", AclUtils.getDigestUserPwd("redred2:123456"));
//
//        acls.add(new ACL(ZooDefs.Perms.ALL,redred1));
//        acls.add(new ACL(ZooDefs.Perms.READ,redred2));
//        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.CREATE,redred2));
//
//        zkServer.createZKNode("/aliyun/testAliyun","testAliyun".getBytes(),acls);


        //注册过的用户必须通过addAuthInfo才能操作节点，参考命令行addauth
//        zkServer.getZk().addAuthInfo("digest","redred1:123456".getBytes());
//        zkServer.createZKNode("/aliyun/testAliyun/childtest","childtest".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL);
//        Stat stat = new Stat();
//        byte[] data = zkServer.getZk().getData("/aliyun/testAliyun",false,stat);
//        System.out.println(new String(data));
//        zkServer.getZk().setData("/aliyun/testAliyun","now".getBytes(),1);


        //ip方式的acl
//        List<ACL> aclsIp = new ArrayList<ACL>();
//        Id ipId1 = new Id("ip","192.168.43.203");
//        aclsIp.add(new ACL(ZooDefs.Perms.ALL,ipId1));
//        zkServer.createZKNode("/aliyun/iptest1","iptest1".getBytes(),aclsIp);

        //验证ip是否有权限
        zkServer.getZk().setData("/aliyun/iptest1","now".getBytes(),1);
        Stat stat = new Stat();
        byte[] data = zkServer.getZk().getData("/aliyun/iptest1",false,stat);
        System.out.println(new String(data));
        System.out.println(stat.getVersion());


    }

    public void process(WatchedEvent event) {
    }

    public ZooKeeper getZk() {
        return zk;
    }
}
