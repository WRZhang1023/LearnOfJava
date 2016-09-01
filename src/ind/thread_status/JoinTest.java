package ind.thread_status;

import org.junit.Test;

/**
 * Created by weirong.zhang on 16/9/1.
 */
public class JoinTest {

    @Test
    public void runJoinWork1() throws InterruptedException {
        JoinWork1Thread j1 = new JoinWork1Thread();
        JoinWork1Thread j2 = new JoinWork1Thread();

        j1.start();
        j2.start();

        j1.join();
        j2.join();

        System.out.println("OK, all of you have done the work ,let's next");


    }

    /**
     * 线程之间进行等待
     * @throws InterruptedException
     */
    @Test
    public void runJoinWork2() throws InterruptedException {

        System.out.println("now we do the next work,but j2 needs to wait j1 have done his work ,OK?");

        JoinWork2Thread j1 = new JoinWork2Thread(null);
        JoinWork2Thread j2 = new JoinWork2Thread(j1);

        j1.start();
        j2.start();

        j2.join();

        System.out.println("OK, will done!");
    }

}

class JoinWork1Thread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " i am ready to do my work !");
        int i = 0;
        while (i != 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println(getName() + " i have done my work !");
    }
}

class JoinWork2Thread extends Thread {
    private Thread temp;

    public JoinWork2Thread(Thread temp) {
        this.temp = temp;
    }

    @Override
    public void run() {
        try {
            if (temp != null) {
                System.out.println(Thread.currentThread().getName() + " wait the " + temp.getName() + " done his work !");
                temp.join();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " i am ready to do my work !");
        int i = 0;
        while (i != 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println(getName() + " i have done my work !");
    }
}
