// Items should be added to the Catalog, to essentially make up the store

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Catalog> items;       // the catalog is composed of various items

    public Catalog(){
        this.items = new ArrayList<>();
    }

    public ArrayList<Catalog> getItemsInCatalog() { return items; }
    public void setItemsInCatalog(ArrayList<Catalog> items) { this.items = items; }

}   // end class Catalog
