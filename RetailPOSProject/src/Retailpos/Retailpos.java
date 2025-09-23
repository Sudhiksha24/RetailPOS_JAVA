package Retailpos;

import java.util.*;

public class Retailpos {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();

    private static int productId = 1;
    private static int supplierId = 1;
    private static int poId = 1;
    private static int saleId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Retail POS Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Add Supplier");
            System.out.println("3. Create Purchase Order");
            System.out.println("4. Receive Purchase");
            System.out.println("5. Make Sale");
            System.out.println("6. Display Inventory");
            System.out.println("7. Exit");

            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: addProduct(); break;
                case 2: addSupplier(); break;
                case 3: createPurchaseOrder(); break;
                case 4: receivePurchase(); break;
                case 5: makeSale(); break;
                case 6: displayInventory(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void addProduct() {
        String name = readString("Enter product name: ");
        double sellPrice = readDouble("Selling price: ");
        double costPrice = readDouble("Cost price: ");
        System.out.println("Choose Tax Category: 1.Exempt 2.GST5 3.GST12 4.GST18");
        int t = readInt("Enter: ");
        TaxCategory tax = TaxCategory.EXEMPT;
        if (t == 2) tax = TaxCategory.GST5;
        else if (t == 3) tax = TaxCategory.GST12;
        else if (t == 4) tax = TaxCategory.GST18;
        Product p = new Product(productId++, name, sellPrice, costPrice, tax);
        products.add(p);
        System.out.println("Product added: " + p);
    }

    private static void addSupplier() {
        String name = readString("Enter supplier name: ");
        String phone = readString("Enter phone: ");
        Supplier s = new Supplier(supplierId++, name, phone);
        suppliers.add(s);
        System.out.println("Supplier added: " + s);
    }

    private static void createPurchaseOrder() {
        if (suppliers.isEmpty() || products.isEmpty()) {
            System.out.println("Add suppliers and products first!");
            return;
        }
        listSuppliers();
        int sid = readInt("Enter supplier ID: ");
        Supplier supplier = findSupplierById(sid);
        if (supplier == null) { System.out.println("Invalid supplier."); return; }

        PurchaseOrder po = new PurchaseOrder(poId++, supplier);
        while (true) {
            listProducts();
            int pid = readInt("Enter product ID (0 to stop): ");
            if (pid == 0) break;
            Product product = findProductById(pid);
            if (product == null) { System.out.println("Invalid product."); continue; }
            int qty = readInt("Quantity: ");
            double cost = readDouble("Unit cost: ");
            po.addItem(new PurchaseOrderItem(product, qty, cost));
        }
        purchaseOrders.add(po);
        po.printReceipt();
    }

    private static void receivePurchase() {
        if (purchaseOrders.isEmpty()) { System.out.println("No purchase orders!"); return; }
        for (PurchaseOrder po : purchaseOrders) {
            System.out.println("PO " + po.getId() + " | Supplier: " + po.getSupplier().getName() + " | Received: " + po.isReceived());
        }
        int id = readInt("Enter PO ID to receive: ");
        for (PurchaseOrder po : purchaseOrders) {
            if (po.getId() == id) {
                po.receiveOrder();
                po.printReceipt();
                return;
            }
        }
        System.out.println("Invalid PO ID.");
    }

    private static void makeSale() {
        Sale sale = new Sale(saleId++);
        while (true) {
            listProducts();
            int pid = readInt("Enter product ID (0 to stop): ");
            if (pid == 0) break;
            Product product = findProductById(pid);
            if (product == null) { System.out.println("Invalid product."); continue; }
            int qty = readInt("Quantity: ");
            if (qty > product.getStock()) {
                System.out.println("Insufficient stock!");
                continue;
            }
            double discount = readDouble("Discount (0 if none): ");
            sale.addItem(new SaleItem(product, qty, discount));
        }
        sales.add(sale);
        sale.printReceipt();
    }

    private static void displayInventory() {
        System.out.println("=== INVENTORY ===");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void listProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void listSuppliers() {
        for (Supplier s : suppliers) {
            System.out.println(s);
        }
    }

    private static Product findProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private static Supplier findSupplierById(int id) {
        for (Supplier s : suppliers) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private static int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }

    private static double readDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine());
    }

    private static String readString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}