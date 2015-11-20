/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherwidgetfx;

import GoogleMapsGeocode.NoResultsException;
import GoogleMapsGeocode.GoogleMapsGeocoding;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import utility.Utility;

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class WeatherWidgetFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Utility.setProxy("192.168.0.1", "8080", "NOMEUTENTE", "PASSWORD");
        String address = "via Antonio Cantore 9, Brescia";
        try {
            
            City city = GoogleMapsGeocoding.getCity(address);
            Forecast forecast = Forecast.getInstance(city);
            System.out.println(forecast);
            System.exit(0);
            
        } catch (IOException ex) {
            System.out.println("Check your internet connection.");
        } catch (NoResultsException ex) {
            System.out.println("There's no place called like \" " + ex.getAddress() + " \". ");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
