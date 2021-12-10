package edu.cudenver.catalog;

// class Item

import java.io.Serializable;

public class Item implements Serializable {
    private int productID;                    // Haven't made const yet (static)
    //private static Stocker stockerAdded;    // Stocker class not defined yet
    private String type;
    private int quantity;
    private double price;                     // Changed to double, not updated on the UML
    private String manufacturer;
    private String model;

    // Constructor for Item     (STILL NEEDS STOCKER AFTER productID)
    public Item(int productID, String type, int quantity, double price, String manufacturer, String model){
        this.productID = productID;
        this.type = type;
        this.setQuantity(quantity);
        this.setPrice(price);
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public int getProductID(){ return productID; }
    public void setProductID(int productID){ this.productID = productID; }

    public String getType() { return type; }
    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) throws IllegalArgumentException {
        if (quantity >= 0){
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Cannot have a negative quantity for an Item!");
        }
    }

    public double getPrice() { return price; }
    public void setPrice(double price) throws IllegalArgumentException {
        if (price >= 0){
            this.price = price;
        } else {
            throw new IllegalArgumentException("Cannot have a negative price for an Item!");
        }
    }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() { return model; }
    public void setModel(String model) {
        this.model = model;
    }

    // Needs Stocker still after Stocker class added ToString()
    public String toString(){
        return String.format("ITEM: [ID: %d | Type: %s | Quantity: %d | Price: $%.2f | Manufacturer: %s | Model: %s]",
                this.getProductID(), this.getType(), this.getQuantity(), this.getPrice(), this.getManufacturer(), this.getModel());
    }

}   // end class Item