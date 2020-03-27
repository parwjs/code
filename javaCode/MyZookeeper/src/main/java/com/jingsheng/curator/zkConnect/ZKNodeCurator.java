package com.jingsheng.curator.zkConnect;

import com.jingsheng.curator.createWatcher.MyCuratorWatcher;
import com.jingsheng.curator.createWatcher.MyWatcher;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 使用curator对节点进行增删改查
 */
public class ZKNodeCurator {

    private static CuratorFramework client = null;
    private static final String zkServerPath = "192.168.43.210:2181";


    public ZKNodeCurator() {

        RetryPolicy retryPolicy = new RetryNTimes(3,5000);

        client = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(50000).retryPolicy(retryPolicy)
                .build();
        client.start();
    }

    private void closeZKClient() {
        if (client != null) {
            this.client.close();
        }
    }


    public static void main(String[] args) throws Exception {

        ZKNodeCurator curator = new ZKNodeCurator();
        final boolean isZkCuratorStarted = curator.client.isStarted();
        System.out.println("当前客户的状态：" + (isZkCuratorStarted ? "连接中" : "已关闭"));


        //创建节点
        final String nodePath = "/super/jingsheng";
//        byte[] data = "superme".getBytes();
//        curator.client.create().creatingParentsIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                .forPath(nodePath,data);
//
//        Thread.sleep(3000);

        //更新节点数据
//        byte[] newData = "batman".getBytes();
//        curator.client.setData()
//                .withVersion(0)
//                .forPath(nodePath,newData);
//
//        Thread.sleep(3000);


        //删除节点
//        curator.client.delete()
//                .guaranteed()           //如果删除失败，那么在后端还是继续会删除，直到成功
//                .deletingChildrenIfNeeded()     //如果有字节点，就删除
//                .withVersion(0)
//                .forPath(nodePath);


        //读取节点数据
//        Stat stat = new Stat();
//        byte[] data = curator.client.getData().storingStatIn(stat).forPath(nodePath);
//        System.out.println("节点" + nodePath + "的数据为：" + new String(data));
//        System.out.println("该节点的版本号为：" + stat.getVersion());


        //查询子节点
//        List<String> childNodes = curator.client.getChildren()
//                .forPath(nodePath);
//
//        System.out.println("开始打印子节点：");
//        for (String str : childNodes) {
//            System.out.println(str);
//        }

        //判断节点是否存在，如果不存在则为空
//        Stat statExist = curator.client.checkExists().forPath(nodePath);
//        System.out.println(statExist);

        // watcher  事件  当使用usingWatcher的时候，监听只会触发一次，监听完毕后就销毁
//        curator.client.getData().usingWatcher(new MyCuratorWatcher()).forPath(nodePath);
//        curator.client.getData().usingWatcher(new MyWatcher()).forPath(nodePath);


        //为节点添加watcher
        //NodeCache:监听数据节点的变更，会触发事件
//        final NodeCache nodeCache = new NodeCache(curator.client,nodePath);
//        //buildInitial : 初始化的时候获取node的值并且缓存
//        nodeCache.start(true);
//        if (nodeCache.getCurrentData() != null) {
//            System.out.println("节点初始化数据为：" + new String(nodeCache.getCurrentData().getData()));
//        }else {
//            System.out.println("节点初始化数据为空...");
//        }
//
//        nodeCache.getListenable().addListener(new NodeCacheListener() {
//            public void nodeChanged() throws Exception {
//                String data = new String(nodeCache.getCurrentData().getData());
//                System.out.println("节点路径：" + nodeCache.getCurrentData().getPath() + " 数据：" + data);
//            }
//        });



        // 为子节点添加watcher
        // PathChildrenCache：监听数据节点的增删改，会触发事件
        String childNodePathCache = nodePath;
        // cacheData：设置缓存节点的数据状态
        final PathChildrenCache childrenCache = new PathChildrenCache(curator.client,childNodePathCache,true);

        /**
         * StartMode：初始化方式
         * POST_INITIALIZED_EVENT：异步初始化，初始化之后会触发事件
         * NORMAL：异步初始化
         * BUILD_INITIAL_CACHE：同步初始化
         */
        childrenCache.start(StartMode.POST_INITIALIZED_EVENT);

        List<ChildData> childDataList = childrenCache.getCurrentData();
        System.out.println("当前数据节点的子节点数据列表：");
        for (ChildData cd : childDataList) {
            String childData = new String(cd.getData());
            System.out.println(childData);
        }

        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                if (event.getType().equals(PathChildrenCacheEvent.Type.INITIALIZED)) {
                    System.out.println("子节点初始化ok...");
                }else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                    System.out.println("添加子节点：" + event.getData().getPath());
                    System.out.println("子节点数据：" + new String(event.getData().getData()));
                }else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                    System.out.println("删除子节点：" + event.getData().getPath());
                }else if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)) {
                    System.out.println("修改子节点路径：" + event.getData().getPath());
                    System.out.println("修改子节点数据：" + new String(event.getData().getData()));
                }
            }
        });



        Thread.sleep(100000);
        curator.closeZKClient();
        boolean isZKCuratorStarted2 = curator.client.isStarted();
        System.out.println("当前客户状态：" + (isZKCuratorStarted2 ? "连接中" : "已完成"));



    }


}
