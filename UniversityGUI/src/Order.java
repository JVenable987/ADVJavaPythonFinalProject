import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private LocalDate date;
    private String orderNumber;
    private ArrayList<OrderLine> lines;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
        this.lines = new ArrayList<OrderLines>();
    }

    public Order(String orderNumber, LocalDate date){
        this.orderNumber = orderNumber;
        this.date = date;
        this.lines = new Arraylist<>();
    }

    public double total(){
        //TODO
        double total= 0;
        for( OrderLine ol : lines) {
            total += ol.subTotal();
        }
        return total;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("Order:[%-20s|%-10s]\n",this.getOrderNumber(),this.getDate()));
        for (OrderLine line: this.getLines()){
            sb.append(String.format("--->%-50s\n",line.toString()));
        }
        sb.append(String.format("---> Order Total: $%10.2f\n", this.total()));
        sb.append("============================================================================================================");
        return sb.toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<OrderLine> getLines() {
        return lines;
    }

    public void setLines(ArrayList<OrderLine> lines) {
        this.lines = lines;
    }
}