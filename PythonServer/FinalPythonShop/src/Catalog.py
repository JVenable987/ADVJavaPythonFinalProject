// Catalog contains all the Items in the system

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items;

    public Catalog(){
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() { return items; }
    public void setItems(ArrayList<Item> items) { this.items = items; }

    public void addItem(Item item) {
        this.items.add(item);
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
}