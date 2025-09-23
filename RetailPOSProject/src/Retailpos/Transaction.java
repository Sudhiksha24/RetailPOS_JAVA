package Retailpos;

	public abstract class Transaction {
	    private int id;
	    public Transaction(int id) {
	        this.id = id;
	    }
	    public int getId() { return id; }
	    public abstract void printReceipt(); 
	}