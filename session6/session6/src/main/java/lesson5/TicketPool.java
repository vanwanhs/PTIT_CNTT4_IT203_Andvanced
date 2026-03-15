package lesson5;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 1; i <= capacity; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket holdTicket(boolean isVIP) {

        long expiry = System.currentTimeMillis() + 5000;

        for (Ticket t : tickets) {

            if (!t.isSold() && !t.isHeld()) {

                t.setHeld(true);
                t.setVIP(isVIP);
                t.setHoldExpiryTime(expiry);

                return t;
            }
        }

        return null;
    }

    public synchronized boolean sellHeldTicket(Ticket ticket) {

        if (ticket != null && ticket.isHeld() && !ticket.isSold()) {

            ticket.setSold(true);
            ticket.setHeld(false);
            return true;
        }

        return false;
    }

    public synchronized void releaseExpiredTickets() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld() && !t.isSold() && t.getHoldExpiryTime() < now) {

                System.out.println("TimeoutManager: Vé " + t.getTicketId() + " hết hạn giữ, đã trả lại kho");

                t.setHeld(false);
            }
        }
    }

    public String getRoomName() {
        return roomName;
    }
}