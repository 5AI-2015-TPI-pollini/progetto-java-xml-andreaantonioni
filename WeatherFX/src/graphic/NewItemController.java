package graphic;

import GoogleMapsGeocode.NoResultsException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import weatherfx.ForecastObservableList;

/**
 * FXML Controller class of "NewItem.fxml"
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class NewItemController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private AnchorPane newItemPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchField.setText("");
    }    

    @FXML
    private void handleOkAction(ActionEvent event) {
        try {
            ForecastObservableList.add(searchField.getText());
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("WeatherFX");
            alert.setContentText("Check your internet connection");
            alert.showAndWait();
        } catch (NoResultsException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("WeatherFX");
            alert.setContentText("City not found");
            alert.showAndWait();
        }
        
        closeWindow();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        closeWindow();
    }
    
    private void closeWindow()
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
}
