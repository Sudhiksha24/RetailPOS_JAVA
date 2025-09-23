package Retailpos;

import java.util.ArrayList;

	public class PurchaseOrder extends Transaction {
	    private Supplier supplier;
	    private ArrayList<PurchaseOrderItem> items;
	    private boolean received;

	    public PurchaseOrder(int id, Supplier supplier) {
	        super(id);
	        this.supplier = supplier;
	        this.items = new ArrayList<>();
	        this.received = false;
	    }

	    public Supplier getSupplier() { return supplier; }
	    public ArrayList<PurchaseOrderItem> getItems() { return items; }
	    public boolean isReceived() { return received; }

	    public void addItem(PurchaseOrderItem item) {
	        items.add(item);
	    }

	    public void receiveOrder() {
	        if (!received) {
	            for (PurchaseOrderItem item : items) {
	                item.getProduct().increaseStock(item.getQuantity());
	            }
	            received = true;
	        }
	    }

	    public double getTotalAmount() {
	        double sum = 0;
	        for (PurchaseOrderItem item : items) {
	            sum += item.getTotal();
	        }
	        return sum;
	    }

	    @Override
	    public void printReceipt() {
	        System.out.println("=== PURCHASE RECEIPT ===");
	        System.out.println("PO ID: " + getId() + " Supplier: " + supplier.getName());
	        for (PurchaseOrderItem item : items) {
	            System.out.println(item);
	        }
	        System.out.println("Total Amount: " + getTotalAmount());
	        System.out.println("Received: " + (received ? "Yes" : "No"));
	    }
	}
