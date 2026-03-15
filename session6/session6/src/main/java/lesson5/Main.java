package lesson5;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 6);

        List<TicketPool> pools = Arrays.asList(roomA, roomB, roomC);

        Thread timeoutManager =
                new Thread(new TimeoutManager(pools));

        timeoutManager.start();

        for (int i = 1; i <= 5; i++) {

            Thread counter =
                    new Thread(new BookingCounter("Quầy " + i, pools));

            counter.start();
        }
    }
}