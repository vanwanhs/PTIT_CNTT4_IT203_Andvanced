package lesson3;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        RemoteControl remote = new RemoteControl();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Gán nút");
            System.out.println("2. Nhấn nút");
            System.out.println("3. Undo");
            System.out.println("4. Thoát");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Nhập số nút:");
                    int slot = sc.nextInt();

                    System.out.println("Chọn lệnh:");
                    System.out.println("1. Bật đèn");
                    System.out.println("2. Tắt đèn");
                    System.out.println("3. Bật quạt");
                    System.out.println("4. Tắt quạt");
                    System.out.println("5. Set điều hòa");

                    int type = sc.nextInt();

                    Command cmd = null;

                    switch (type) {
                        case 1:
                            cmd = new LightOnCommand(light);
                            break;
                        case 2:
                            cmd = new LightOffCommand(light);
                            break;
                        case 3:
                            cmd = new FanOnCommand(fan);
                            break;
                        case 4:
                            cmd = new FanOffCommand(fan);
                            break;
                        case 5:
                            System.out.println("Nhập nhiệt độ:");
                            int temp = sc.nextInt();
                            cmd = new ACSetTemperatureCommand(ac, temp);
                            break;
                    }

                    if (cmd != null) {
                        remote.setCommand(slot, cmd);
                    }
                    break;

                case 2:
                    System.out.println("Nhập nút:");
                    int press = sc.nextInt();
                    remote.pressButton(press);
                    break;

                case 3:
                    remote.undo();
                    break;

                case 4:
                    return;
            }
        }
    }
}
