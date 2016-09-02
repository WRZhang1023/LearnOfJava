package test.fork_join;

import java.util.concurrent.RecursiveTask;

/**
 * Created by weirong.zhang on 16/9/1.
 */
public class SumTask2 extends RecursiveTask<Long> {

    public SumTask2(int low, int higt, long[] array) {
        this.low = low;
        this.higt = higt;
        this.array = array;
    }


    private static final int THRESHOLD = 1000000;

    private int low;
    private int higt;

    private long[] array;

    @Override
    protected Long compute() {
        Long sum = 0l;

        if (higt - low < THRESHOLD) {

            while (low != higt) {
                sum += array[low];
                low++;
            }

        } else {
            int mid = (low + higt) >>> 1;

            SumTask2 left = new SumTask2(low, mid, array);
            SumTask2 right = new SumTask2(mid + 1, higt, array);

            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }


        return sum;
    }


    public static void main(String[] args) {


        long[] array = new long[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = i;
        }
        SumTask2 task = new SumTask2(0, 10000, array);
        Long result = task.compute();
        System.out.println(result);

    }
}
