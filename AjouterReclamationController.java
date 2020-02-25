/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.itextpdf.text.Annotation.FILE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import utils.ConnexionBD;
import service.ServiceTab_Reclamation;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import entities.Tab_Reclamation;
import doryan.windowsnotificationapi.fr.Notification;
import entities.SendMail;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSetMetaData;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField numtel;
    
    private Statement ste;
    private Connection con;

    @FXML
    private ComboBox<String> comboBox;
    ServiceTab_Reclamation stb = new ServiceTab_Reclamation();
    
    ObservableList<String> list = FXCollections.observableArrayList("parent","enseignat");
    @FXML
    private Button AjouterDemande;
    @FXML
    private TableView<Tab_Reclamation> AffichageTabDemande;
    @FXML
    private TableColumn<Tab_Reclamation, String> nomtab;
    @FXML
    private TableColumn<Tab_Reclamation, String> prenomtab;
    @FXML
    private TableColumn<Tab_Reclamation, String> cintab;
    @FXML
    private TableColumn<Tab_Reclamation, String> numteltab; 
    
    @FXML
    private TableColumn<Tab_Reclamation, String> postetab;
    private final ObservableList<Tab_Reclamation> data = FXCollections.observableArrayList();

    @FXML
    private Button Supprimer;
    @FXML
    private TextField recherche;
    @FXML
    private TextField mail;
    @FXML
    private TextField object;
    @FXML
    private TextField corp;
    @FXML
    private Button sendmail;
    
    /*@FXML
    private void pdf () throws DocumentException, SQLException, FileNotFoundException
    {
        
        List<Tab_Reclamation> list = stb.readAll();
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(FILE));
                        document.open();
                        pdf.addMetaData(document);
                        pdf.addTitlePage(document, list);
                        document.close();
    }*/
    public void Aff(){
                        try {
         //   con = DataBase.getInstance().getConnection();
             con = ConnexionBD.getInstance().getCnx();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from Tab_Reclamation");
            while(rs.next()){
                //public Tab_Reclamation(String nom, String prenom, String cin, int numtel, String poste){
                data.add(new Tab_Reclamation(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
//            idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cintab.setCellValueFactory(new PropertyValueFactory<>("cin"));
            numteltab.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            
            postetab.setCellValueFactory(new PropertyValueFactory<>("poste"));

            AffichageTabDemande.setItems(data);
            AffichageTabDemande.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            prenomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            cintab.setCellFactory(TextFieldTableCell.forTableColumn());
            numteltab.setCellFactory(TextFieldTableCell.forTableColumn());
            

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
                        Aff();

        RechercheAV();

    }
    
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tab_Reclamation> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(tab_demande -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (tab_demande.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (tab_demande.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(tab_demande.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Tab_Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageTabDemande.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageTabDemande.setItems(sortedData);
    }
    
    @FXML
    private void AjouterDemande(MouseEvent event) throws SQLException{
                   con = ConnexionBD.getInstance().getCnx();
             ste = con.createStatement();

        
//               int nmt= (int) Integer.valueOf(this.numtel.getText());
//DatePicker tmpdate=(DatePicker) date_naiss;
//String date= (String) tmpdate.getValue().toString();
//date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);
//java.util.Date myDate = new java.util.Date(date);
//java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
    ComboBox tmpcmb = (ComboBox) comboBox;

String poste = (String) tmpcmb.getValue().toString();
ServiceTab_Reclamation ser =new ServiceTab_Reclamation();
ser.ajouter(new Tab_Reclamation(nom.getText(), "0",cin.getText(),numtel.getText(),poste));
Aff();
RechercheAV();


 
                try {
         Notification.sendNotification("Element ajouté", "Vous pouver passer une autre reclamation",TrayIcon.MessageType.INFO);
     } catch (AWTException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (MalformedURLException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }                
               
                    
}

   
    
    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                               		
             AffichageTabDemande.setItems(data);

             ObservableList<Tab_Reclamation> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabDemande.getItems();
             SingleDemandes=AffichageTabDemande.getSelectionModel().getSelectedItems();
             Tab_Reclamation A = SingleDemandes.get(0);
             ServiceTab_Reclamation STD = new ServiceTab_Reclamation(); // STD = Service TAB DEMANDE
             STD.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
             RechercheAV();
              
                try {
         Notification.sendNotification("Element supprimé", "Vous pouver passer une autre reclamation",TrayIcon.MessageType.INFO);
     } catch (AWTException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (MalformedURLException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
                

    }



    @FXML
 public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Reclamation tab_Reclamationselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Reclamationselected.setNom(bb.getNewValue().toString());
     stb.updatetab(tab_Reclamationselected);
 }
 
    @FXML
  public void Change_Prenom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Reclamation tab_Reclamationselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Reclamationselected.SetPrenom(bb.getNewValue().toString());
     stb.updatetab(tab_Reclamationselected);
 }
  
    @FXML
   public void Change_Numtel(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Reclamation tab_Reclamationselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Reclamationselected.setNumtel(bb.getNewValue().toString());
     stb.updatetab(tab_Reclamationselected);
 }
   
    @FXML
     public void sendmail() {
       
        String mail= (String) nom.getText();
        String Objet= (String) prenom.getText();
        String Corp= (String) cin.getText();
   SendMail.sendMail(mail,Objet, Corp); 
       
    
}
   
        



    

}
