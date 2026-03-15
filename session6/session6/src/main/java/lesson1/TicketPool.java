package lesson1;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private String roomName;
    private Queue<Ticket> tickets = new LinkedList<>();

    public TicketPool(String roomName, int total) {
        this.roomName = roomName;

        for (int i = 1; i <= total; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
        }
    }

    public Ticket getTicket() {
        return tickets.poll();
    }

    public void returnTicket(Ticket t) {
        tickets.add(t);
    }

    public String getRoomName() {
        return roomName;
    }

    public boolean hasTicket() {
        return !tickets.isEmpty();
    }
}