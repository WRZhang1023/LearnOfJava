package ind.readWrite;

/**
 * 所有的都是互斥的情况 读读互斥  写写互斥  读写互斥
 */
public class DataWithSychronized {
    private int data;// 共享数据      
    public synchronized void set(int data) {
        System.out.println(Thread.currentThread().getName() + "准备写入数据");  
        try {  
            Thread.sleep(20);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        this.data = data;  
        System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
    }     
    public synchronized void get() {
        System.out.println(Thread.currentThread().getName() + "准备读取数据");  
        try {  
            Thread.sleep(20);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
    }  
}