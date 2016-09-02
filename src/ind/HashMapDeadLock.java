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
    public  Map<Integer, Integer> results = new ConcurrentHashMap<>();
    public StringBuffer sb = new StringBuffer();


    @Override
    public Integer call() throws Exception {

        for (int i = 0; i < 20; i++) {
            results.put(i, i);
        }
        System.out.println("result:" + results.hashCode());
        System.out.println("result.toString:" + results.toString());
        System.out.println("sb:" + sb.hashCode());

        for (int i = 0; i < 20; i++) {
            results.remove(i);
            Thread.sleep(100);
        }

        System.out.println(" ---- " + Thread.currentThread().getName() + "     " + results.get(0));

        return results.get(1);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try {
            for (int i = 0; i < 20; i++) {
                HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
                threadPool.submit(hashMapDeadLock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
