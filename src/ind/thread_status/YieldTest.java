package ind.thread_status;

import org.junit.Test;

/**
 * Created by weirong.zhang on 16/9/1.
 */
public class YieldTest {


    @Test
    public void runYieldTest() throws InterruptedException {
        YieldThread y1= new YieldThread();
        YieldThread y2= new YieldThread();
        YieldThread y3= new YieldThread();
        y1.start();
        y2.start();
        y3.start();

        Thread.sleep(2000);

        y1.interrupt();
        y2.interrupt();
        y3.interrupt();
    }

}

class YieldThread extends Thread {

    @Override
    public void run() {
        int i = 0;
        while (!isInterrupted()) {
            i++;
            if (i % 10 == 0) {
                yield();
                System.out.println(getName()+" "+i);
            }

        }
    }
}
