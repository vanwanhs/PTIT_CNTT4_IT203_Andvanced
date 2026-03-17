package lesson5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light();
        Fan fan = new Fan();
        AirConditioner ac = new AirConditioner();

        Command sleepMode = new SleepModeCommand(
                Arrays.asList(
                        new LightOffCommand(light),
                        new ACSetCommand(ac),
                        new FanLowCommand(fan)
                )
        );

        TemperatureSensor sensor = new TemperatureSensor();
        sensor.addObserver(new FanObserver(fan));
        sensor.addObserver(new ACObserver(ac));

        while (true) {
            System.out.println("\n1. Sleep Mode");
            System.out.println("2. Change Temperature");
            System.out.println("3. Show Status");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sleepMode.execute();
                    break;

                case 2:
                    System.out.print("Nhập nhiệt độ: ");
                    int temp = sc.nextInt();
                    sensor.setTemperature(temp);
                    break;

                case 3:
                    fan.showStatus();
                    ac.showStatus();
                    break;

                case 0:
                    return;
            }
        }
    }
}
