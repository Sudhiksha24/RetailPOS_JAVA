package com.kce.pos.main;

import com.kce.pos.model.*;
import com.kce.pos.service.*;
import com.kce.pos.exception.*;
import com.kce.pos.util.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        POSService pos = new POSService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Retail POS Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Add Supplier");
            System.out.println("3. Create Purchase Order");
            System.out.println("4. Receive Purchase");
            System.out.println("5. Make Sale");
            System.out.println("6. Display Inventory");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Product Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = InputValidator.validatePositiveDouble(sc.nextDouble());
                    System.out.print("Enter Initial Stock: ");
                    int stock = InputValidator.validatePositiveInt(sc.nextInt());
                    sc.nextLine(); 
                    System.out.print("Enter Tax Category Name: ");
                    String taxName = sc.nextLine();
                    System.out.print("Enter Tax Rate (e.g., 0.18 for 18%): ");
                    double taxRate = InputValidator.validatePositiveDouble(sc.nextDouble());
                    TaxCategory productTax = new TaxCategory(taxName, taxRate); 
                    Product product = new Product(pid, pname, price, stock, productTax);
                    inventory.addProduct(product);
                    System.out.println("✅ Product added successfully.");
                    break;

                    case 2:
                        System.out.print("Enter Supplier ID: ");
                        int sid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Supplier Name: ");
                        String sname = sc.nextLine();
                        Supplier supplier = new Supplier(sid, sname);
                        pos.addSupplier(supplier);
                        System.out.println("✅ Supplier added successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Supplier ID for Purchase Order: ");
                        int supplierId = sc.nextInt();
                        Supplier selectedSupplier = pos.getSupplier(supplierId);
                        if (selectedSupplier == null) {
                            System.out.println("❌ Supplier not found.");
                            break;
                        }

                        PurchaseOrder po = new PurchaseOrder(selectedSupplier);
                        while (true) {
                            System.out.print("Enter Product ID to add to Purchase Order (or -1 to finish): ");
                            int prodId = sc.nextInt();
                            if (prodId == -1) break;

                            Product prod = inventory.getProduct(prodId);
                            if (prod == null) {
                                System.out.println("❌ Product not found.");
                                continue;
                            }

                            System.out.print("Enter Quantity: ");
                            int qty = InputValidator.validatePositiveInt(sc.nextInt());
                            po.addItem(new PurchaseOrderItem(prod, qty));
                            System.out.println("✅ Item added to Purchase Order.");
                        }
                        pos.receivePurchase(po, inventory);
                        System.out.println("🧾 Purchase Order received and stock updated.");
                        break;

                    case 4:
                        System.out.println("Receiving purchase is handled during creation (Option 3).");
                        break;

                    case 5:
                        Sale sale = new Sale();
                        while (true) {
                            System.out.print("Enter Product ID to sell (or -1 to finish): ");
                            int saleProdId = sc.nextInt();
                            if (saleProdId == -1) break;

                            Product saleProd = inventory.getProduct(saleProdId);
                            if (saleProd == null) {
                                System.out.println("❌ Product not found.");
                                continue;
                            }

                            System.out.print("Enter Quantity: ");
                            int saleQty = InputValidator.validatePositiveInt(sc.nextInt());
                            sale.addItem(new SaleItem(saleProd, saleQty));
                            System.out.println("✅ Item added to Sale.");
                        }

                        boolean success = pos.makeSale(sale, inventory);
                        if (success) {
                            System.out.println("\n🧾 Sale Bill:");
                            for (SaleItem item : sale.getItems()) {
                                Product p = item.getProduct();
                                double base = p.getPrice() * item.getQuantity();
                                double tax = base * p.getTaxCategory().getTaxRate();
                                System.out.printf("Product: %s | Qty: %d | Price: %.2f | Tax: %.2f | Total: %.2f\n",
                                        p.getName(), item.getQuantity(), base, tax, item.getTotalPrice());
                            }
                            System.out.printf("Total Amount: ₹%.2f\n", sale.getTotalAmount());
                        } else {
                            System.out.println("❌ Sale failed due to insufficient stock.");
                        }
                        break;

                    case 6:
                        inventory.displayInventory();
                        break;

                    case 7:
                        System.out.println("👋 Exiting POS System. Goodbye!");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("❌ Invalid option. Please try again.");
                }
            } catch (InvalidInputException e) {
                System.out.println("⚠️ Input Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("⚠️ Unexpected Error: " + e.getMessage());
            }
        }
    }
}
