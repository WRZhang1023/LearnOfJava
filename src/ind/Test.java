package ind;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by weirong.zhang on 16/8/31.
 */
public class Test {


    @org.junit.Test
    public void midd() {

        //查看位移运算符  有无符号
        int a = (2 + 2) >>> 1;
//        System.out.println(a);

        int b = 4 ^ 4;
//        System.out.println(b);

        ConcurrentHashMap map = new ConcurrentHashMap();
        ConcurrentHashMap map1 = new ConcurrentHashMap();
        map.put(2,3);
        map1.put(2,3);


        System.out.println(map.hashCode());
        System.out.println(map1.hashCode());

        System.out.println(map == map1);
        System.out.println(map.equals(map1));


        //0100
        //0100

    }

    public boolean isEmpty() {
        return false;
    }


}
