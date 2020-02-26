/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

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
 * @author amal
 */
public class ParticiperClubEventController implements Initializable {

    @FXML
    private Button btnEvent;
    @FXML
    private Button btnClub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEventAction(ActionEvent event) throws IOException {
             Parent tableview = FXMLLoader.load(getClass().getResource("ParticiperEvent.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void btnClubAction(ActionEvent event) throws IOException {
             Parent tableview = FXMLLoader.load(getClass().getResource("Participerclubs1.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
