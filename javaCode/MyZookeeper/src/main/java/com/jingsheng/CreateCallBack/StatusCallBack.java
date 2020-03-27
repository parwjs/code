package com.jingsheng.CreateCallBack;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.data.Stat;

/**
 *
 */
public class StatusCallBack implements StatCallback {
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("修改的节点：" + path);
        System.out.println("修改后的版本：" + stat.getVersion());
    }
}
