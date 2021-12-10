package edu.cudenver.catalog;

// class Customer, interacts with the ShoppingCart, add items to his/her ShoppingCart

public class Customer {
    private String userName;
    private String password;
    private boolean isStocker;
    private ShoppingCart shoppingCart;

    // Customer constructor
    Customer(String userName, String password, boolean isStocker, ShoppingCart shoppingCart) {
        this.userName = userName;
        this.password = password;
        this.isStocker = isStocker;
        this.shoppingCart = shoppingCart;
    }

    public String getUserName(){ return userName; }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword(){ return password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsStocker() { return isStocker; }
    public void setIsStocker(boolean isStocker) {
        this.isStocker = isStocker;
    }

    public ShoppingCart getShoppingCart() { return shoppingCart; }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    // addItemToCart(int pid) void
}