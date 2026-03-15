package lesson6;
public class Ticket {

    private String id;
    private String room;
    private boolean sold;

    public Ticket(String id, String room) {
        this.id = id;
        this.room = room;
        this.sold = false;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getRoom() {
        return room;
    }

    public String getId() {
        return id;
    }
}