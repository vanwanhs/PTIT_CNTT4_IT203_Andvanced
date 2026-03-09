package session2.lesson5;

public class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {

        // chọn dùng method của AdminActions
        AdminActions.super.logActivity(activity);

        // hoặc có thể gọi cả hai
        UserActions.super.logActivity(activity);
    }

}