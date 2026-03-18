public class DigitalProduct extends Product{
        private double weight;

    public DigitalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void displayInfo(){
        System.out.println("Thông tin sản phẩn: " + getId());
        System.out.println("Tên sản phẩm: " + getName());
        System.out.println("Giá sản phẩm: " + getPrice());
        System.out.println("Cân nặng sản phầm: "+weight+"kg");
    }
}
