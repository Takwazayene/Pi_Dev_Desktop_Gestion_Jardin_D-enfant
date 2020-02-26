/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import entities.clubs;
import entities.clubs;
import java.io.IOException;
import javafx.geometry.Insets;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.S;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import services.serviceclubs;

/**
 * FXML Controller class
 *
 * @author Siwar
 */
public class AfficherClubsController implements Initializable {

    
    @FXML
    private TableView<clubs> idtable;
    @FXML
    private TableColumn<clubs, String> idnom;
    @FXML
    private TableColumn<clubs, String> idact;
    @FXML
    private TableColumn<clubs, Integer> ideff;
    @FXML
    private Button idretour;
    @FXML
    private Button idstatistique;
    @FXML
    private TextField chercher;
    @FXML
    private Button btnchercher;
    serviceclubs u = new serviceclubs();        
ObservableList listuu = FXCollections.observableArrayList();
    @FXML
    private Button idsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
                  ObservableList<clubs> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(clubs bb: u.readAll())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
     //mettre les données dans la table view:  
        // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
         idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         idact.setCellValueFactory(new PropertyValueFactory<>("activite"));
         ideff.setCellValueFactory(new PropertyValueFactory<>("effectif"));
        
        // TODO
        idtable.setItems(listu);
        
        
        
        
        idtable.setEditable(true);
           idnom.setCellFactory(TextFieldTableCell.forTableColumn());
            idact.setCellFactory(TextFieldTableCell.forTableColumn());
            ideff.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        

    }    
    
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("club.fxml"));

        Scene sceneview = new Scene(tableview);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void StatistiqueAction(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("Statistiques1.fxml"));

        Scene sceneview = new Scene(tableview);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void Recherche_Clubs(ActionEvent event) {
        
               String text = chercher.getText();
              ObservableList<clubs> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(clubs bb: u.RechercherClubs(text))
             listu.add(bb);
            idtable.setItems(listu); 
     
     } catch (SQLException ex) {
         Logger.getLogger(AfficherClubsController.class.getName()).log(Level.SEVERE, null, ex);
     }   
 
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

    @FXML
    private void SupprimerAction(ActionEvent event) {
        
        ObservableList<clubs> SelectedRows, allpeople;
     
     allpeople = idtable.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =idtable.getSelectionModel().getSelectedItems();
     
     for(clubs gg:SelectedRows){
       allpeople.remove(gg);
       u.supprimerclubs (gg);
     }
    }

 /*  @FXML
    private void ModifierAction(ActionEvent event) {
           /*   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      
        alert.setTitle("Modification");
        alert.setHeaderText("Voulez-vous vraiment modifier cette Cateorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){*/
           // Product pre = new Product(nomProduit, 0, 0, brochure)
       //     Product pre = new Product(Float.parseFloat(fieldprix.getText()), Integer.parseInt(fieldstock.getText()),fieldnom.getText(), fieldimg.getText());
       
         //AbonneResto a =new Product(Cnom.getText(),CtypeAbo.getText(),Integer.parseInt(fieldstock.getText()),fieldimg.getText());
      /*   clubs a= new clubs(idnom.getText(),idact.getText(),Integer.parseInt(ideff.getText()));
         

          clubs aa= (clubs)idtable.getSelectionModel().getSelectedItem();
           
            serviceclubs ps=new serviceclubs();
            
          
            ps.modifierNbr1(a);*/
          //  loadData();
          // clearTextfField();
   // }
    
    
    
    
  /*  private void clearTextfField(){
        idnom.clear();
        idact.clear();
        ideff.clear();
     
        
        readAll();



    }
    
    private void loadData(){
       serviceclubs ps= new  serviceclubs();
        ObservableList<clubs> listu  = FXCollections.observableArrayList();
      //  ArrayList p= (ArrayList) ps.readAll();
        ObservableList matchObservable;
      //  matchObservable = FXCollections.observableArrayList(p);
          idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         idact.setCellValueFactory(new PropertyValueFactory<>("activite"));
         ideff.setCellValueFactory(new PropertyValueFactory<>("effectif"));
        
        // TODO
        idtable.setItems(listu);
        
       // afficher();
    }*/
   // serviceclubs u = new serviceclubs(); 

   /* @FXML
    private void Change_Nom(TableColumn.CellEditEvent bb ) throws SQLException{
        clubs tab_Matiereselected = idtable.getSelectionModel().getSelectedItem();
     tab_Matiereselected.setNom(bb.getNewValue().toString());
     u.updatetab(tab_Matiereselected);
    }
    */

   @FXML
     public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     clubs tab_Groupeselected = idtable.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setNom(bb.getNewValue().toString());
     u.updatetab(tab_Groupeselected);
    }

    @FXML
    private void Change_activite(TableColumn.CellEditEvent bb) throws SQLException {
       clubs tab_Groupeselected = idtable.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setActivite(bb.getNewValue().toString());
     u.updatetab(tab_Groupeselected);
    }

    @FXML
    private void Change_effectif(TableColumn.CellEditEvent bb) throws SQLException {
        clubs tab_Groupeselected = idtable.getSelectionModel().getSelectedItem();
     tab_Groupeselected.setEffectif(Integer.parseInt(bb.getNewValue().toString()));
     u.updatetab(tab_Groupeselected);
    }
}
    




