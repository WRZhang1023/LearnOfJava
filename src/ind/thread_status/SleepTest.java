package ind.thread_status;

import org.junit.Test;

/**
 * Created by weirong.zhang on 16/9/1.
 * synchronized 中存在sleep并不会释放锁
 */
public class SleepTest {

    @Test
    public void runSleep() {
        SleepThread sleepThread1 = new SleepThread();
        SleepThread sleepThread2 = new SleepThread();

        sleepThread1.start();
        sleepThread2.start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SleepThread extends Thread {

    //static 保证两个线程使用的是同一个工具
    private static Tools tools = new Tools();


    @Override
    public void run() {


        try {
            tools.work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tools {
    public synchronized void work() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ": sleep ");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + ": i got up ,but the work is also there! no one helps me to do it ");

    }
}
