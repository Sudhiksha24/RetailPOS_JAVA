package com.kce.pos.model;

public class SaleItem {
    private Product product;
    private int quantity;

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

    public double getTotalPrice() {
        double base = product.getPrice() * quantity;
        double tax = base * product.getTaxCategory().getTaxRate();
        return base + tax;
    }
}
