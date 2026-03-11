package lesson1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    UserValidator userValidator = new UserValidator();
    @BeforeEach
    void setUp() {
    }

    @Test
    void Testcase1() {
        String userName = "user123";
        boolean result = userValidator.isValidUsername(userName);
        assert(result);
    }
   @Test
    void Testcase2(){
        String userName = "abc";
        boolean result = userValidator.isValidUsername(userName);
        assert(result);
   }
   @Test
    void TestCase3(){
        String username = "UserName 123";
        boolean result = userValidator.isValidUsername(username);
        assert(result);
   }
}