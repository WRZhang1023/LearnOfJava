package ind.future_callable.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by weirong.zhang on 16/9/2.
 */
public class ConditionTest {

    //
    public static void main(String[] args) {

        ConditionTest test = new ConditionTest();
        FutureData result = test.returnFutureDate();

        System.out.println(result.getRealData().getName());


    }

    /**
     * 返回FutureData, 异步给FutureDate中的属性set对象
     *
     * @return
     */

    public FutureData returnFutureDate() {
        FutureData futureDate = new FutureData();

        RealData realData = new RealData();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("i need more time to process ...");
                    realData.setAddrs("beijing ,China ");
                    realData.setName("ming.wang");
                    realData.setAge(20);
                    futureDate.setRealData(realData);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) {
        }.start();

        return futureDate;
    }

}

//================内部类====================

/**
 * Created by weirong.zhang on 16/9/2.
 */
class FutureData {

    private RealData realData;
    private boolean flag = false;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public RealData getRealData() {
        lock.lock();
        try {

            while (!flag) {
                try {
                    System.out.println("wait....");
                    condition.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return realData;
        } finally {
            lock.unlock();
        }
    }

    public void setRealData(RealData realData) {
        lock.lock();
        try {
            while (flag) {
                System.out.println("return....");
                return;
            }
            this.flag = true;
            this.realData = realData;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * Created by weirong.zhang on 16/9/2.
 */
class RealData {

    private String name;
    private int age;
    private String addrs;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }
}
