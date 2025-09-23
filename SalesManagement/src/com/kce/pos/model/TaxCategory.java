package com.kce.pos.model;

public class TaxCategory {
    private String name;
    private double taxRate;

    public TaxCategory(String name, double taxRate) {
        this.name = name;
        this.taxRate = taxRate;
    }

    public String getName() { return name; }
    public double getTaxRate() { return taxRate; }
}
