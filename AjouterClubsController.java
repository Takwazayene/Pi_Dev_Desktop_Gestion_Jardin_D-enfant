/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Event;
import entities.clubs;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import services.serviceclubs;
import services.serviceevent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterClubsController implements Initializable {

    @FXML
    private TextField idnom;
    
    @FXML
    private TextField ideff;
    @FXML
    private ComboBox<String> RCombobox;
    @FXML
    private Button idajouter;
    @FXML
    private Button idretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RCombobox.getItems().add("sportif");
          RCombobox.getItems().add("culturel");
            RCombobox.getItems().add("humain");
            ValidationSupport vs = new ValidationSupport();
        vs.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vs.registerValidator(ideff, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
       
            ValidationSupport vs1 = new ValidationSupport();
        vs1.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vs1.registerValidator(idnom, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
       
ValidationSupport vsp = new ValidationSupport();
         String regmail ="^[0-9]*$";
        vsp.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vsp.registerValidator(ideff, Validator.createRegexValidator("musn't be empty!",regmail, Severity.ERROR));
       
       
        
        
    }    

    @FXML
    private void AjouterClubAction(ActionEvent event) throws IOException {
         String nom=idnom.getText();
//        String activite=idact.getText();
        String activites=RCombobox.getValue();
     //iddates.getValue();
        
        int effectif=Integer.parseInt(ideff.getText());
       // String cb=String.valueOf(cbitems.getItems());
       
       
       // Date.valueOf( dateformat.format(iddates.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000));
      
       serviceclubs srv1 = new serviceclubs();
        clubs c = new clubs (nom, activites,effectif);
        srv1.ajouterclubs(c);
        FXMLLoader loader2=new FXMLLoader(getClass().getResource("AfficherClubs1.fxml"));
            Parent root =loader2.load();

          
            idnom.getScene().setRoot(root);
            
            
            
            
         try {
         Notification.sendNotification("Ajout effectuée", "merci ",TrayIcon.MessageType.INFO);
     } catch (Exception ex) {
         Logger.getLogger(AjouterClubsController.class.getName()).log(Level.SEVERE, null, ex);
     }   
            
            
            
            
            
    }
    
    
    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
           Parent tableview = FXMLLoader.load(getClass().getResource("clubs1.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
