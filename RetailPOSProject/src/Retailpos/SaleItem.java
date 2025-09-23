package Retailpos;

public class SaleItem {
	    private Product product;
	    private int quantity;
	    private double discount; 

	    public SaleItem(Product product, int quantity, double discount) {
	        this.product = product;
	        this.quantity = quantity;
	        this.discount = discount;
	    }

	    public Product getProduct() { return product; }
	    public int getQuantity() { return quantity; }
	    public double getDiscount() { return discount; }

	    public double getSubtotal() {
	        return quantity * product.getSellingPrice();
	    }

	    public double getTax() {
	        double taxable = getSubtotal() - discount;
	        return taxable * (product.getTaxCategory().getRate() / 100);
	    }

	    public double getTotal() {
	        return (getSubtotal() - discount) + getTax();
	    }

	    @Override
	    public String toString() {
	        return product.getName() + " x" + quantity +
	               " | Subtotal: " + getSubtotal() +
	               " | Discount: " + discount +
	               " | Tax: " + getTax() +
	               " | Total: " + getTotal();
	    }
	}
