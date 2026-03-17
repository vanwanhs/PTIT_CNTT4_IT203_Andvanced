package lesson5;
class AirConditioner {
    private int temperature = 25;

    public void setTemperature(int temp) {
        temperature = temp;
        System.out.println("Điều hòa: Nhiệt độ = " + temperature);
    }

    public void showStatus() {
        System.out.println("Điều hòa: Nhiệt độ = " + temperature);
    }
}