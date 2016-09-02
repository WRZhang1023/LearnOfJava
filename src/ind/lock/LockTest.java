package ind.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {  
        final Outputter output = new Outputter();
        new Thread() {  
            public void run() {  
                output.output("zhangsan");  
            };  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("lisi");  
            };  
        }.start();  
    }  
}

//================内部类====================
class Outputter {
    private Lock lock = new ReentrantLock();// 锁对象
    public void output(String name) {
        // TODO 线程输出方法
        lock.lock();// 得到锁
        try {
            for(int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
        } finally {
            lock.unlock();// 释放锁
        }
    }
}