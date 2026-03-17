package lesson5;

public class ACObserver implements Observer {
    private AirConditioner ac;

    public ACObserver(AirConditioner ac) {
        this.ac = ac;
    }

    public void update(int temp) {
        if (temp > 30) {
            ac.setTemperature(28);
        }
    }
}