package ind.thread_status;

import org.junit.Test;

/**
 * Created by weirong.zhang on 16/9/1.
 */
public class Interrupte {


    @Test
    public void runInterruptTest() throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        myThread.interrupt(); //调用inetrrupt将线程的中断标示置为 true;
        System.out.println("interrupt");
        Thread.sleep(5000);
    }

}

class MyThread extends Thread {

    @Override
    public void run() {
        int i = 0;
        System.out.println("Thread.interrupted() " + Thread.interrupted());//"Thread.interrupted()"
        while (!isInterrupted()) {//判断线程是否 被打上中断表示,如果没有搭上则isInterrupted() 返回false
            i++;
        }
        System.out.println("Thread.interrupted() " + Thread.interrupted());// 清除 interrupt 状态
        System.out.println("isInterrupted() " + isInterrupted());// isInterrupted()
        System.out.println("Thread.interrupted() " + Thread.interrupted());
        System.out.println(i);
    }
}
