public class PhysicalProduct extends Product{
    private double size;

    public PhysicalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public void displayInfo(){
        System.out.println("Thông tin sản phẩn: " + getId());
        System.out.println("Tên sản phẩm: " + getName());
        System.out.println("Giá sản phẩm: " + getPrice());
        System.out.println("Kích thước sản phẩm: "+size);
    }
}
