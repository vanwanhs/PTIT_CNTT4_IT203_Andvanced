package lesson2;
public class Supplier extends Thread {

    private TicketPool ticketPool;

    public Supplier(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ticketPool.addTickets(3);
    }
}