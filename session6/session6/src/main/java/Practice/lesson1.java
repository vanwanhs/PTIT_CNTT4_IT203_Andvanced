package Practice;

import java.util.Random;

public class lesson1 extends Thread{
    String[] stuents = {"Ánh","Nhung","Vánh","Hnhung","PTHongNhung","NVA"};
    Random random = new Random();
    @Override
    public void run() {
        try{
            while(true){
                int index = random.nextInt(stuents.length);
                System.out.println("Bạn: "+stuents[index]);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        lesson1 th = new lesson1();
        th.start();
    }
}
