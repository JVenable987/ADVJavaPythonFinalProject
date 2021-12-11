package catalog;

public class OrderLine {
    private Item item;
    private int quantity;

    public OrderLine(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double subTotal(){
        return this.item.getPrice() * this.quantity;
    }

    public String toString(){
        return String.format("Line:{%5d x %s = $%10.2f}",this.getQuantity(),this.getItem(), this.subTotal());
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}