package club.iwalker.core.thread.basic.synchronize;

/**
 * sychronized 异常
 * Created by wangchen on 2016/8/7.
 */
public class SyncException {
    private int i = 0;
    public synchronized void operation() {
        while (true) try {
            i++;
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() + "，i = " + i);
            if (i == 10) {
                Integer.parseInt("a");
                //throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" log info i = " + i);
            //throw new RuntimeException();
            //continue;
        }
    }

    public static void main(String[] args) {
        final SyncException se = new SyncException();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        }, "t1");
        t1.start();
    }
}
