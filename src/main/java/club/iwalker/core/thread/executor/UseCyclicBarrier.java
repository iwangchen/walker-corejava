package club.iwalker.core.thread.executor;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 针对多个线程
 * Created by wangchen on 2016/8/14.
 */
public class UseCyclicBarrier {
    static class Runner implements Runnable {
        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random().nextInt(5)));
                System.out.println(name + " 准备 ok.");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + "Go !!");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3); //3 一共3个运动员，如果四个的话，会一直等待
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(new Thread(new Runner(barrier, "zhangsan")));
        executor.submit(new Thread(new Runner(barrier, "lisi")));
        executor.submit(new Thread(new Runner(barrier, "wangwu")));

        executor.shutdown();
    }
}
