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
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.NativeArray;
import services.serviceevent;

/**
 * FXML Controller class
 *
 * @author amal
 */

 
          
          //  data = FXCollections.observableArrayList(lp);
            
public class ReservationController implements Initializable {
 serviceevent u = new serviceevent();        
 //  ObservableList listuu = FXCollections.observableArrayList();
//private final ObservableList<Event> data = FXCollections.observableArrayList();
 
    @FXML
    private TableColumn<Event, String> nomid;
    @FXML
    private TableColumn<Event, String> descriptionId;
    @FXML
    private TableColumn<Event, String> categorieId;
    @FXML
    private TableColumn<Event, Date> DateId;
    @FXML
    private TableColumn<Event, String> clubId;
    @FXML
    private TableColumn<Event, Integer> nbId;
    @FXML
    private TableView<Event> idTabe;
    @FXML
    private Button btnRetour;
    @FXML
    private Button participerid;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             ObservableList<Event> listu  = FXCollections.observableArrayList();
   
     try {
        
         
         for(Event bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
         //idev.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
         nomid.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
         categorieId.setCellValueFactory(new PropertyValueFactory<>("categorieEvent"));
         nbId.setCellValueFactory(new PropertyValueFactory<>("nbrPlaceDispo"));
         DateId.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
         descriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    
 
         idTabe.setItems(listu);
       
    }
    private void RetourAction(ActionEvent event) throws IOException {
           Parent tableview = FXMLLoader.load(getClass().getResource("FXMLevent.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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
        

            Event aa= (Event)idTabe.getSelectionModel().getSelectedItem();
           
            serviceevent ps=new serviceevent();
          
            ps.modifierNbr(aa);
         ObservableList<Event> listu  = FXCollections.observableArrayList();
   
     try {
        
         
         for(Event bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
         //idev.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
         nomid.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
         categorieId.setCellValueFactory(new PropertyValueFactory<>("categorieEvent"));
         nbId.setCellValueFactory(new PropertyValueFactory<>("nbrPlaceDispo"));
         DateId.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
         descriptionId.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    
 
         idTabe.setItems(listu);
       


          
    }     


   
    

   
    

  
    
}
