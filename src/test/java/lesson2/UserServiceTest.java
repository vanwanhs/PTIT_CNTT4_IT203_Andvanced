package lesson2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService = new UserService();
    @Test
    void testCase1() {
        int age = 18;
        boolean result = userService.checkRegistrationAge(age);
        assertEquals(true,result);
    }
    @Test
    void TestCase2(){
        int age = 17;
        boolean result  = userService.checkRegistrationAge(age);
        assertEquals(false,result);
    }
    @Test
    void TestCaase3(){
        int age = -1;
        assertThrows(IllegalArgumentException.class, () ->{
            userService.checkRegistrationAge(age);
        });
    }
}