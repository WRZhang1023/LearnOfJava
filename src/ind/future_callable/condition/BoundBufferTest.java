package ind.future_callable.condition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 * Created by weirong.zhang on 16/9/2.
 */
public class BoundBufferTest {

    public static void main(String[] args) {
        BoundBuffer buffer = new BoundBuffer();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    buffer.addObject(i);

                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 150; i++) {
                    System.out.println(buffer.getOjbect());

                }
            }
        }.start();
    }

}

//==========inner class ==========


/**
 * Created by weirong.zhang on 16/9/2.
 */
class BoundBuffer {
    private final Object[] buffer = new Object[100];
    final Lock lock = new ReentrantLock();
    final Condition Notfull = lock.newCondition();
    final Condition NotEmpty = lock.newCondition();
    private int counted/*计数器*/, addPrt/*写指针*/, getPrt /*读指针*/ = 0;


    public void addObject(Object x) {
        lock.lock();
        try {
            while (counted == buffer.length) {//数组满了,挂起写入线程
                System.out.println("Sorry, I am full.....");
                Notfull.await();
                System.out.println("this world will not print,until Notfull is notified by others");

            }
            buffer[addPrt++] = x;
            counted++;
            if (addPrt == buffer.length) {
                addPrt = 0;
            }
            NotEmpty.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    public Object getOjbect() {
        lock.lock();
        Object obj = null;
        try {
            while (counted == 0) {
                try {
                    NotEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            obj = buffer[getPrt++];
            counted--;
            if (getPrt == buffer.length) {
                getPrt = 0;
            }
            Notfull.signal();


        } finally {
            lock.unlock();
        }

        return obj;
    }

}

