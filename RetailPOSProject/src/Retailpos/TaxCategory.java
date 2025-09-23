package Retailpos;

public enum TaxCategory {
	    EXEMPT(0.0),
	    GST5(5.0),
	    GST12(12.0),
	    GST18(18.0);
	    private double rate;
	    TaxCategory(double rate) {
	        this.rate = rate;
	    }
	    public double getRate() {
	        return rate;
	    }
	}
