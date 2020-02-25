/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Enfant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.CarteFidelite;
import entities.Paiement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceAbonneResto;
import service.ServiceCarteFidelite;
import service.ServicePaiement;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CarteFideliteAdminFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      private ObservableList<CarteFidelite> carte;
    
       private ObservableList<Paiement> paiement;
      @FXML
      private TableView<CarteFidelite> tableCarte;
      
      @FXML
      private TableView<Paiement> tablePaiement;
      
    @FXML
    private TableColumn<CarteFidelite, Integer> idC;
 @FXML
    private TableColumn<CarteFidelite, Integer> idAb;
     @FXML
    private TableColumn<CarteFidelite, Integer> nbpoint;
      @FXML
    private TableColumn<CarteFidelite, Float> credit;
       @FXML
    private TableColumn<CarteFidelite, Float>  benefice;
        @FXML
    private TableColumn<CarteFidelite, Integer> status;
    
        @FXML
    private TableColumn<Paiement, Integer> idP;
         @FXML
    private TableColumn<Paiement, Integer> idC2;
          @FXML
    private TableColumn<Paiement, String> type;
           @FXML
    private TableColumn<Paiement, Float> total;
            @FXML
    private TableColumn<Paiement, DatePicker> date;
           @FXML
   private Label resCalcul;
           @FXML 
   private Button calcul ;
        
        
        
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficherCarteFidelite();
        afficherPaiement();
    }   
    
    
    

 private void afficherCarteFidelite(){

        ServiceCarteFidelite service= new ServiceCarteFidelite();
       carte= FXCollections.observableArrayList();
        
        List<CarteFidelite> ls = service.getAll();
        ls.stream().forEach(carte::add);
        
         
        
        tableCarte.setItems(carte);
       idC.setCellValueFactory(new PropertyValueFactory<>("idC")); 
        idAb.setCellValueFactory(new PropertyValueFactory<>("idAb"));
        nbpoint.setCellValueFactory(new PropertyValueFactory<>("nbpoint"));
       credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        benefice.setCellValueFactory(new PropertyValueFactory<>("benefice"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
      
      //recupererLesValeurs();
        loadData();
        
    }  
 
 
  private void afficherPaiement(){

        ServicePaiement service= new ServicePaiement();
       paiement= FXCollections.observableArrayList();
        
        List<Paiement> ls = service.getAll();
        ls.stream().forEach(paiement::add);
        
         
        
        tablePaiement.setItems(paiement);
       idP.setCellValueFactory(new PropertyValueFactory<>("idP")); 
        idC2.setCellValueFactory(new PropertyValueFactory<>("idC"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
       total.setCellValueFactory(new PropertyValueFactory<>("total"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
      
      //recupererLesValeurs();
        //loadData();
        
    }  
 
  @FXML
  private void calculTotalCaisse()
  {
      ServicePaiement service= new ServicePaiement();
      float caisse = service.CalculTotalCaisse();
     resCalcul.setText(Float.toString(caisse));
       
      
  }
 
 
 
 
     private void loadData(){
       ServiceCarteFidelite ps= new  ServiceCarteFidelite();
        ArrayList p= (ArrayList) ps.getAll();
        ObservableList matchObservable;
        matchObservable = FXCollections.observableArrayList(p);
        tableCarte.setItems(matchObservable);   
        idC.setCellValueFactory(new PropertyValueFactory<>("idC")); 
        idAb.setCellValueFactory(new PropertyValueFactory<>("idAb"));
        nbpoint.setCellValueFactory(new PropertyValueFactory<>("nbpoint"));
       credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        benefice.setCellValueFactory(new PropertyValueFactory<>("benefice"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
      
        
       // afficher();
    }
     
      @FXML
    private void onbloquerclicked(ActionEvent event) throws SQLException {
        
          
        
       ServiceCarteFidelite cfs=new ServiceCarteFidelite();
      
       CarteFidelite carte=new CarteFidelite();
       carte=tableCarte.getSelectionModel().getSelectedItem();
      
    
     
        cfs.bloquercarte(carte);
        
        loadData();
    }
    
    
    
    
}
