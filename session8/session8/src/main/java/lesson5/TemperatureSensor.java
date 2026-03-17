package lesson5;

import java.util.ArrayList;
import java.util.List;

public class TemperatureSensor {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void setTemperature(int temp) {
        System.out.println("Cảm biến: Nhiệt độ = " + temp);
        notifyObservers(temp);
    }

    private void notifyObservers(int temp) {
        for (Observer o : observers) {
            o.update(temp);
        }
    }
}