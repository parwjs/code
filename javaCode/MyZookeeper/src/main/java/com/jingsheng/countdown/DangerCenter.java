package com.jingsheng.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 危险中心类
 */
public abstract class DangerCenter implements Runnable {


    private CountDownLatch latch;  //计数器
    private String station;  //调度站
    private boolean ok;  //调度站针对当前自己的站点进行检查，是否检查ok的标志


    public DangerCenter(CountDownLatch latch,String station) {
        this.latch = latch;
        this.station = station;
        this.ok = false;
    }


    public void run() {
        try {
            check();
            ok = true;
        }catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }finally {
            if (latch != null) {
                latch.countDown();
            }
        }
    }


    /**
     * 检查危化品车
     * 蒸罐
     * 汽油
     * 轮胎
     * GPS
     * ...
     */

    public abstract void check();

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}








