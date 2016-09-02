package ind.future_callable.future_data;

/**
 * Created by weirong.zhang on 16/9/2.
 */
public class Test {

    //
    public static void main(String[] args) {

        Test test = new Test();
        FutureData result = test.returnFutureDate();

        System.out.println(result.getRealData().getName());


    }


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
