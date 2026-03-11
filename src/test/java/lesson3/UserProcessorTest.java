package lesson3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProcessorTest {

    UserProcessor userProcessor;

    @BeforeEach
    void setUp() {
        userProcessor = new UserProcessor();
    }

    @Test
    void TC1() {

        String result = userProcessor.ProcessEmail("user@gmail.com");

        assertEquals("user@gmail.com", result);
    }

    @Test
    void TC2() {

        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.ProcessEmail("usergmail.com");
        });

    }

    @Test
    void TC3() {

        assertThrows(IllegalArgumentException.class, () -> {
            userProcessor.ProcessEmail("user@");
        });

    }

    @Test
    void TC4() {

        String result = userProcessor.ProcessEmail("Example@Gmail.com");

        assertEquals("example@gmail.com", result);

    }
}