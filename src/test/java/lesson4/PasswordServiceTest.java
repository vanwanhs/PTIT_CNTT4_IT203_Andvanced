package lesson4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {

    PasswordService passwordService;

    @BeforeEach
    void setUp() {
        passwordService = new PasswordService();
    }

    @Test
    void passwordLength() {

        assertAll(

                // TC01: Mật khẩu mạnh
                () -> assertEquals("Mạnh",
                        passwordService.passwordLength("Abc123!@")),

                // TC02: Thiếu chữ hoa
                () -> assertEquals("Trung bình",
                        passwordService.passwordLength("abc123!@")),

                // TC03: Thiếu chữ thường
                () -> assertEquals("Trung bình",
                        passwordService.passwordLength("ABC123!@")),

                // TC04: Thiếu số
                () -> assertEquals("Trung bình",
                        passwordService.passwordLength("Abcdef!@")),

                // TC05: Thiếu ký tự đặc biệt
                () -> assertEquals("Trung bình",
                        passwordService.passwordLength("Abc12345")),

                // TC06: Quá ngắn
                () -> assertEquals("Yếu",
                        passwordService.passwordLength("Ab1!")),

                // TC07: Chỉ có chữ thường
                () -> assertEquals("Yếu",
                        passwordService.passwordLength("password")),

                // TC08: Chỉ có chữ hoa và số
                () -> assertEquals("Yếu",
                        passwordService.passwordLength("ABC12345"))

        );
    }
}