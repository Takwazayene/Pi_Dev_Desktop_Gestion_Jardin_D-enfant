/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import entities.Event;
import entities.clubs;
import java.io.IOException;
import javafx.geometry.Insets;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.serviceevent;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class FXMLaffichereventController implements Initializable {
   serviceevent u = new serviceevent();        
   ObservableList listuu = FXCollections.observableArrayList();
 
    @FXML
    private TableView<Event> idtable;
    @FXML
    private TableColumn<Event,String> idnom;
    @FXML
    private TableColumn<Event, String> idcat;
    @FXML
    private TableColumn<Event,Integer> idnbr;
    @FXML
    private TableColumn<Event, Date> idate;
    @FXML
    private TableColumn<Event, String> idaf;
    @FXML
    private Button idretour;
    @FXML
    private Button supprimerid;
    @FXML
    private TextField recherche;
//public static TableView<Event> table2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       RechercheAV();
         ObservableList<Event> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(Event bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
         //idev.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
         idnom.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
         idcat.setCellValueFactory(new PropertyValueFactory<>("categorieEvent"));
         idnbr.setCellValueFactory(new PropertyValueFactory<>("nbrPlaceDispo"));
         idate.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
         idaf.setCellValueFactory(new PropertyValueFactory<>("description"));
        
    
 
         idtable.setItems(listu);
         
        
           idtable.setEditable(true);
            idnom.setCellFactory(TextFieldTableCell.forTableColumn());
            
            
             
    }


    
    
    
    
    
    
    
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Event> filteredData = new FilteredList<>(listuu, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(groupe -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (groupe.getNomEvent().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				    	 return false; // Does not match.
			});
		});
                	// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Event> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(idtable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		idtable.setItems(sortedData);
    }
    
    
  

 
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
           Parent tableview = FXMLLoader.load(getClass().getResource("Event.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

  /*  @FXML
    private void Recherche(KeyEvent event) {
          idtable.setItems(data);
        String rech= rech_id.getText();
       
        
           if(idtable.getItems().isEmpty()){
               
                   idtable.setItems(data);
          
          }else{
       
  idtable.setItems(idtable.getItems().filtered(e->e.getNomEvent().equals(rech)));
          }
    }
    */

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
        
        ObservableList<Event> SelectedRows, allpeople;
     
     allpeople = idtable.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =idtable.getSelectionModel().getSelectedItems();
     
     for(Event gg:SelectedRows){
       allpeople.remove(gg);
       u.supprimerevent(gg);
     }
    }

    @FXML
    private void Change_nom(TableColumn.CellEditEvent bb) throws SQLException {
         Event tab_Groupeselected = idtable.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setNomEvent(bb.getNewValue().toString());
     u.updatetab(tab_Groupeselected);
    }

    

   

    
    }
    
    


 
