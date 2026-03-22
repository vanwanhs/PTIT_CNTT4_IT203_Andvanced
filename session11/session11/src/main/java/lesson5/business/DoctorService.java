package lesson5.business;

import lesson5.dao.DoctorDAO;
import lesson5.model.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public void showAll() {
        List<Doctor> list = dao.getAllDoctors();
        list.forEach(d ->
                System.out.println(d.getId() + " | " + d.getName() + " | " + d.getSpecialty())
        );
    }

    public void addDoctor(String id, String name, String specialty) {
        if (id.isEmpty() || name.isEmpty() || specialty.isEmpty()) {
            System.out.println("Không được để trống!");
            return;
        }

        boolean result = dao.insertDoctor(new Doctor(id, name, specialty));

        if (result) {
            System.out.println("Thêm thành công!");
        }
    }

    public void statistic() {
        dao.countBySpecialty();
    }
}