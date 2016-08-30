package ind.readWrite;

public class Data {
    private int data;// 共享数据      
    public void set(int data) {  
        System.out.println(Thread.currentThread().getName() + "准备写入数据");  
        try {  
            Thread.sleep(20);
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        this.data = data;  
        System.out.println(Thread.currentThread().getName() + "写入" + this.data);  
    }     
    public void get() {  
        System.out.println(Thread.currentThread().getName() + "准备读取数据");  
        try {  
            Thread.sleep(20);
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println(Thread.currentThread().getName() + "读取" + this.data);  
    }  
}