package lesson5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermissionServiceTest {

    PermissionService check = new PermissionService();

    @Test
    void canPerformAction() {
        //check
        User admin = new User(Role.ADMIN);

        assertTrue(check.canPerformAction(admin, Action.DELETE_USER));
        assertTrue(check.canPerformAction(admin, Action.LOCK_USER));
        assertTrue(check.canPerformAction(admin, Action.VIEW_PROFILE));


        // moderator
        User moderator = new User(Role.MODERATOR);

        assertFalse(check.canPerformAction(moderator,Action.DELETE_USER));
        assertTrue(check.canPerformAction(moderator, Action.LOCK_USER));
        assertTrue(check.canPerformAction(moderator,Action.VIEW_PROFILE));


        //user
        User user = new User(Role.USER);

        assertFalse(check.canPerformAction(user,Action.DELETE_USER));
        assertFalse(check.canPerformAction(user,Action.LOCK_USER));
        assertTrue(check.canPerformAction(user,Action.VIEW_PROFILE));
    }
}