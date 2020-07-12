package com.accenture.acme.store.shoppingcart;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private long productId ;
    private String description;
    private int quantity;
    private double weight;
    private BigDecimal price;

    public Item(long productId, String description, int quantity, double weight, BigDecimal price) {
        this.productId = productId;
        this.description = description;
        this.quantity = quantity;
        this.weight = weight;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return productId == item.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
