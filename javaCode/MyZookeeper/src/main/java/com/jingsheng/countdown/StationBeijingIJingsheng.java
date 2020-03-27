package com.jingsheng.countdown;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class StationBeijingIJingsheng extends DangerCenter{

    public StationBeijingIJingsheng(CountDownLatch latch) {
        super(latch,"北京竞生调度站");
    }

    public void check() {
        System.out.println("正在检查[" + this.getStation() + "]...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查完毕[" + this.getStation() + "]，可以发车~");
    }
}
