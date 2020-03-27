package com.jingsheng.countdown;

import java.util.concurrent.CountDownLatch;

/**
 *
 */
public class StationJiangsuSanling extends DangerCenter {


    public StationJiangsuSanling(CountDownLatch latch) {
        super(latch,"江苏三林调度站");
    }

    public void check() {
        System.out.println("正在检查[" + this.getStation() + "]...");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查完毕[" + this.getStation() + "]，可以发车~");
    }
}
