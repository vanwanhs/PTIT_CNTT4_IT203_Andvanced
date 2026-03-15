package demo_thread;

public class NhuCauThread {
    public static void main(String[] args) {
        boolean flag = true;
        while(flag){
            System.out.println("Xin chào Hnhung");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        while (true){
            System.out.println("Chào mừng các bạn đến với Thread");
        }
    }
}
