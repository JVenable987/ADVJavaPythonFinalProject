package catalog;

// class ShoppingCart
// this object is composed of Item objects

import java.util.ArrayList;

public class ShoppingCart {
    // thinking if we should have a cartID? for that unique cart?
    private ArrayList<Item> cartList;       // list of Items

    //Constructor for ShoppingCart
    public ShoppingCart(){
        this.cartList = new ArrayList<>(100);   // assumed to have a capacity of 100 items
    }

    public ArrayList<Item> getCartList(){ return cartList; }

    // No methods for appending/adding to the cartList added as of yet or toString to check if the items accurately added

}