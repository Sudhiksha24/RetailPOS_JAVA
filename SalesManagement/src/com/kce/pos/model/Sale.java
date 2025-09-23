package com.kce.pos.model;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private List<SaleItem> items;

    public Sale() {
        this.items = new ArrayList<>();
    }

    public void addItem(SaleItem item) {
        items.add(item);
    }

    public List<SaleItem> getItems() { return items; }

    public double getTotalAmount() {
        return items.stream().mapToDouble(SaleItem::getTotalPrice).sum();
    }
}
