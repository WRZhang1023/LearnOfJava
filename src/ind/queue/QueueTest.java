package ind.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

/**
 * Created by weirong.zhang on 16/9/2.
 * <p>
 * 使用ArrayBlockQueue实现生产者消费着的模式
 */
public class QueueTest {

    /**
     * 非阻塞式的队列?????? 使用add() 和 remove()
     *
     * @throws InterruptedException
     */
    @Test
    public void test() throws InterruptedException {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

        System.out.println("===================");
        System.out.println("please put your eye on this print:=====no blocking =====  this test case will throw an exception ");
        System.out.println("===================");


        Thread aa = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    queue.add(i);// attention: here is a add method,compare with the follow test case;
                }

            }
        };

        Thread bb = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Integer obj = queue.remove();// attention: here is a remove method,compare with the follow test case;
                    System.out.println(obj);
                }

            }
        };

        aa.start();
        bb.start();

        aa.join();
        bb.join();

    }


    /**
     * this test case will block
     * @throws InterruptedException
     */
    @Test
    public void testBlock() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100);
        Thread aa = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Thread bb = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 102; i++) {
                    Integer obj = null;
                    try {
                        obj = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(obj);
                }

            }
        };

        aa.start();
        bb.start();

        aa.join();
        bb.join();

    }


}
