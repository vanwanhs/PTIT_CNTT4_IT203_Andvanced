package lesson5;

import java.util.List;
import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private List<TicketPool> pools;

    private Random random = new Random();

    public BookingCounter(String counterName, List<TicketPool> pools) {
        this.counterName = counterName;
        this.pools = pools;
    }

    @Override
    public void run() {

        while (true) {

            TicketPool pool = pools.get(random.nextInt(pools.size()));

            boolean vip = random.nextBoolean();

            Ticket ticket = pool.holdTicket(vip);

            if (ticket != null) {

                System.out.println(counterName +
                        ": Đã giữ vé " + ticket.getTicketId() +
                        (vip ? " (VIP)" : "") +
                        ". Vui lòng thanh toán trong 5s");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}

                boolean paid = pool.sellHeldTicket(ticket);

                if (paid) {
                    System.out.println(counterName +
                            ": Thanh toán thành công vé " +
                            ticket.getTicketId());
                }

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}