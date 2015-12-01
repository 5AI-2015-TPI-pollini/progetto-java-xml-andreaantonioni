/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utility.Utility;
import weatherfx.Forecast;
import weatherfx.ForecastArrayList;

/**
 * FXML Controller class
 *
 * @author Andrea Antonioni -
 * <a href="mailto:andreaantonioni97@gmail.com">andreaantonioni97@gmail.com</a>
 */
public class SettingsController implements Initializable {

    @FXML
    private ListView<String> cityList;
    @FXML
    private Button addCityButton;
    @FXML
    private Button removeCityButton;
    @FXML
    private CheckBox useProxy;
    @FXML
    private Label serverLabel;
    @FXML
    private Label portLabel;
    @FXML
    private TextField serverField;
    @FXML
    private TextField portField;
    @FXML
    private CheckBox authentication;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCityList();
    }

    @FXML
    private void handleAddCityButton(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("NewItem.fxml")));
            stage.setScene(scene);
            stage.showAndWait();

            updateCityList();
        } catch (IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleRemoveCityButton(ActionEvent event) {
        if(!removeCityButton.isDisable() && cityList.getSelectionModel().getSelectedItem()!=null)
        {
            ForecastArrayList.remove(cityList.getSelectionModel().getSelectedItem());
            updateCityList();
        }

    }
    
    @FXML
    private void handleListViewAction(ActionEvent event) {
        removeCityButton.setDisable(false);
    }

    @FXML
    private void handleUseProxyAction(ActionEvent event) {
        if (useProxy.isSelected()) {
            serverLabel.setDisable(false);
            portLabel.setDisable(false);
            serverField.setDisable(false);
            portField.setDisable(false);
            authentication.setDisable(false);
        } else {
            serverLabel.setDisable(true);
            portLabel.setDisable(true);
            serverField.setDisable(true);
            portField.setDisable(true);
            authentication.setSelected(false);
            authentication.setDisable(true);
            userLabel.setDisable(true);
            passwordLabel.setDisable(true);
            userField.setDisable(true);
            passwordField.setDisable(true);
        }
    }

    @FXML
    private void handleAuthenticationAction(ActionEvent event) {
        if (authentication.isSelected()) {
            userLabel.setDisable(false);
            passwordLabel.setDisable(false);
            userField.setDisable(false);
            passwordField.setDisable(false);
        } else {
            userLabel.setDisable(true);
            passwordLabel.setDisable(true);
            userField.setDisable(true);
            passwordField.setDisable(true);
        }
    }

    @FXML
    private void handleSaveAction(ActionEvent event) {
        //UTILIZZAR ELA NUOVA FUNZIONE SETPROXY PER PROXY SENZA AUTENTICAZIONE
        if (useProxy.isSelected()) {
            Utility.setProxy(serverField.getText(), portField.getText(), userField.getText(), passwordField.getText());
            try {
                URL url = new URL("http://www.google.com");
                InputStream in = url.openStream();
                System.out.println("Connection estabiled");
            } catch (MalformedURLException ex) {
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        closeWindow();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void updateCityList() {
        cityList.setItems(null);

        ObservableList<String> observableCityList = FXCollections.observableArrayList();

        for (Forecast forecast : ForecastArrayList.observableList) {
            observableCityList.add(forecast.getCity().getName());
        }

        cityList.setItems(observableCityList);
    }
}
