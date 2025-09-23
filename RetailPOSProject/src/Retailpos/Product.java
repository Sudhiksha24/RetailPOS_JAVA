package Retailpos;

public class Product {
	    private int id;
	    private String name;
	    private double sellingPrice;
	    private double costPrice;
	    private TaxCategory taxCategory;
	    private int stock;

	    public Product(int id, String name, double sellingPrice, double costPrice, TaxCategory taxCategory) {
	        this.id = id;
	        this.name = name;
	        this.sellingPrice = sellingPrice;
	        this.costPrice = costPrice;
	        this.taxCategory = taxCategory;
	        this.stock = 0; 
	    }

	    public int getId() { return id; }
	    public String getName() { return name; }
	    public double getSellingPrice() { return sellingPrice; }
	    public double getCostPrice() { return costPrice; }
	    public TaxCategory getTaxCategory() { return taxCategory; }
	    public int getStock() { return stock; }

	    public void increaseStock(int qty) {
	        if (qty > 0) stock += qty;
	    }

	    public boolean decreaseStock(int qty) {
	        if (qty > 0 && qty <= stock) {
	            stock -= qty;
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public String toString() {
	        return "[" + id + "] " + name +
	               " | Price: " + sellingPrice +
	               " | Stock: " + stock +
	               " | Tax: " + taxCategory.getRate() + "%";
	    }
	}
