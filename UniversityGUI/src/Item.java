// class Item

import edu.cudenver.university.Course;

import java.time.LocalDate;
import java.util.ArrayList;

public class Item {
    private int productID;                    // Haven't made const yet (static)
    //private static Stocker stockerAdded;    // Stocker class not defined yet
    private String type;
    private int quantity;
    private float price;            // Changed it to float, not updated on the UML
    private String manufacturer;
    private String model;

    // Constructor for Item     (STILL NEEDS STOCKER AFTER productID)
    public Item(int productID, String type, int quantity, float price, String manufacturer, String model){
        this.productID = productID;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
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
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() { return price; }
    public void setPrice(int price) {
        this.price = price;
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
        return String.format("ID: %d | Type: %s | Quantity: %d | Price: $%.2f | Manufacturer: %s | Model: %s",
                this.getProductID(), this.getType(), this.getQuantity(), this.getPrice(), this.getManufacturer(), this.getModel());
    }

}   // end class Item
