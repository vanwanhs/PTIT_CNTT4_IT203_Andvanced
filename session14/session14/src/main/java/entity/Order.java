package entity;
public class Order {
    private int orderId;
    private int userId;
    private String status;

    public Order(int orderId, int userId, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
