package ind.threadSychronized;

public class TraditionalThreadSynchronized {
    public static void main(String[] args) {  
        final Outputter output = new Outputter();  
        new Thread() {  
            public void run() {  
                output.output("zhangsan");  
            }  
        }.start();        
        new Thread() {  
            public void run() {  
                output.output("lisi");  
            }  
        }.start();  
    }  
}  