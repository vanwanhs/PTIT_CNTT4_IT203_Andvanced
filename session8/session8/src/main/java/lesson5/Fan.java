package lesson5;

class Fan {
    private String speed = "Tắt";

    public void setLow() {
        speed = "Thấp";
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void setHigh() {
        speed = "Mạnh";
        System.out.println("Quạt: Nhiệt độ cao, chạy tốc độ mạnh");
    }

    public void showStatus() {
        System.out.println("Quạt: " + speed);
    }
}