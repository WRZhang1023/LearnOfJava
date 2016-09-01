package test.four_kind_threadPool;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by weirong.zhang on 16/8/31.
 */
public class ThreadPoolExcutor {

    @Test
    public void newSingileThreadExcutor() throws InterruptedException {

        Executor excutor = Executors.newSingleThreadExecutor();


        Runnable aa = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 10; i++) {
            excutor.execute(aa);

        }

        Thread.sleep(10000);


    }


    @Test
    public  void newFixedThreadPool(){

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                System.out.println("aaaaa");
            }
        };
        executor.execute(runable);
        Callable<? extends Object> callbale = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(10000);
                return 12;
            }
        };
        Future<? extends Object> result = executor.submit(callbale);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("==========");
    }

}
