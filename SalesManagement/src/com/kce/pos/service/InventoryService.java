package com.kce.pos.service;

import com.kce.pos.model.Product;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Product p : products.values()) {
            System.out.printf("ID: %d | Name: %s | Stock: %d | Price: %.2f\n",
                p.getId(), p.getName(), p.getStock(), p.getPrice());
        }
    }
}
