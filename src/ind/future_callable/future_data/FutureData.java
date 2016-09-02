package ind.future_callable.future_data;

/**
 * Created by weirong.zhang on 16/9/2.
 */
public class FutureData {

    private RealData realData;
    private boolean flag = false;


    public synchronized RealData getRealData() {

        while (!flag) {
            try {
                System.out.println("wait....");
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return realData;
    }

    public synchronized void setRealData(RealData realData) {

        while (flag) {
            System.out.println("return....");
            return;
        }
        this.flag = true;
        this.realData = realData;
        notifyAll();
    }
}

