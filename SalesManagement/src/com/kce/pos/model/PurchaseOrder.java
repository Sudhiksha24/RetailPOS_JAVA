package com.kce.pos.model;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder {
    private Supplier supplier;
    private List<PurchaseOrderItem> items;

    public PurchaseOrder(Supplier supplier) {
        this.supplier = supplier;
        this.items = new ArrayList<>();
    }

    public void addItem(PurchaseOrderItem item) {
        items.add(item);
    }

    public List<PurchaseOrderItem> getItems() { return items; }
    public Supplier getSupplier() { return supplier; }
}
