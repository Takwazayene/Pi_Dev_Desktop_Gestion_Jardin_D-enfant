/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.DemandeInscri;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.serviceDemandeInscri;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class payementController implements Initializable {

    @FXML
    private TableView<DemandeInscri> table;
    @FXML
    private TableColumn<DemandeInscri, String> nEnfant;
    @FXML
    private TableColumn<DemandeInscri, String> Penfant;
    @FXML
    private TableColumn<DemandeInscri, String> etat;
    @FXML
    private Label alert;
    @FXML
    private CheckBox edinar;
    @FXML
    private CheckBox cartebancaire;
    @FXML
    private TextField montant;
    @FXML
    private Label montant2;
    @FXML
    private Button valider;
    @FXML
    private TextField codeCarte;
    @FXML
    private Label codeCarte2;
    @FXML
    private Button inscri;
    @FXML
    private Button payement;
    
     serviceDemandeInscri sv = new serviceDemandeInscri();
    @FXML
    private Button paye;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<DemandeInscri> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(DemandeInscri bb: sv.getAllInscri(LoginFXMLController.usernameautho))
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
     }

         //mettre les données dans la table view:    
        
         nEnfant.setCellValueFactory(new PropertyValueFactory<>("nomE"));
         Penfant.setCellValueFactory(new PropertyValueFactory<>("prenomE"));
         etat.setCellValueFactory(new PropertyValueFactory<>("Etatp"));
       
        //load dummy data
        table.setItems(listu);
       
       
    }    

   
    @FXML
    private void validerlepaiement(ActionEvent event) {
     DemandeInscri Dselectionner = table.getSelectionModel().getSelectedItem();
     String nomF =Dselectionner.getNomE();
     String prenomF =Dselectionner.getPrenomE();
     int age =Dselectionner.getAgeE();
      System.out.println("heloo");
     int e=Dselectionner.getEtatp();
     System.out.println(e);
     sv.updateEtatP(Dselectionner,1);
        System.out.println("ken 5edmet tore9di ken ma 5edmetech tore9di");
      ObservableList<DemandeInscri> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(DemandeInscri bb: sv.getAllInscri(LoginFXMLController.usernameautho))
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
     }

         //mettre les données dans la table view:    
        
         nEnfant.setCellValueFactory(new PropertyValueFactory<>("nomE"));
         Penfant.setCellValueFactory(new PropertyValueFactory<>("prenomE"));
         etat.setCellValueFactory(new PropertyValueFactory<>("Etatp"));
       
        //load dummy data
        table.setItems(listu);
     
   
  
  
    }

    @FXML
    private void nouvelinscri(ActionEvent event) {
        try {
            Parent tableview = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(payementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void pagepayement(ActionEvent event) {
        try {
            Parent tableview = FXMLLoader.load(getClass().getResource("payement.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(payementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void payer(ActionEvent event) {
        DemandeInscri Dselectionner = table.getSelectionModel().getSelectedItem();
        int e=Dselectionner.getEtatp();
        if (e==0){
        int nbr=sv.calculer_nbE(LoginFXMLController.usernameautho);
        if(nbr==1){
            montant.setText("2000000");
        }
        
        else if(nbr==2){
        montant.setText("1800000");

        }
        else {
        montant.setText("1600000");
         
        }}
        else {
         
         JOptionPane.showMessageDialog(null, "Inscription déja payé");
     }
    }

    @FXML
    private void onedinarclicked(ActionEvent event) {
    }

    @FXML
    private void oncartebancaireclick(ActionEvent event) {
    }
    
}
