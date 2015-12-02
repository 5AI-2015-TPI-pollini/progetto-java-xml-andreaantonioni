package weatherfx;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * WeatherFX allows you to see taday weather forecast.
 * 
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 * 
 */
public class WeatherFX extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        ForecastObservableList.importXML(new File("config.xml"));
        
        Parent root = FXMLLoader.load(getClass().getResource("/graphic/GUI.fxml"));
        
        stage.setTitle("WeatherFX");
        
        //If the user click on X to close the program
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                ForecastObservableList.exportXML();
            }
        });
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
