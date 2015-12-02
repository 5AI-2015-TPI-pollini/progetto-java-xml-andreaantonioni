package weatherfx;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Contains the way to represents graphically Forecast objects inside the ListView.
 * @see graphic.GUIController#list
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class ForecastCellRender extends ListCell<Forecast> {

    @Override
    protected void updateItem(Forecast item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(createWeatherPane());

    }

    private Pane createWeatherPane() {
        Forecast item = getItem();
        if(item == null)
            return null;
        
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
        
        return borderPane;
    }

}
