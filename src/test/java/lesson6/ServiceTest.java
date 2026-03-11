package lesson6;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void updateProfile() {

        Service service = new Service();

        User existingUser = new User(
                "user@gmail.com",
                LocalDate.of(2000, 1, 1)
        );

        List<User> allUsers = new ArrayList<>();
        allUsers.add(existingUser);
        allUsers.add(new User(
                "other@gmail.com",
                LocalDate.of(1999, 5, 5)
        ));

        // CASE 1: Email hợp lệ + ngày sinh hợp lệ
        UserProfile profile1 = new UserProfile(
                "new@gmail.com",
                LocalDate.of(1998, 1, 1)
        );

        User result1 = service.updateProfile(existingUser, profile1, allUsers);
        assertNotNull(result1);


        // CASE 2: Ngày sinh trong tương lai
        UserProfile profile2 = new UserProfile(
                "new@gmail.com",
                LocalDate.now().plusDays(1)
        );

        User result2 = service.updateProfile(existingUser, profile2, allUsers);
        assertNull(result2);


        // CASE 3: Email trùng với user khác
        UserProfile profile3 = new UserProfile(
                "other@gmail.com",
                LocalDate.of(1998, 1, 1)
        );

        User result3 = service.updateProfile(existingUser, profile3, allUsers);
        assertNull(result3);


        // CASE 4: Email giữ nguyên
        UserProfile profile4 = new UserProfile(
                "user@gmail.com",
                LocalDate.of(1995, 5, 5)
        );

        User result4 = service.updateProfile(existingUser, profile4, allUsers);
        assertNotNull(result4);


        // CASE 5: Danh sách user rỗng
        List<User> emptyUsers = new ArrayList<>();

        UserProfile profile5 = new UserProfile(
                "new@gmail.com",
                LocalDate.of(1997, 3, 3)
        );

        User result5 = service.updateProfile(existingUser, profile5, emptyUsers);
        assertNotNull(result5);


        // CASE 6: Email trùng + ngày sinh tương lai
        UserProfile profile6 = new UserProfile(
                "other@gmail.com",
                LocalDate.now().plusDays(2)
        );

        User result6 = service.updateProfile(existingUser, profile6, allUsers);
        assertNull(result6);
    }
}