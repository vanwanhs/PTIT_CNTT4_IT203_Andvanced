package session3.demo.demo_datetimeApi;

import java.awt.desktop.SystemSleepEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Demo_DatetimeApi {
    public static void main(String[] args) {
        LocalDate birthday;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhapaj ngayf sinh cuar banj");
        birthday = LocalDate.parse(sc.nextLine(),formatter);
        System.out.println("Ngay sinh cuar banj "+birthday);
        System.out.println("Ngayf sinh cuar banj dungs dinh dang "+ birthday.format(formatter));
    }
}
