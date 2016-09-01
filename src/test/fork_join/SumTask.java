package test.fork_join;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by weirong.zhang on 16/9/1.
 */
public class SumTask extends RecursiveTask<Long> {

    private ArrayList<Long> arrayList;
    private static final int threshost = 600000;
    private int start;
    private int end;


    public SumTask(int start, int end, ArrayList arrayList) {
        this.start = start;
        this.end = end;
        this.arrayList = arrayList;
    }


    public static ArrayList randomArray(int size) {

        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(new Random().nextLong());
        }
        return arrayList;
    }


    @Override
    protected Long compute() {
        Long sum = 0l;

        if (end - start < threshost) {

            while (start != end) {
                sum = sum + arrayList.get(start);
                start++;
            }
            return sum;


        } else {
            int mid = (start + end) >>> 1;

            SumTask left = new SumTask(start, mid, arrayList);
            SumTask right = new SumTask(mid + 1, end, arrayList);

            left.fork();
            right.fork();

            sum = left.join() + right.join();

        }

        return sum;
    }


    public static void main(String[] args) {

        ArrayList list = randomArray(400000);

        long starttime = System.currentTimeMillis();
        SumTask sumTash = new SumTask(0, 400000, list);
        Long result = sumTash.compute();
        long endtime = System.currentTimeMillis();
        System.out.println(endtime - starttime);
        System.out.println(result);

    }

}
