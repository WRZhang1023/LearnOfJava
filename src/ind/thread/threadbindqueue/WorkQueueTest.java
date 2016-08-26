package ind.thread.threadbindqueue;

/**
 * Created by weirong.zhang on 16/8/26.
 */
public class WorkQueueTest {


    public static void main(String[] args) {

        WorkQueue workQueue = new WorkQueue(2);// two threads

        for (int i = 0; i < 10; i++) {
            workQueue.execute(new Runnable() {
                                  @Override
                                  public void run() {
                                      System.out.println("hello ");
                                  }
                              }
            );
        }
        System.out.println("have pushed tasks into threadpool's queue and wait the thread to process those task .....");


    }


}
