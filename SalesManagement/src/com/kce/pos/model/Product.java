package com.kce.pos.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private TaxCategory taxCategory;

    public Product(int id, String name, double price, int stock, TaxCategory taxCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.taxCategory = taxCategory;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public TaxCategory getTaxCategory() { return taxCategory; }

    public void increaseStock(int quantity) { this.stock += quantity; }
    public void decreaseStock(int quantity) { this.stock -= quantity; }
}
