public class Order {
    private int orderId;
    private int itemId;
    private int quantity;
    private String orderDate;

    public Order(int orderId, int itemId, int quantity, String orderDate) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
}
