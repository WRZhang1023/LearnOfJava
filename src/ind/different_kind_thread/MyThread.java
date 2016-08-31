package ind.different_kind_thread;

/**
 * Created by weirong.zhang on 16/8/30.
 */
public class MyThread extends Thread{

    public void run() {
        System.out.println(Thread.currentThread().getName() + " 正在执行。。。(注意线程的名称)");
    }
}
