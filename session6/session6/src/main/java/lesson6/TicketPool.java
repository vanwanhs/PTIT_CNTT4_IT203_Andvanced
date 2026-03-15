package lesson6;
import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 1; i <= capacity; i++) {
            tickets.add(new Ticket(roomName + "-" + i, roomName));
        }
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {

            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }

        return null;
    }

    public synchronized void addTicket(int amount) {

        int start = tickets.size() + 1;

        for (int i = 0; i < amount; i++) {
            tickets.add(new Ticket(roomName + "-" + (start + i), roomName));
        }
    }

    public int getTotal() {
        return tickets.size();
    }

    public int getSold() {

        int count = 0;

        for (Ticket t : tickets)
            if (t.isSold()) count++;

        return count;
    }

    public String getRoomName() {
        return roomName;
    }
}