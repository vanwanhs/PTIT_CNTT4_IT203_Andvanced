package lesson1;

public class BookingCounter extends Thread {

    private String name;
    private TicketPool roomA;
    private TicketPool roomB;
    private boolean reverseOrder;

    public BookingCounter(String name, TicketPool roomA, TicketPool roomB, boolean reverseOrder) {
        this.name = name;
        this.roomA = roomA;
        this.roomB = roomB;
        this.reverseOrder = reverseOrder;
    }

    public void sellCombo() {

        TicketPool first = reverseOrder ? roomB : roomA;
        TicketPool second = reverseOrder ? roomA : roomB;

        synchronized (first) {

            Ticket t1 = first.getTicket();

            if (t1 == null) {
                System.out.println(name + ": Hết vé phòng " + first.getRoomName());
                return;
            }

            System.out.println(name + ": Đã lấy vé " + t1.getCode());

            try {
                Thread.sleep(500);
            } catch (Exception e) {}

            synchronized (second) {

                Ticket t2 = second.getTicket();

                if (t2 == null) {

                    first.returnTicket(t1);

                    System.out.println(name + ": Không đủ vé combo -> trả lại vé");
                    return;
                }

                System.out.println(name + " bán combo thành công: "
                        + t1.getCode() + " & " + t2.getCode());
            }
        }
    }

    @Override
    public void run() {
        sellCombo();
    }
}