package lesson5;

public class Ticket {

    private String ticketId;
    private String roomName;

    private boolean isSold = false;
    private boolean isHeld = false;
    private boolean isVIP = false;

    private long holdExpiryTime = 0;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isHeld() {
        return isHeld;
    }

    public void setHeld(boolean held) {
        isHeld = held;
    }

    public long getHoldExpiryTime() {
        return holdExpiryTime;
    }

    public void setHoldExpiryTime(long holdExpiryTime) {
        this.holdExpiryTime = holdExpiryTime;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public String getRoomName() {
        return roomName;
    }
}