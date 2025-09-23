package com.kce.pos.model;

public class PurchaseOrderItem {
    private Product product;
    private int quantity;

    public PurchaseOrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
