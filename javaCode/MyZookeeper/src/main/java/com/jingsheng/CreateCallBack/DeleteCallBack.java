package com.jingsheng.CreateCallBack;

import org.apache.zookeeper.AsyncCallback;

/**
 *
 */
public class DeleteCallBack implements AsyncCallback.VoidCallback {
    public void processResult(int rc, String path, Object ctx) {
        System.out.println(ctx);
    }
}
