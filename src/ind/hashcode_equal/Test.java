package ind.hashcode_equal;

/**
 * Created by weirong.zhang on 16/8/31.
 */
public class Test {

    /**
     * equal 比较的是什么
     * == 比较的是什么
     */

    @org.junit.Test
    public void equals() {
        User a = new User();
        User b = new User();

        System.out.println(a == b);
        System.out.println(a.equals(b));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        a = b;
        System.out.println(a == b);
        System.out.println(a.equals(b));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }


    @org.junit.Test
    public void Stringequal() {
        String a = "hello";
        String b = new String("hello");


        System.out.println(a == b);
        System.out.println(a.equals(b));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

    }

    @org.junit.Test
    public void empty() {
        User a = null;
        User b = null;

        System.out.println(a == b);
        System.out.println(a.equals(b));

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

    }

    @org.junit.Test
    public void baseDataType() {
        int a =1;
        int b= 1;

        System.out.println(a == b);
    }
}
