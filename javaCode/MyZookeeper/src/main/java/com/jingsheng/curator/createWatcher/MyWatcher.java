package com.jingsheng.curator.createWatcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 *
 */
public class MyWatcher implements Watcher {
    public void process(WatchedEvent watchedEvent) {
        System.out.println("触发watcher，节点路径为：" + watchedEvent.getPath());
    }
}
