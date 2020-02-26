/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import pidev.DataBase.DataBase;
import pidev.Service.ServiceTab_Demande;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import pidev.Entite.Pdf;
import pidev.Entite.Tab_Demande;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class AjouterDemandeController implements Initializable {
    private Statement ste;
    private Connection con;

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField numtel;
    @FXML
    private TextField cv;
    @FXML
    private DatePicker date_naiss;
    @FXML
    private TextField etude;

    @FXML
    private ComboBox<String> comboBox;
    ServiceTab_Demande stb = new ServiceTab_Demande();  
    
    ObservableList<String> list = FXCollections.observableArrayList("ok","bb","Bye");
    @FXML
    private Button AjouterDemande;
    
    @FXML
    private Label msg;

    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBox.setItems(list);

    }

    @FXML
    private void AjouterDemande(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException{
                       con = DataBase.getInstance().getConnection();
             ste = con.createStatement();
              if(Validchamp(nom)&&Validchamp(prenom)&&Validchamp(cin)&&Validchamp(cv)&&Validchamp(etude)){            
                try {
                 int nmt= (int) Integer.valueOf(this.numtel.getText());
                DatePicker tmpdate=(DatePicker) date_naiss;
                String date= (String) tmpdate.getValue().toString();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                ComboBox tmpcmb = (ComboBox) comboBox;
                
                    String poste = (String) tmpcmb.getValue().toString();
                    ServiceTab_Demande ser =new ServiceTab_Demande();
                    ser.ajouter(new Tab_Demande(nom.getText(), prenom.getText(),cin.getText(),nmt,cv.getText(),sqlDate,etude.getText(),poste));
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
                     Pdf p = new Pdf();
                  p.add("Demande",nom.getText(), prenom.getText(),cin.getText(),numtel.getText(),cv.getText(),date_naiss.getValue().toString(),etude.getText(),comboBox.getValue());
                msg.setText("Ajout avec succée");
                nom.setText("");
                prenom.setText("");
                cin.setText("");
                numtel.setText("");
                cv.setText("");
                etude.setText("");
             
              }
              else{
                  msg.setText("Verifier vos données");
              }
                  
                    
}
}
