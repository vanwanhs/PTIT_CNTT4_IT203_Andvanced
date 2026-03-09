package session2.lesson5;

public interface AdminActions {

    default void logActivity(String activity) {
        System.out.println("Admin activity: " + activity);
    }

}