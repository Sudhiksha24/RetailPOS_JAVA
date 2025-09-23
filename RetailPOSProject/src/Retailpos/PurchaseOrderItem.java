package Retailpos;

public class PurchaseOrderItem {
	    private Product product;
	    private int quantity;
	    private double unitCost;

	    public PurchaseOrderItem(Product product, int quantity, double unitCost) {
	        this.product = product;
	        this.quantity = quantity;
	        this.unitCost = unitCost;
	    }

	    public Product getProduct() { return product; }
	    public int getQuantity() { return quantity; }
	    public double getUnitCost() { return unitCost; }

	    public double getSubtotal() {
	        return quantity * unitCost;
	    }

	    public double getTax() {
	        return getSubtotal() * (product.getTaxCategory().getRate() / 100);
	    }

	    public double getTotal() {
	        return getSubtotal() + getTax();
	    }

	    @Override
	    public String toString() {
	        return product.getName() + " x" + quantity + " @ " + unitCost +
	               " | Subtotal: " + getSubtotal() +
	               " | Tax: " + getTax();
	    }
	}
