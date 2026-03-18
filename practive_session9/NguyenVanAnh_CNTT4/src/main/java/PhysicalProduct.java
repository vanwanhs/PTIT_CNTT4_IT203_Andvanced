public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public void displayInfo() {
        System.out.println("[Physical] ID: " + getId() +
                " | Name: " + getName() +
                " | Price: " + getPrice() +
                " | Weight: " + weight);
    }
}