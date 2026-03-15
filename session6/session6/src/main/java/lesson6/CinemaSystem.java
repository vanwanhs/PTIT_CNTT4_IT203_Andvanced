package lesson6;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CinemaSystem {

    static Scanner sc = new Scanner(System.in);

    static List<TicketPool> pools = new ArrayList<>();
    static List<BookingCounter> counters = new ArrayList<>();

    static ExecutorService executor;

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    startSimulation();
                    break;

                case 2:
                    pauseSimulation();
                    break;

                case 3:
                    resumeSimulation();
                    break;

                case 4:
                    addTicket();
                    break;

                case 5:
                    Statistics.show(pools);
                    break;

                case 6:
                    DeadlockDetector.detect();
                    break;

                case 7:
                    System.out.println("Đang dừng hệ thống...");
                    System.exit(0);
            }
        }
    }

    static void startSimulation() {

        System.out.print("Nhập số phòng: ");
        int roomCount = sc.nextInt();

        System.out.print("Nhập số vé mỗi phòng: ");
        int ticketCount = sc.nextInt();

        System.out.print("Nhập số quầy: ");
        int counterCount = sc.nextInt();

        pools.clear();

        for (int i = 0; i < roomCount; i++) {

            char name = (char) ('A' + i);

            pools.add(new TicketPool(String.valueOf(name), ticketCount));
        }

        executor = Executors.newFixedThreadPool(counterCount);

        counters.clear();

        for (int i = 1; i <= counterCount; i++) {

            BookingCounter counter =
                    new BookingCounter("Quầy " + i, pools);

            counters.add(counter);

            executor.execute(counter);
        }

        System.out.println("Đã khởi tạo hệ thống với "
                + roomCount + " phòng.");
    }

    static void pauseSimulation() {

        for (BookingCounter c : counters)
            c.stop();

        System.out.println("Đã tạm dừng tất cả quầy bán vé.");
    }

    static void resumeSimulation() {

        executor = Executors.newFixedThreadPool(counters.size());

        for (BookingCounter c : counters)
            executor.execute(c);

        System.out.println("Đã tiếp tục hoạt động.");
    }

    static void addTicket() {

        System.out.print("Chọn phòng (A,B,C..): ");
        String room = sc.next();

        System.out.print("Số vé thêm: ");
        int amount = sc.nextInt();

        for (TicketPool p : pools) {

            if (p.getRoomName().equals(room)) {

                p.addTicket(amount);
                System.out.println("Đã thêm vé vào phòng " + room);
                return;
            }
        }

        System.out.println("Không tìm thấy phòng.");
    }
}