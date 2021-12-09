package application;

import edu.cudenver.university.Course;
import edu.cudenver.university.Student;
import edu.cudenver.university.University;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.stage.Stage;

//by Joshua Venable


public class Controller {
    public TextField txtStudentName;
    public DatePicker dtpStudentDOB;
    public Button btnAddUndergraduate;
    public Button btnAddPhD;
    public Button btnAddMaster;
    public TextField txtPhdDissertation;
    public Button btnAddCourse;
    public TextField txtCourseTitle;
    public TextField txtCourseNumber;
    public ComboBox<String> selCourseSubject;
    public Button btnExit;
    //public ListView<Customers> listCustomers;
    //public ListView<Products> listProducts;
    public Tab tabListCourses;
    public Tab tabListStudents;
    public Button btnLoad;
    public Button btnSave;
    public Button btnEnrollment;
    public TextField txtCourseEnrollmentNumber;
    public TextField txtCourseEnrollmentName;
    public TextField txtStudentEnrollmentName;
    public ListView listEnrollmentCourses;
    public ListView listEnrolledStudents;
    public Tab tabListEnrollment;

    //private University university;

    public Controller(){
        //shop = new Shop();
        this.selCourseSubject = new ComboBox<>();
        //this.listProducts = new ListView<>();
        //this.listCustomers = new ListView<>();
        this.listEnrollmentCourses = new ListView<>();
        this.listEnrolledStudents = new ListView<>();
    }

    public void initialize(){
        this.selCourseSubject.setItems(FXCollections.observableArrayList("CSCI", "MATH", "ENGL"));
        //this.listCourses.setItems(FXCollections.observableArrayList(university.getCourses()));
        //this.listStudents.setItems(FXCollections.observableArrayList(university.getStudents()));
        this.listEnrollmentCourses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Course>() {
            @Override
            public void changed(ObservableValue<? extends Course> observable, Course oldCourse, Course newCourse) {
                //System.out.println("ListView selection changed from oldValue = " + oldValue + " to newValue = " + newValue);
                updateEnrolledStudents(newCourse);
            }
        });
    }
    private void updateEnrolledStudents(Course newCourse){
        listEnrolledStudents.setItems(FXCollections.observableArrayList(newCourse.getEnrolledStudents()));
    }

    private void cleanAddStudent(){
        this.txtStudentName.setText("");
        this.dtpStudentDOB.setValue(null);
        this.txtPhdDissertation.setText("");
    }

    private void cleanAddCourse(){
        //this.txtCourseNumber.setText("");
        //this.txtCourseTitle.setText("");
    }

    public void addUnderGraduateStudent(ActionEvent actionEvent) {
        university.addUndergrad(this.txtStudentName.getText(), this.dtpStudentDOB.getValue());
        //System.out.print(university);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Undergrad added Successfully");
        alert.show();
        cleanAddStudent();
    }

    public void addPhDStudent(ActionEvent actionEvent) {
        university.addPhD(this.txtStudentName.getText(), this.dtpStudentDOB.getValue(), this.txtPhdDissertation.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"PhD added Successfully");
        alert.show();
        cleanAddStudent();
    }

    public void addMasterStudent(ActionEvent actionEvent) {
        university.addMaster(this.txtStudentName.getText(), this.dtpStudentDOB.getValue());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Student added Successfully");
        alert.show();
        cleanAddStudent();
    }

    public void addCourse(ActionEvent actionEvent) {
        try {
            university.addCourse(this.selCourseSubject.getValue(), Integer.parseInt(this.txtCourseNumber.getText()),
                    this.txtCourseTitle.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Course added Successfully");
            alert.show();
            cleanAddCourse();
        }
        catch(IllegalArgumentException iae){
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());
            alert.show();
        }
    }

    public void enrollStudent(ActionEvent actionEvent) throws IllegalArgumentException{
        try {
            university.enrollStudentToCourse(txtStudentEnrollmentName.getText(), txtCourseEnrollmentName.getText(),
                    Integer.parseInt(txtCourseEnrollmentNumber.getText()));
        }
        catch(IllegalArgumentException iae){
            Alert alert = new Alert(Alert.AlertType.ERROR, iae.getMessage());
            alert.show();
            throw iae;
        }
    }

    public void exitApplication(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btnExit.getScene().getWindow();
        stage.close();
    }

    public void listCoursesUpdate(Event event) {
        if(tabListCourses.isSelected()){
            this.listCourses.setItems(FXCollections.observableArrayList(university.getCourses()));
        }
    }

    public void listStudentUpdate(Event event) {
        if(tabListStudents.isSelected()){
            this.listStudents.setItems(FXCollections.observableArrayList(university.getStudents()));
        }
    }

    public void listEnrollmentUpdate(Event event) {
        if(tabListEnrollment.isSelected()){
            this.listEnrollmentCourses.setItems(FXCollections.observableArrayList(university.getCourses()));
        }
    }

    public void loadFromFile(ActionEvent actionEvent) {
        this.university = University.loadFromFile();
        this.initialize();
    }

    public void saveToFile(ActionEvent actionEvent) {
        university.saveToFile();
    }
}
