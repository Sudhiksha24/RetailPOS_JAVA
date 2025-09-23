package Retailpos;

import java.util.ArrayList;

	public class Sale extends Transaction {
	    private ArrayList<SaleItem> items;

	    public Sale(int id) {
	        super(id);
	        this.items = new ArrayList<>();
	    }

	    public void addItem(SaleItem item) {
	        items.add(item);
	        // Business rule: decrease stock immediately
	        item.getProduct().decreaseStock(item.getQuantity());
	    }

	    public double getTotalAmount() {
	        double sum = 0;
	        for (SaleItem item : items) {
	            sum += item.getTotal();
	        }
	        return sum;
	    }

	    @Override
	    public void printReceipt() {
	        System.out.println("=== SALE BILL ===");
	        System.out.println("Sale ID: " + getId());
	        for (SaleItem item : items) {
	            System.out.println(item);
	        }
	        System.out.println("Total Amount: " + getTotalAmount());
	    }
	}
