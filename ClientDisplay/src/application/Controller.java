// The Controller will interact with the Model (where we add the code)

package application;
import catalog.*;

/*
import edu.cudenver.catalog.Catalog;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
 */

import catalog.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.stage.Stage;
import server.Client;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    // client
    private Client client;

    //all
    private Catalog catalog;
    public Button btnExit;


    //tab1
    public TextField txtItemName;       // string
    public TextField txtItemId;         // int
    public TextField txtItemType;       // string
    public TextField txtItemQuantity;   // int
    public TextField txtItemPrice;      // double
    public TextField txtItemManufacturer;   // string
    public TextField txtItemModel;          // string
    public Button btnAddItem;

    //tab2
    public Tab tabCatalogList;
    public ListView<String> lstCatalog;

    //tab3
    public TextField txtUsernameTab3;
    public TextField txtPasswordTab3;
    public Button btnAddTheCustomer;
    public TextField txtStockerTab3;

    //tab4
    public TextField txtCustomerName;
    public Button btnShoppingCartSearch;
    public ListView<String> lstOfShoppingCartItems;

    //tab5
    public Button btnAddItemTab5;
    public TextField txtCustomerNameTab5;
    public TextField txtItemIdTab5;
    public TextField txtQuantityTab5;

    //public ListView<Catalog> lstCatalog;  // will store Catalog object or Item objects, un-sure
    /*
    public TextField txtStudentName;
    public DatePicker dtpStudentDOB;
    public Button btnAddUndergraduate;
    public TextField txtPhdDissertation;
    public TextField txtPhdDissertation;
    public ComboBox<String> selCourseSubject
    public TextField txtPhdDissertation;
     */


    //private University university;    // ours is catalog

    public Controller(){
        /*
        university = new University();
        this.selCourseSubject = new ComboBox<>();
        this.lstCatalog = new ListView<>();         // our catalog
         */
        catalog = new Catalog();
        client = new Client("127.0.0.1", 10000, 5);
        client.connect();
        this.lstCatalog = new ListView<>();
        this.lstOfShoppingCartItems = new ListView<>();
    }

    ////    FOR ALL TABS    //// -- load, exit, save, pre-initializing, etc.
    public void initialize(){
        // used to populate the selCourseSubject because we cannot do this in the constructor of the controller
        ArrayList<String> catalogStrings = new ArrayList<>();
        this.lstCatalog.setItems(FXCollections.observableArrayList(catalogStrings));
    }
    public void exitApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btnExit.getScene().getWindow();
        stage.close();  // will only close the window, not stop the application running
    }
    public void saveToFile(ActionEvent actionEvent) {           // saves to file
        catalog.saveToFile();
    }
    public void loadFromFile(ActionEvent actionEvent) {         // loads from the file
        this.catalog = Catalog.loadFromFile();      // will return the one saved, or an empty one
        this.initialize();                          // has to initialize the catalog as well
    }

    ////    TAB 1    //// -- Add Item (Owner)
    public void addItem(ActionEvent actionEvent) {
        try {   // if it works, it'll add it the item to the catalog
            //catalog.addItem1(this.txtItemName.getText(), Integer.parseInt(this.txtItemId.getText()), this.txtItemType.getText(), Integer.parseInt(this.txtItemQuantity.getText()),
                    //Double.parseDouble(this.txtItemPrice.getText()), this.txtItemManufacturer.getText(), this.txtItemModel.getText());
            //System.out.println(catalog);
            client.sendRequest("3|" + this.txtItemName.getText() + "|" + this.txtItemId.getText() + "|" + this.txtItemType.getText() + "|"
                                + this.txtItemQuantity.getText() + "|" + this.txtItemPrice.getText() + "|"
                                + this.txtItemManufacturer.getText() + "|" + this.txtItemModel.getText());
            cleanAddItem();
        } catch (IllegalArgumentException | IOException iae) {    // otherwise throws an iae as ERR
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());
            alert.show();
        }
    }
    private void cleanAddItem() {
    // cleans the input fields after adding an Item
        this.txtItemName.setText("");
        this.txtItemId.setText("");
        this.txtItemType.setText("");
        this.txtItemQuantity.setText("");   // could be null
        this.txtItemPrice.setText("");
        this.txtItemManufacturer.setText("");
        this.txtItemModel.setText("");
        this.txtCustomerNameTab5.setText("");
        this.txtItemIdTab5.setText("");
        this.txtQuantityTab5.setText("");
        this.txtUsernameTab3.setText("");
        this.txtPasswordTab3.setText("");
        this.txtStockerTab3.setText("");
    }

    ////     TAB 2      //// -- Catalog List (Owner)
    public void listCatalogUpdate(Event event) throws IOException {
        //int catalogSize = Integer.parseInt(client.input.readUTF());
        ArrayList<String> catalogStrings = client.sendRequestSpecial("4|");

        if (this.tabCatalogList.isSelected()) {
            this.lstCatalog.setItems(FXCollections.observableArrayList(catalogStrings));
        }
    }

    ////    TAB 3 - Add New Customer            //// -- Displays all of the items in the Catalog
    public void addCustomer(ActionEvent actionEvent) throws IOException {
        client.sendRequest("0|" + this.txtUsernameTab3.getText() + "|" + this.txtPasswordTab3.getText() + "|" + this.txtStockerTab3.getText());
        cleanAddItem();
    }

    ////    TAB 4 - Display Shopping Cart       ////
    public void findCustomer(ActionEvent actionEvent) throws IOException {
        ArrayList<String> cartList = client.sendRequestSpecial("8|" + this.txtCustomerName.getText());
        cartList.add(client.sendRequest("6|" + this.txtCustomerName.getText()));
        this.lstOfShoppingCartItems.setItems(FXCollections.observableArrayList(cartList));
    }

    ////    TAB 5 - Add Item to Customer Cart   ////
    public void addItemToCustomerCart(ActionEvent actionEvent) {
        try {   // if it works, it'll add it the item to the catalog
            //catalog.addItem1(this.txtItemName.getText(), Integer.parseInt(this.txtItemId.getText()), this.txtItemType.getText(), Integer.parseInt(this.txtItemQuantity.getText()),
            //Double.parseDouble(this.txtItemPrice.getText()), this.txtItemManufacturer.getText(), this.txtItemModel.getText());
            //System.out.println(catalog);
            client.sendRequest("2|" + this.txtCustomerNameTab5.getText() + "|" + this.txtItemIdTab5.getText() + "|" + this.txtQuantityTab5.getText());
            cleanAddItem();
        } catch (IllegalArgumentException | IOException iae) {    // otherwise throws an iae as ERR
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());
            alert.show();
        }
    }

}
