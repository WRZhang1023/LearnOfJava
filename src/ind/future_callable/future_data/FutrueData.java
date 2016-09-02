package ind.future_callable.future_data;

/**
 * Created by weirong.zhang on 16/9/2.
 */
public class FutrueData {

    private RealData realData;
    private boolean flag = false;


    public synchronized RealData getRealData() {

        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return realData;
    }

    public synchronized void setRealData(RealData realData) {

        if(flag){
            return;
        }
        this.flag=true;
        this.realData = realData;
        notifyAll();
    }
}

