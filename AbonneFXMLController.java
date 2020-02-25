/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AbonneFXMLController implements Initializable {

    @FXML
    private Button resto;
    @FXML
    private Button carte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    @FXML
    private void onrestoclicked(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("ReservationFXML.fxml"));
    Scene newScene= new Scene(root);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(newScene);
       window.show();
        
    }
    
    
     @FXML
    private void oncarteclicked(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("CarteFidAbonne2FXML.fxml"));
    Scene newScene= new Scene(root);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(newScene);
       window.show();
        
    }

       @FXML
    private void onplatclicked(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("YoutubeFXML.fxml"));
    Scene newScene= new Scene(root);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(newScene);
       window.show();
        
    }
    
    
}
