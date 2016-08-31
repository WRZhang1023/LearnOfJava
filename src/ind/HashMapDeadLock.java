package ind;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapDeadLock implements Callable<Integer> {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    // private static Map<Integer, Integer> results = new HashMap<>();
    // static Hashtable<Integer, Integer> results = new Hashtable<Integer, Integer>();
    public  Map<Integer, Integer> results = new ConcurrentHashMap<>();
    public  Map<Integer, Integer> results1 = new HashMap<>();
    public  StringBuffer sb = new StringBuffer();

    @Override
    public Integer call() throws Exception {
        /*
         * results.put(1, 1); results.put(2, 2); results.put(3, 3);
         */
        for (int i = 0; i < 10; i++) {
            results.put(i, i);
        }
        System.out.println("result:" + results.hashCode());

        for (int i = 0; i < 10; i++) {
            results.remove(i);
            Thread.sleep(100);
        }

//        System.out.println(" ---- " + Thread.currentThread().getName() + "     " + results.get(0));

        return results.get(1);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try {
            for (int i = 0; i < 20; i++) {
                HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
//                System.out.println("hashMapDeadLock:" + hashMapDeadLock.hashCode());
//                System.out.println("hashMapDeadLock.result:" + hashMapDeadLock.results.hashCode());
//                System.out.println("hashMapDeadLock.result1:" + hashMapDeadLock.results1.hashCode());
//                System.out.println("hashMapDeadLock.sb:" + hashMapDeadLock.sb.hashCode());
                threadPool.submit(hashMapDeadLock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
