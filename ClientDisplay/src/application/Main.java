// Main allows us to run the MVC

package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("application.fxml"));
        primaryStage.setTitle("Catalog Application");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();


        primaryStage.setOnCloseRequest( e-> {   // for the exit button, to actually close the application
            Platform.exit();            // closing JavaFx window
            System.exit(0);      // closing the system
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
