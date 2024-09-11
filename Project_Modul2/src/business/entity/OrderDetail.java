package business.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable {
    private int orderId;
    private int productId;
    private String name;
    private Double unitPrice;
    private int orderQuantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int productId, String name, Double unitPrice, int orderQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.orderQuantity = orderQuantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
