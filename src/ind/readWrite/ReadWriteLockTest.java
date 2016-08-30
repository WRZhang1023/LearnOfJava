package ind.readWrite;

import org.junit.Test;

import java.util.Random;

public class ReadWriteLockTest {
    /**
     * 没有锁
     */
    @Test
    public void noSychronized() {
        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 全部锁定  读读互斥  写写互斥  读写互斥
     */
    @Test
    public void allSychronized() {

        DataWithSychronized data = new DataWithSychronized();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读写互斥  写写互斥
     */
    @Test
    public void withLock() {
        DataWithLock data = new DataWithLock();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}  