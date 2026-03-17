package lesson5;

public class FanObserver implements Observer {
    private Fan fan;

    public FanObserver(Fan fan) {
        this.fan = fan;
    }

    public void update(int temp) {
        if (temp > 30) {
            fan.setHigh();
        }
    }
}