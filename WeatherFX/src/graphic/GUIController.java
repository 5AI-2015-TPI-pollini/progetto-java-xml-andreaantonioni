package graphic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import weatherfx.Forecast;
import weatherfx.ForecastObservableList;
import weatherfx.ForecastCellRender;

/**
 * FXML Controller class of "GUI.fxml"
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class GUIController implements Initializable {

    @FXML
    private ListView<Forecast> list;
    @FXML
    private ImageView settingsButton;

    /**
     * ListView displays the objects contained in ForecastObsevableList adn decides how to render using ForecastCellRenderer
     * @see weatherfx.ForecastObservableList
     * @see weatherfx.ForecastCellRender
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.setItems(ForecastObservableList.observableList);

        list.setCellFactory(new Callback<ListView<Forecast>, ListCell<Forecast>>() {

            @Override
            public ListCell<Forecast> call(ListView<Forecast> param) {
                return new ForecastCellRender();
            }
        });
    }

    @FXML
    private void handleSettingsAction(MouseEvent event) {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene (FXMLLoader.load(getClass().getResource("Settings.fxml")));
            stage.setScene(scene);
            stage.setTitle("Settings");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
