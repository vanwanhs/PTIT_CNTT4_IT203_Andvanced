package lesson6;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector {

    public static void detect() {

        ThreadMXBean bean =
                ManagementFactory.getThreadMXBean();

        long[] threadIds =
                bean.findDeadlockedThreads();

        System.out.println("Đang quét deadlock...");

        if (threadIds == null) {

            System.out.println("Không phát hiện deadlock.");

        } else {

            System.out.println("Phát hiện deadlock!");
        }
    }
}