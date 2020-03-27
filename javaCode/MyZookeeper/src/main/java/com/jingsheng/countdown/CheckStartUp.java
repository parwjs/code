package com.jingsheng.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 */
public class CheckStartUp {

    private static List<DangerCenter> stationList;
    private static CountDownLatch latch;

     public CheckStartUp() {}

     private static boolean checkAllStations() throws Exception {
         //初始化3个调度站

         latch = new CountDownLatch(3);
         //把所有站点添加进list
         stationList = new ArrayList<DangerCenter>();
         stationList.add(new StationBeijingIJingsheng(latch));
         stationList.add(new StationShandongChangchun(latch));
         stationList.add(new StationJiangsuSanling(latch));

         //使用线程池
         Executor service = Executors.newFixedThreadPool(stationList.size());

         for(DangerCenter center : stationList) {
            service.execute(center);
         }

         //等待线程执行完毕
         latch.await();

        for (DangerCenter center : stationList) {
            if (!center.isOk()) {
                return false;
            }
        }

        return true;

     }

    public static void main(String[] args) throws Exception {
        boolean result = CheckStartUp.checkAllStations();
        System.out.println("监控中心针对所有危险品调度站点的检查结果为：" + result);
    }

}
