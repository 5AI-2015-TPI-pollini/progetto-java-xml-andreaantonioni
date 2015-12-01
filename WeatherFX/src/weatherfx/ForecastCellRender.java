/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfx;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class ForecastCellRender extends ListCell<Forecast> {

    @Override
    protected void updateItem(Forecast item, boolean empty) {
        super.updateItem(item, empty);
        /*if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            initCell();
        }*/
        initCell();

    }

    @Override
    public void updateSelected(boolean selected) {
        super.updateSelected(selected);
        Forecast item = getItem();
        if (selected) {

            /*BorderPane borderPane = new BorderPane();

            StackPane pane = new StackPane();
            Label city = new Label("CAZZO");
            city.setFont(new Font("Helvetica", 22));
            pane.getChildren().add(city);

            ImageView image = new ImageView(item.getWeather().getUrlIcon());

            borderPane.setLeft(pane);
            borderPane.setRight(image);
            setGraphic(borderPane);*/
            initCell();
            
        } else {
            initCell();
        }
    }

    /*private void initCell() {
        Forecast item = getItem();
        if(item == null)
        {
            setGraphic(null);
            return;
        }
        
        BorderPane borderPane = new BorderPane();

        StackPane pane = new StackPane();
        Label city = new Label(item.getCity().getName());
        city.setFont(new Font("Helvetica", 18));
        pane.getChildren().add(city);
        
        HBox hbox = new HBox(80);
        hbox.setAlignment(Pos.CENTER);
        
        ImageView image = new ImageView(item.getWeather().getUrlIcon());
        hbox.getChildren().add(image);
        
        Label temperature = new Label(item.getWeather().getTemperature());
        temperature.setFont(new Font("Helvetica", 30));
        hbox.getChildren().add(temperature);
        
        borderPane.setLeft(pane);
        borderPane.setRight(hbox);
        
        setGraphic(borderPane);
    }*/
    
    private void initCell() {
        Forecast item = getItem();
        if(item == null)
        {
            setGraphic(null);
            return;
        }
        
        BorderPane borderPane = new BorderPane();

        StackPane pane = new StackPane();
        Label city = new Label(item.getCity().getName());
        city.setFont(new Font("Helvetica", 18));
        pane.getChildren().add(city);
        
        GridPane gridPane = new GridPane();
        ColumnConstraints icon = new ColumnConstraints(25);
        ColumnConstraints temperatures = new ColumnConstraints(75);
        gridPane.getColumnConstraints().addAll(icon,temperatures);
        gridPane.setHgap(100);
        
        
        ImageView image = new ImageView(item.getWeather().getUrlIcon());
        gridPane.add(image, 0, 0);
        
        Label temperature = new Label(item.getWeather().getTemperature());
        temperature.setFont(new Font("Helvetica", 30));
        gridPane.add(temperature, 1, 0);
        
        borderPane.setLeft(pane);
        borderPane.setRight(gridPane);
        
        setGraphic(borderPane);
    }

}
