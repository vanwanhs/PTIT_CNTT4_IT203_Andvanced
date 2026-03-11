package lesson4;

public class PasswordService {

    public String passwordLength(String password) {

        if (password == null || password.length() < 8) {
            return "Yếu";
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        int count = 0;

        if (hasUpper) count++;
        if (hasLower) count++;
        if (hasDigit) count++;
        if (hasSpecial) count++;

        if (count == 4) return "Mạnh";
        if (count >= 3) return "Trung bình";

        return "Yếu";
    }
}