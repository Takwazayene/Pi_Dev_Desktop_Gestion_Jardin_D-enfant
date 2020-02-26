/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import entities.Event;
import entities.clubs;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.serviceclubs;
import services.serviceevent;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class ParticiperClubController implements Initializable {
serviceclubs u = new serviceclubs(); 
    @FXML
    private TableView<clubs> idtable;
    @FXML
    private TableColumn<clubs, String> idnom;
    @FXML
    private TableColumn<clubs, String> idact;
    @FXML
    private TableColumn<clubs, Integer> ideff;

    
   // ObservableList<clubs> data;
    @FXML
    private Button btnRetour;
    @FXML
    private Button idparticiper;
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<clubs> listu  = FXCollections.observableArrayList();
      
        try {
            for(clubs bb: u.readAll())
             listu.add(bb);
            
            } catch (SQLException ex) {
         Logger.getLogger(ParticiperClubController.class.getName()).log(Level.SEVERE, null, ex);
     }
            
            
             idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idact.setCellValueFactory(new PropertyValueFactory<>("activite"));
            ideff.setCellValueFactory(new PropertyValueFactory<>("effectif"));
            
              
         
            idtable.setItems(listu);
                  
  
           
            //Logger.getLogger(EventsListController.class.getName()).log(Level.SEVERE, null, ex);
        

    }
 

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("ParticiperClubEvent1.fxml"));

        Scene sceneview = new Scene(tableview);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void ParticiperAction(ActionEvent event) {
         clubs aa= (clubs)idtable.getSelectionModel().getSelectedItem();
           
            serviceclubs ps=new serviceclubs();
          
            ps.modifierNbr1(aa);
         ObservableList<clubs> listu  = FXCollections.observableArrayList();
         
         
           try {
            for(clubs bb: u.readAll())
             listu.add(bb);
            
            } catch (SQLException ex) {
         Logger.getLogger(ParticiperClubController.class.getName()).log(Level.SEVERE, null, ex);
     }
            
            
             idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idact.setCellValueFactory(new PropertyValueFactory<>("activite"));
            ideff.setCellValueFactory(new PropertyValueFactory<>("effectif"));
            
              
         
            idtable.setItems(listu);
                  
  
           
    }
}