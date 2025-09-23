package com.kce.pos.service;

import com.kce.pos.model.*;
import java.util.HashMap;
import java.util.Map;

public class POSService {
    private Map<Integer, Supplier> suppliers = new HashMap<>();

    public void addSupplier(Supplier supplier) {
        suppliers.put(supplier.getId(), supplier);
    }

    public Supplier getSupplier(int id) {
        return suppliers.get(id);
    }

    public void receivePurchase(PurchaseOrder order, InventoryService inventory) {
        for (PurchaseOrderItem item : order.getItems()) {
            item.getProduct().increaseStock(item.getQuantity());
        }
        System.out.println("Purchase received from " + order.getSupplier().getName());
    }

    public boolean makeSale(Sale sale, InventoryService inventory) {
        for (SaleItem item : sale.getItems()) {
            Product p = item.getProduct();
            if (p.getStock() < item.getQuantity()) {
                System.out.println("Insufficient stock for product: " + p.getName());
                return false;
            }
        }
        for (SaleItem item : sale.getItems()) {
            item.getProduct().decreaseStock(item.getQuantity());
        }
        return true;
    }
}
