
package GUI;

import entities.DemandeInscri;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import services.serviceDemandeInscri;

/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class InscriptionController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private TextField nomF;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField numF;
    @FXML
    private TextField adresF;
    @FXML
    private TextField mailF;
    @FXML
    private Label prenom;
    @FXML
    private Label adress;
    @FXML
    private Label nomE;
    @FXML
    private TextField nomEF;
    @FXML
    private TextField prenomEF;
    @FXML
    private TextField ageEF;
    @FXML
    private Label prenomE;
    @FXML
    private Label mail;
    @FXML
    private Button incri;
    @FXML
    private TextField cin;
    @FXML
    private Label num1;
    @FXML
    private Label mail1;
    @FXML
    private Label adress1;
    @FXML
    private Label Cin;
    @FXML
    private Label prenom1;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //nom parent 
        ValidationSupport vs = new ValidationSupport();
        vs.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vs.registerValidator(nomF, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
       //prenom parent
        ValidationSupport v = new ValidationSupport();
        v.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        v.registerValidator(prenomf, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
        //num parent 
        ValidationSupport vsp5 = new ValidationSupport();
         String regnum ="^[0-9]*$";
         vsp5.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
         vsp5.registerValidator(numF, Validator.createRegexValidator("musn't be empty!",regnum, Severity.ERROR));
       //address parent 
       ValidationSupport vadd = new ValidationSupport();
        vadd.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vadd.registerValidator(adresF, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
        //Email parent 
        ValidationSupport vmail = new ValidationSupport();
        String regmail ="^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        vmail.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vmail.registerValidator(mailF, Validator.createRegexValidator("musn't be empty!",regmail, Severity.ERROR));
        //Cin parent
         ValidationSupport vcin = new ValidationSupport();
         String regcin ="^[0-9]*$";
         vcin.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
         vcin.registerValidator(cin, Validator.createRegexValidator("musn't be empty!",regcin, Severity.ERROR));
         //nom enfant
         ValidationSupport ve = new ValidationSupport();
        ve.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        ve.registerValidator(nomEF, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
        //prenom enfant
         
         ValidationSupport vpe = new ValidationSupport();
        vpe.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
        vpe.registerValidator(prenomEF, Validator.createEmptyValidator("musn't be empty!", Severity.ERROR));
        //age enfant 
        ValidationSupport vage = new ValidationSupport();
        String regage ="^[3-5]";
         vage.setErrorDecorationEnabled(true); // we don't want errors to bother us for now.
         vage.registerValidator(ageEF, Validator.createRegexValidator("musn't be empty!",regage, Severity.ERROR));
         
         
         
    }    

    @FXML
    private void handleInscriButtonAction(ActionEvent event) {
    
    String firstnameP =nomF.getText();
    String lastname =prenomf.getText();
    int numr =Integer.parseInt(numF.getText());
    String add =adresF.getText();
    String mailp =mailF.getText();
    String cinp =cin.getText();
    String nome =nomEF.getText();
    String prenome =prenomEF.getText();
    int age =Integer.parseInt(ageEF.getText());
     serviceDemandeInscri sv = new serviceDemandeInscri();
     DemandeInscri D1=new DemandeInscri(firstnameP,lastname,numr,add,mailp,nome,prenome,age,cinp);
        try {
            sv.ajouterDemandeInscri(D1);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
