package test.thread_synchronized;

import org.junit.Test;

/**
 * Created by weirong.zhang on 16/9/1.
 */
public class ThreadSynchronized {

    @Test
    public void runNoatom1() throws InterruptedException {

        NotAtomAction notAtomAction = new NotAtomAction("zhangsan");

        //两个线程使用了相同的类实现,减少了创建对象,共享了内部变量
        Thread thread1 = new Thread(notAtomAction);
        Thread thread2 = new Thread(notAtomAction);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


    }

    @Test
    public void runNoatom2() throws InterruptedException {

        NotAtomAction notAtomAction1 = new NotAtomAction("zhangsan");
        NotAtomAction notAtomAction2 = new NotAtomAction("zhangsan");

        //使用了不通的Runable实现  不共享变量
        Thread thread1 = new Thread(notAtomAction1);
        Thread thread2 = new Thread(notAtomAction2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


    }


    @Test
    public void runAtomOnMember() throws InterruptedException {
        AtomActionOnMenber notAtomAction = new AtomActionOnMenber("zhangsan");

        //两个线程使用了相同的类实现,减少了创建对象,共享了内部变量,使用了 synchronized 锁定了共享资源,让资源的函数变成了原子操作
        Thread thread1 = new Thread(notAtomAction);
        Thread thread2 = new Thread(notAtomAction);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
    @Test
    public void runAtomOnFun() throws InterruptedException {
        AtomActionOnFunction notAtomAction = new AtomActionOnFunction("zhangsan");

        //两个线程使用了相同的类实现,减少了创建对象,共享了内部变量,使用了 synchronized 锁定了方法,让函数变成了原子操作
        Thread thread1 = new Thread(notAtomAction);
        Thread thread2 = new Thread(notAtomAction);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

}

class NotAtomAction implements Runnable {
    private String name;
    private int i;

    public NotAtomAction(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        for (char letter : name.toCharArray()) {
            System.out.println(Thread.currentThread().getName() + " " + letter);
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the time of this opention done by this object " + i);
    }
}

/**
 * synchronized 用在了共享变量上面
 */
class AtomActionOnMenber implements Runnable {
    private String name;
    private int i;

    public AtomActionOnMenber(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        synchronized (name) {
            for (char letter : name.toCharArray()) {
                System.out.println(Thread.currentThread().getName() + " " + letter);
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("the time of this opention done by this object " + i);
        }

    }
}
/**
 * synchronized 用在了共有方法上面
 */
class AtomActionOnFunction implements Runnable {
    private String name;
    private int i;

    public AtomActionOnFunction(String name) {
        this.name = name;
    }

    public synchronized void printLetter(){
        for (char letter : name.toCharArray()) {
            System.out.println(Thread.currentThread().getName() + " " + letter);
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the time of this opention done by this object " + i);
    }

    @Override
    public void run() {
        printLetter();
    }
}
