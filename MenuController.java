/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package pidev.Test.Demande;

import static com.itextpdf.text.Annotation.FILE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pidev.Entite.Tab_Reclamation;
import pidev.Service.ServiceTab_Reclamation;

/**
 * FXML Controller class
 *
 * @author wajih
 */
/*public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button interajouter;
    @FXML
    private Button intersupprimer;
    @FXML
    private Button intermodifier;
    @FXML
    private Button interafficher;
    @FXML
    private AnchorPane AP;
    
    public void changetoAjouterScreen(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterDemande.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    /**
     * Initializes the controller class.
     */
   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void AjouterDemande(MouseEvent event) {
        //bp.setCenter(AP);
        loadPage("AjouterDemande");
    }
    
    

    @FXML
    private void SupprimerDemande(MouseEvent event) {
    }

    @FXML
    private void ModifierDemande(MouseEvent event) {
    }

    @FXML
    private void AfficherDemande(MouseEvent event) {
    }
    
    private void loadPage(String page){
                           
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setCenter(root);
    }
    
    
    
}*/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import static com.itextpdf.text.Annotation.FILE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import pidev.DataBase.DataBase;
import pidev.Service.ServiceTab_Reclamation;
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
import pidev.Entite.Tab_Reclamation;
import doryan.windowsnotificationapi.fr.Notification;
import functions.SendMail;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSetMetaData;
import java.util.Set;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import static utils.Whatsapp.ACCOUNT_SID;
import static utils.Whatsapp.AUTH_TOKEN;





/**
 * FXML Controller class
 *
 * @author hp
 */
public class MenuController implements Initializable {

    private TextField nom;
    private TextField prenom;
    private TextField cin;
    private TextField numtel;
    
    private Statement ste;
    private Connection con;

    private ComboBox<String> comboBox;
    ServiceTab_Reclamation stb = new ServiceTab_Reclamation();
    
    ObservableList<String> list = FXCollections.observableArrayList("parent","enseignat");
    @FXML
    private TableView<Tab_Reclamation> AffichageTabDemande;
    @FXML
    private TableColumn<Tab_Reclamation, String> nomtab;
    @FXML
    private TableColumn<Tab_Reclamation, String> prenomtab;
    @FXML
    private TableColumn<Tab_Reclamation, String> cintab;
    @FXML
    private TableColumn<Tab_Reclamation, Integer> numteltab;
     @FXML
    private PieChart bookChart;
     
  /*  @FXML
    private TextField calculerSom;*/

      


    @FXML
    private TableColumn<Tab_Reclamation, String> postetab;
                ObservableList<Tab_Reclamation> cls = FXCollections.observableArrayList();

    private TextField recherche;
    @FXML
    private Button Approuv;
    @FXML
    private Button Excel;
    @FXML
    private Button calculerSom;
    @FXML
    private TextField resultat;
   
    private void pdf () throws DocumentException, SQLException, FileNotFoundException
    {
        
        List<Tab_Reclamation> list = stb.readAll();
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(FILE));
                        document.open();
                        pdf.addMetaData(document);
                        pdf.addTitlePage(document, list);
                        document.close();
    }
    
    
    public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        cls.clear();
                ServiceTab_Reclamation ser=new ServiceTab_Reclamation();
                List<Tab_Reclamation> list = ser.readAll();
                for(Tab_Reclamation aux : list){

                    cls.add(new Tab_Reclamation(aux.getNom(),aux.getPrenom(),aux.getId(), aux.getCin(), aux.getNumtel(), aux.getPoste()));
                    
                }
                
           // idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cintab.setCellValueFactory(new PropertyValueFactory<>("cin"));
            numteltab.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            postetab.setCellValueFactory(new PropertyValueFactory<>("poste"));
            
            AffichageTabDemande.setItems(cls); 
            


        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                        Aff();
                        ServiceTab_Reclamation ser=new ServiceTab_Reclamation();
                        //ServiceClubs ser = new ServiceClubs();
                        String s;
                        s = String.valueOf(ser.calculerTotal());
                        
                        resultat.setText(s);
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                
                new PieChart.Data("parent", ser.calculer("parent")),
                new PieChart.Data("enseignat", ser.calculer("enseignat"))
              
        );
        bookChart.setData(pieChartData);
        bookChart.setClockwise(false);
    // calculerSom.set
                calculerSom.setOnAction(event->{ser.calculerTotal();});


    //calculerSom
    }
    
    
  /*  private void ButtonSupprimer(ActionEvent event) throws SQLException {
                               		
             AffichageTabDemande.setItems(cls);

             ObservableList<Tab_Demande> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabDemande.getItems();
             SingleDemandes=AffichageTabDemande.getSelectionModel().getSelectedItems();
             Tab_Reclamation A = SingleDemandes.get(0);
             ServiceTab_Reclamation STD = new ServiceTab_Reclamation(); // STD = Service TAB DEMANDE
             STD.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
            // RechercheAV();
              
                try {
         Notification.sendNotification("Element supprimé", "Vous pouver passer une autre reclamation",TrayIcon.MessageType.INFO);
     } catch (AWTException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (MalformedURLException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
               */




 public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Reclamation tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
 
  public void Change_Prenom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Reclamation tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.SetPrenom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
  
   public void Change_Numtel(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Reclamation tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNumtel(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
   
     public void sendmail() {
       
        String mail= (String) nom.getText();
        String Objet= (String) prenom.getText();
        String Corp= (String) cin.getText();
        SendMail.sendMail(mail,Objet, Corp);
       
    
}

   

    @FXML
    private void Approuv(ActionEvent event) throws SQLException {
             
             AffichageTabDemande.setItems(cls);
             ServiceTab_Reclamation STD = new ServiceTab_Reclamation(); // STD = Service TAB DEMANDE
             Tab_Reclamation aux=AffichageTabDemande.getSelectionModel().getSelectedItem();
             Tab_Reclamation tmp1 = new Tab_Reclamation(aux.getNom(),aux.getPrenom(),aux.getId(), aux.getCin(), aux.getNumtel(), aux.getPoste());
             STD.updateapp(tmp1);
             
             Aff();
               Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21652272411"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                //" lkhir" +nom.getText()+"wl barka  💖 !")
                 " Votre reclamation a été approuvé 💖 !")
           .create();

        System.out.println(message.getSid()); 
                try {
         Notification.sendNotification("Element Approuvé", "Vous pouver passer une autre reclamation",TrayIcon.MessageType.INFO);
     } catch (AWTException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (MalformedURLException ex) {
         Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
                
    }
    
    @FXML
     public void Excel() throws Exception{
       
        try {
            
              ServiceTab_Reclamation ser=new ServiceTab_Reclamation();
           //  ServiceReclamation ser = new ServiceReclamation();
            ser.getDefendants("reclamation");
                 
              
        }catch (SQLException e) {
                   System.out.println("Failed to get data from database");
                 }
       
    
}

    





    

}

