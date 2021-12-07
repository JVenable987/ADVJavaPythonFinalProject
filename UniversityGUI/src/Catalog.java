// Items should be added to the Catalog, to essentially make up the store

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items;       // the catalog is composed of various items

    public Catalog(){
        this.items = new ArrayList<>();
    }

    public ArrayList<Item> getItemsInCatalog() { return items; }
    public void setItemsInCatalog(ArrayList<Item> items) { this.items = items; }

}   // end class Catalog
