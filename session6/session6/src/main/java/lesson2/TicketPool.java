package lesson2;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private String roomName;
    private Queue<Ticket> tickets = new LinkedList<>();
    private int ticketCounter = 1;

    public TicketPool(String roomName, int initialTickets) {
        this.roomName = roomName;

        for (int i = 0; i < initialTickets; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", ticketCounter++)));
        }
    }

    // Bán vé
    public synchronized Ticket sellTicket(String counterName) {

        while (tickets.isEmpty()) {
            try {
                System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
                wait(); // quầy sẽ ngủ ở đây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Ticket ticket = tickets.poll();
        System.out.println(counterName + " bán vé " + ticket.getCode());

        return ticket;
    }

    // Nhà cung cấp thêm vé
    public synchronized void addTickets(int amount) {

        for (int i = 0; i < amount; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", ticketCounter++)));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + amount + " vé vào phòng " + roomName);

        notifyAll(); // đánh thức các quầy đang chờ
    }
}