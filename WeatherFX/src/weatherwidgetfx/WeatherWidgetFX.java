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

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class WeatherWidgetFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        /*List<Forecast> list = new ArrayList<>();
            
            ObservableList<Forecast> observableList = FXCollections.observableList(list);
            ListView<Forecast> listView = new ListView<>();
            listView.setItems(observableList);
            observableList.add(new Forecast(new Coordinates("", ""), "Brescia"));
            
            listView.setCellFactory(new Callback<ListView<Forecast>, ListCell<Forecast>>() {
            
            @Override
            public ListCell<Forecast> call(ListView<Forecast> param) {
            return new ForecastCell();
            }
            });
            
            
            StackPane root = new StackPane();
            root.getChildren().add(listView);
            
            Scene scene = new Scene(root, 300, 250);
            
            primaryStage.setTitle("Weather");
            primaryStage.setScene(scene);
            primaryStage.show();*/
        
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
