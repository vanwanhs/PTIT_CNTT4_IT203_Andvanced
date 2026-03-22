package lesson4;

public class Main {
    public static void main(String[] args) {
        PatientDAO dao = new PatientDAO();

        dao.findPatientByName("An");
        dao.findPatientByName("' OR '1'='1"); // test hack
    }
}