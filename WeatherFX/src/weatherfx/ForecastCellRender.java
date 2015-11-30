/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfx;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
        if (empty || item == null)
        {
            setText(null);
            setGraphic(null);
        }
        else{
            BorderPane borderPane = new BorderPane();
            
            StackPane pane = new StackPane();
            Label city = new Label(item.getCity().getName());
            city.setFont(new Font("Helvetica", 22));
            pane.getChildren().add(city);
            
            
            ImageView image = new ImageView(item.getWeather().getUrlIcon());

            borderPane.setLeft(pane);
            borderPane.setRight(image);
            setGraphic(borderPane);
        }
        
        /*if(item!=null)
        {
            BorderPane borderPane = new BorderPane();
            
            StackPane pane = new StackPane();
            Label city = new Label(item.getCity().getName());
            city.setFont(new Font("Helvetica", 22));
            pane.getChildren().add(city);
            
            
            ImageView image = new ImageView(item.getWeather().getUrlIcon());

            borderPane.setLeft(pane);
            borderPane.setRight(image);
            setGraphic(borderPane);
        }*/

    }

}
