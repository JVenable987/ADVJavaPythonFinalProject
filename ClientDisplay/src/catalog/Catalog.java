package catalog;

// Catalog contains all the Items in the system

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;

public class Catalog implements Serializable {
    private ArrayList<Item> items;

    public static final String filename = "./catalog.ser";

    public Catalog(){
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() { return items; }
    public void setItems(ArrayList<Item> items) { this.items = items; }

    public void addItemObject(Item item) {    //if the Item is already in Object form
        this.items.add(item);
    }

    public void addItem1(int id, String type, int quantity, double price, String manufacturer, String model) {
        this.items.add(new Item(id, type, quantity, price, manufacturer, model));
    }

    public void showCatalog(){
        // to show everything that is in the catalog (should be all items)
        for (Item i: items){
            System.out.println(i);
        }
    }

    public void sizeOfCatalog(){
        System.out.println("Catalog has: " + items.size() + " items");
    }

    public String toString(){
        return String.format("Catalog has %d items", this.getItems().size());
    }


    /////////////////////////////////////////////////////////////////////////
    public void saveToFile(){

        ObjectOutputStream oos = null;

        try{
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        finally {
            if (oos != null){
                try{
                    oos.close();
                }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static Catalog loadFromFile(){
        // delegating responsibility to class University to load from file and return that file
        ObjectInputStream ois = null;
        Catalog catalog = null;

        try{
            ois = new ObjectInputStream(new FileInputStream(filename));
            catalog = (Catalog)ois.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
            catalog = new Catalog();  // an empty University
        }
        finally{
            if (ois != null){
                try{
                    ois.close();
                }
                catch(IOException ioe){ioe.printStackTrace();}
            }
        }
        return catalog;  // return the object, need to return that university above
    }
}   // end Catalog