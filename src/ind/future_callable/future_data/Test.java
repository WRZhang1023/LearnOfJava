package ind.future_callable.future_data;

/**
 * Created by weirong.zhang on 16/9/2.
 */
public class Test {

    //
    public static void main(String[] args) {

        Test test = new Test();
        FutrueData result = test.returnFutureDate();

        System.out.println(result.getRealData().getName());


    }


    public FutrueData returnFutureDate() {
        FutrueData futureDate = new FutrueData();

        RealData realData = new RealData();

        try {
            Thread.sleep(5000);
            System.out.println("i need more time to process ...");
            realData.setAddrs("beijing ,China ");
            realData.setName("ming.wang");
            realData.setAge(20);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        futureDate.setRealData(realData);
        return futureDate;
    }

}