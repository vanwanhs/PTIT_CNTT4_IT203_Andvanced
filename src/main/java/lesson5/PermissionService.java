package lesson5;
public class PermissionService {

    public boolean canPerformAction(User user, Action action) {

        Role role = user.getRole();

        if (role == Role.ADMIN) {
            return true;
        }

        if (role == Role.MODERATOR) {

            if (action == Action.DELETE_USER) {
                return false;
            }

            return true;
        }

        if (role == Role.USER) {

            if (action == Action.VIEW_PROFILE) {
                return true;
            }

            return false;
        }

        return false;
    }
}