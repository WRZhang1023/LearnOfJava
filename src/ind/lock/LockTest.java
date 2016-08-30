package ind.lock;

public class LockTest {
    public static void main(String[] args) {  
        final Outputter output = new Outputter();
        new Thread() {  
            public void run() {  
                output.output("zhangsan");  
            };  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("lisi");  
            };  
        }.start();  
    }  
}  