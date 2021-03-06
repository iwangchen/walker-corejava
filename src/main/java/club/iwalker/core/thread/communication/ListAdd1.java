package club.iwalker.core.thread.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用异常停止线程
 * Created by wangchen on 2016/8/7.
 */
public class ListAdd1 {
    private volatile static List<String> list = new ArrayList<>();

    public void add() {
        list.add("walker");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 listAdd1 = new ListAdd1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        listAdd1.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素...");
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (listAdd1.size() == 5) {
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止...");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
