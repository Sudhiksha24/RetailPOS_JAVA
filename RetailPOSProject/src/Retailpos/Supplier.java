package Retailpos;

public class Supplier {
	    private int id;
	    private String name;
	    private String phone;

	    public Supplier(int id, String name, String phone) {
	        this.id = id;
	        this.name = name;
	        this.phone = phone;
	    }

	    public int getId() { return id; }
	    public String getName() { return name; }
	    public String getPhone() { return phone; }

	    @Override
	    public String toString() {
	        return "Supplier[" + id + "] " + name + " (Phone: " + phone + ")";
	    }
	}