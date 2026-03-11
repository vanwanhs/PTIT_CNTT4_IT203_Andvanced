package lesson6;

import java.time.LocalDate;
import java.util.List;

public class Service {

    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {

        // 1. Kiểm tra ngày sinh không được là tương lai
        if (newProfile.getBirthDate().isAfter(LocalDate.now())) {
            return null;
        }

        String newEmail = newProfile.getEmail();

        // 2. Kiểm tra email có bị trùng với user khác hay không
        if (!newEmail.equals(existingUser.getEmail())) {

            // nếu danh sách user không rỗng thì kiểm tra trùng
            if (allUsers != null) {
                for (User user : allUsers) {
                    if (user != existingUser && user.getEmail().equals(newEmail)) {
                        return null;
                    }
                }
            }
        }

        // 3. Nếu không vi phạm điều kiện thì cập nhật
        existingUser.setEmail(newEmail);
        existingUser.setBirthDate(newProfile.getBirthDate());

        return existingUser;
    }
}