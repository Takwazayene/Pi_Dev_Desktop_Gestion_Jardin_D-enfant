/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Event;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.serviceevent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.paint.ImagePattern;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;




/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button idajouter;
    @FXML
    private Label id1;
    @FXML
    private Label id2;
    @FXML
    private Label id3;
    @FXML
    private Label id4;
    @FXML
    private Label id5;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idnbr;
    @FXML
    private DatePicker idate;
    @FXML
    private TextArea idad;
    @FXML
    private Button idretour;
    @FXML
    private ComboBox<String> RCombobox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
                  // int id=Integer.parseInt(idid.getText());
        DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
      
        String nom=idnom.getText();
        //Date date=Date.valueOf(idate.getValue());
     //iddates.getValue();
        String description=idad.getText();
      // String cb=idc.getText();
        int nbr=Integer.parseInt(idnbr.getText());
       // String cb=String.valueOf(cbitems.getItems());
        String cb=RCombobox.getValue();
       LocalDate localDate = idate.getValue();
       // Date.valueOf( dateformat.format(iddates.getValue().atStartOfDay(ZoneId.of("GMT")).toEpochSecond()* 1000));
      
       serviceevent srv = new serviceevent();
      Event e1=new Event( nom, cb, nbr, java.sql.Date.valueOf(localDate), description);
           //   Event e1=new Event(1,1 nom, cb, nbr, date, description);

        //Event e = new Event (nom, cb, nbr, date , description);
        srv.ajouterevent(e1);
       // label.setText("merci d'ajouter un evenement");
 
   
         try {
         Notification.sendNotification("Ajout effectuée", "merci ",TrayIcon.MessageType.INFO);
     } catch (Exception ex) {
         Logger.getLogger(AjouterClubsController.class.getName()).log(Level.SEVERE, null, ex);
     }  
   
   
   
   
   
    }
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         RCombobox.getItems().add("quran");
          RCombobox.getItems().add("music");
            RCombobox.getItems().add("theatre");
            
            
            
                 ValidationSupport vs5 = new ValidationSupport();
        vs5.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vs5.registerValidator(idnbr, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
       
            ValidationSupport vs6 = new ValidationSupport();
        vs6.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vs6.registerValidator(idnom, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
       
ValidationSupport vsp5 = new ValidationSupport();
         String regmail ="^[0-9]*$";
        vsp5.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vsp5.registerValidator(idnbr, Validator.createRegexValidator("musn't be empty!",regmail, Severity.ERROR));
        
        
      ValidationSupport vsp7 = new ValidationSupport();
   
       vsp7.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
       vsp7.registerValidator(idad, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
       
       
          
        
        
        
        // TODO
    }    

    @FXML
    private void RetourAction(ActionEvent event) throws IOException {
        
           Parent tableview = FXMLLoader.load(getClass().getResource("Event.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void accueil(ActionEvent event) {
    }

   
    
}
