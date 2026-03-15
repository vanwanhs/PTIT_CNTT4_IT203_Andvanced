package lesson6;

import java.util.List;

public class Statistics {

    public static void show(List<TicketPool> pools) {

        System.out.println("=== THỐNG KÊ HIỆN TẠI ===");

        int revenue = 0;

        for (TicketPool p : pools) {

            int sold = p.getSold();

            System.out.println(
                    "Phòng " + p.getRoomName() +
                            ": Đã bán " +
                            sold + "/" + p.getTotal() + " vé");

            revenue += sold * 250000;
        }

        System.out.println("Tổng doanh thu: " + revenue + " VNĐ");
    }
}