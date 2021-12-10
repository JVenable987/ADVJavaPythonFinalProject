// The Controller will interact with the Model (where we add the code)

package edu.cudenver.application;

/*
import edu.cudenver.catalog.Catalog;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
 */

import edu.cudenver.catalog.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {
    //all
    private Catalog catalog;
    public Button btnExit;

    //tab1
    public TextField txtItemId;         // int
    public TextField txtItemType;       // string
    public TextField txtItemQuantity;   // int
    public TextField txtItemPrice;      // double
    public TextField txtItemManufacturer;   // string
    public TextField txtItemModel;          // string
    public Button btnAddItem;

    //tab2
    public Tab tabCatalogList;
    public ListView<Item> lstCatalog;


    //tab3

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
        this.lstCatalog = new ListView<>();
    }

    ////    FOR ALL TABS    //// -- load, exit, save, pre-initializing, etc.
    public void initialize(){
        // used to populate the selCourseSubject because we cannot do this in the constructor of the controller
        /*
        this.selCourseSubject.setItems(FXCollections.observableArrayList("CSCI", "MATH"));
                                                                         // can read from file too

        this.lstCatalog.setItems(FXCollections.observableArrayList(catalog.show_catalog()));
         */
        this.lstCatalog.setItems(FXCollections.observableArrayList(catalog.getItems()));
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
            catalog.addItem1(Integer.parseInt(this.txtItemId.getText()), this.txtItemType.getText(), Integer.parseInt(this.txtItemQuantity.getText()),
                    Double.parseDouble(this.txtItemPrice.getText()), this.txtItemManufacturer.getText(), this.txtItemModel.getText());
            System.out.println(catalog);
            cleanAddItem();
        } catch (IllegalArgumentException iae) {    // otherwise throws an iae as ERR
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());
            alert.show();
        }
    }
    private void cleanAddItem() {
    // cleans the input fields after adding an Item
        this.txtItemId.setText("");
        this.txtItemType.setText("");
        this.txtItemQuantity.setText("");   // could be null
        this.txtItemPrice.setText("");
        this.txtItemManufacturer.setText("");
        this.txtItemModel.setText("");
    }
    /*
    public void addUndergraduateStudent(ActionEvent actionEvent) {
    // adds an undergrad student in tab1
    // TODO: add Item To Catalog?

        university.addUndergrad(this.txtStudentName.getText(), this.dtpStudentDOB.getValue());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User added Successfully");   // creating alert object
        alert.show();                   // to show the alert
        // can also do alert.showAndWait(); for yes/no to be alerted to screen
        System.out.println(university)  // will print out the University with 1 student and 0 courses
        cleanAddStudent();
        // if we need more buttons in the button panel, just use another GridPane for nicer layout
    }
     */

    ////     TAB 2      //// -- Catalog List (Owner)
    public void listCatalogUpdate(Event event) {
        if (this.tabCatalogList.isSelected()) {
            this.lstCatalog.setItems(FXCollections.observableArrayList(catalog.getItems()));
        }
    }



    /*
    public void addCourse(ActionEvent actionEvent) {

        try {
            university.addCourse(this.selCourseSubject.getValue(), Integer.parseInt(this.txtCourseNumber.getText()),
                    this.txtCourseTitle.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Course added Successfully");
            alert.show();                   // to show the alert
        }
        catch (IllegalArgumentException iae) {
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());   // exception displays the error
            alert.show();                   // to show the alert
        }
    }
     */

    ////    TAB 3 - Catalog List    //// -- Displays all of the items in the Catalog



}
