/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherwidgetfx;

import GoogleMapsGeocode.NoResultsException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.Utility;

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class WeatherWidgetFX extends Application {

    private ForecastArray array;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                array.exportXML();
            }
        });

        Utility.setProxy("192.168.0.1", "8080", "NOMEUTENTE", "PASSWORD");
        /*try {
         array = new ForecastArray(new File("forecast_array.xml"));
         } catch (IOException ex) {
         Logger.getLogger(WeatherWidgetFX.class.getName()).log(Level.SEVERE, null, ex);
         }*/

        array = new ForecastArray();

        String address = "via Antonio Cantore 9, Brescia";

        try {
            array.add(address);
        } catch (IOException ex) {
            Logger.getLogger(WeatherWidgetFX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoResultsException ex) {
            Logger.getLogger(WeatherWidgetFX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
