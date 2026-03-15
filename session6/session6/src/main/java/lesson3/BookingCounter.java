package lesson3;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool pool;
    private int soldCount = 0;
    private boolean running = true;

    public BookingCounter(String counterName, TicketPool pool) {
        this.counterName = counterName;
        this.pool = pool;
    }

    @Override
    public void run() {

        while (running) {

            Ticket ticket = pool.sellTicket();

            if (ticket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + ticket.getCode());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopCounter() {
        running = false;
    }

    public int getSoldCount() {
        return soldCount;
    }
}