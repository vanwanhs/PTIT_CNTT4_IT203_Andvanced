package lesson3;

public class Main {
    public static void main(String[] args) {
        BedDAO dao = new BedDAO();

        dao.updateBedStatus("Bed_001"); // có thể tồn tại
        dao.updateBedStatus("Bed_999"); // test lỗi
    }
}