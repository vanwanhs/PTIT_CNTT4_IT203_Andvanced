package lesson4;
import java.sql.*;
import java.util.*;
class BenhNhanDTO {
    int maBenhNhan;
    String tenBenhNhan;
    List<DichVu> dsDichVu = new ArrayList<>();
    public BenhNhanDTO(int ma, String ten) {
        this.maBenhNhan = ma;
        this.tenBenhNhan = ten;
    }
}

