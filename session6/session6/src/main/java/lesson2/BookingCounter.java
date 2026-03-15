package lesson2;

public class BookingCounter extends Thread {

    private String counterName;
    private TicketPool ticketPool;

    public BookingCounter(String counterName, TicketPool ticketPool) {
        this.counterName = counterName;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {

        while (true) {

            ticketPool.sellTicket(counterName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}