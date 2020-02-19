/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import pidev.Entite.SendMail;
import pidev.Entite.Tab_Demande;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class AjouterDemandeController implements Initializable {

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
    private Statement ste;
    private Connection con;

    @FXML
    private ComboBox<String> comboBox;
    ServiceTab_Demande stb = new ServiceTab_Demande();
    
    ObservableList<String> list = FXCollections.observableArrayList("Enseignant(e)","Femme de ménage","Garde");
    @FXML
    private Button AjouterDemande;
    @FXML
    private TableView<Tab_Demande> AffichageTabDemande;
    @FXML
    private TableColumn<Tab_Demande, Integer> idtab;
    @FXML
    private TableColumn<Tab_Demande, String> nomtab;
    @FXML
    private TableColumn<Tab_Demande, String> prenomtab;
    @FXML
    private TableColumn<Tab_Demande, String> cintab;
    @FXML
    private TableColumn<Tab_Demande, Integer> numteltab;
    @FXML
    private TableColumn<Tab_Demande, String> cvtab;
    @FXML
    private TableColumn<Tab_Demande, java.sql.Date> datenaisstab;
    @FXML
    private TableColumn<Tab_Demande, String> etudetab;
    @FXML
    private TableColumn<Tab_Demande, String> postetab;
    private final ObservableList<Tab_Demande> data = FXCollections.observableArrayList();

    @FXML
    private Button Supprimer;
    @FXML
    private TextField recherche;
    
    public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from Tab_Demande");
            while(rs.next()){
                data.add(new Tab_Demande(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            cintab.setCellValueFactory(new PropertyValueFactory<>("cin"));
            numteltab.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            cvtab.setCellValueFactory(new PropertyValueFactory<>("cv"));
            datenaisstab.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
            etudetab.setCellValueFactory(new PropertyValueFactory<>("etude"));
            postetab.setCellValueFactory(new PropertyValueFactory<>("poste"));

            AffichageTabDemande.setItems(data);
            AffichageTabDemande.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            prenomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            cintab.setCellFactory(TextFieldTableCell.forTableColumn());
            numteltab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            cvtab.setCellFactory(TextFieldTableCell.forTableColumn());
            etudetab.setCellFactory(TextFieldTableCell.forTableColumn());

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
        FilteredList<Tab_Demande> filteredData = new FilteredList<>(data, b -> true);
		
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
		SortedList<Tab_Demande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageTabDemande.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageTabDemande.setItems(sortedData);
    }
    
    @FXML
    private void AjouterDemande(MouseEvent event) throws SQLException{
                       con = DataBase.getInstance().getConnection();
             ste = con.createStatement();

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
                    Aff();
                    RechercheAV();
                    SendMail.sendMail("lachihebines46@gmail.com", "demande", "nouvelle demande");
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
                
                    
}

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                               		
             AffichageTabDemande.setItems(data);

             ObservableList<Tab_Demande> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabDemande.getItems();
             SingleDemandes=AffichageTabDemande.getSelectionModel().getSelectedItems();
             Tab_Demande A = SingleDemandes.get(0);
             ServiceTab_Demande STD = new ServiceTab_Demande(); // STD = Service TAB DEMANDE
             STD.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
             RechercheAV();

    }



 public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
 
  public void Change_Prenom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.SetPrenom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
  
   public void Change_Numtel(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNumtel(Integer.parseInt(bb.getNewValue().toString()));
     stb.updatetab(tab_Demandeselected);
 }


    

}
