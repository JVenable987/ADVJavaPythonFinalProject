import java.time.LocalDate;
import java.util.ArrayList;


public class Server(){
    private ArrayList<Customer> customers;
    private Catalog catalog;
    private ArrayList<Order> orders;
    private ArrayList<ShoppingCart> carts;


    private purchaseCartItems (ShoppingCart cart){
        //TODO
        for(Item i : cart.cartList) {
            if (this.catalog.getItem()i.getQuantity >=
        }
    }
}