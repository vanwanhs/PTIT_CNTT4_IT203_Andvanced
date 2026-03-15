package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class lesson2 extends Thread{
    private List<String> name;
    private List<String> dress;
    public lesson2(){
        name = Arrays.asList(   "Hồng Nhung ★",
                "Hồng Nhung ☆",
                "Hồng Nhung ♡",
                "Hồng Nhung ♧",
                "Hồng Nhung ♤",
                "Hồng Nhung ♢",
                "Hồng Nhung ✿",
                "Hồng Nhung ✪",
                "Hồng Nhung ✧",
                "Hồng Nhung ✨");
        dress = Arrays.asList("Hà Nội",
                "Hải Phòng",
                "Đà Nẵng",
                "Quảng Ninh",
                "Thanh Hóa",
                "Nghệ An",
                "Huế",
                "Khánh Hòa",
                "TP Hồ Chí Minh",
                "Cần Thơ");
    }
    Random random = new Random();
    @Override
    public void run(){
        while(true){
            try{
                String student = name.get(random.nextInt(name.size()));
                String city = dress.get(random.nextInt(dress.size()));
                System.out.println("name " + student + "City " + city);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
