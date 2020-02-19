/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import pidev.DataBase.DataBase;
import pidev.Entite.Groupe;
import pidev.Entite.Matiere;
import pidev.Service.ServiceGroupe;
import pidev.Service.ServiceMatiere;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class AjouterMatiereController implements Initializable {
            private Statement ste;
    private Connection con;


    @FXML
    private TextField nom;
    @FXML
    private TextField coeff;
    @FXML
    private TextField nbh;
    @FXML
    private TableView<Matiere> AffichageTabMatiere;
    @FXML
    private TableColumn<Matiere, Integer> idtab;
    @FXML
    private TableColumn<Matiere, String> nomtab;
    @FXML
    private TableColumn<Matiere, Integer> coefftab;
    @FXML
    private TableColumn<Matiere, Integer> nbhtab;
    @FXML
    private TableColumn<Matiere, Integer> id_enseignanttab;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField recherche;
    @FXML
    private TextField id_enseignant;
    private final ObservableList<Matiere> data = FXCollections.observableArrayList();
        ServiceMatiere SM = new ServiceMatiere(); // SG = Service Groupe
    @FXML
    private Button AjouterMatiere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                                        Aff();

        RechercheAV();

    }    
         public void Aff(){
                        try {
            con = DataBase.getInstance().getConnection();
            ste = con.createStatement();
                        data.clear();

            ResultSet rs = ste.executeQuery("select * from Matiere");
            while(rs.next()){
                data.add(new Matiere(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            coefftab.setCellValueFactory(new PropertyValueFactory<>("coeff"));
            nbhtab.setCellValueFactory(new PropertyValueFactory<>("nbh"));
            id_enseignanttab.setCellValueFactory(new PropertyValueFactory<>("id_enseignant"));

            AffichageTabMatiere.setItems(data);
            AffichageTabMatiere.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            coefftab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            nbhtab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            id_enseignanttab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Matiere> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(groupe -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (groupe.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(groupe.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Matiere> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageTabMatiere.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageTabMatiere.setItems(sortedData);
    }

    private void AjouterMatiere(MouseEvent event) throws SQLException {
                                       con = DataBase.getInstance().getConnection();
             ste = con.createStatement();

                try {
                
                    ServiceMatiere ser =new ServiceMatiere();
                    ser.ajouter(new Matiere(nom.getText(),Integer.valueOf(coeff.getText()),Integer.valueOf(nbh.getText()),Integer.valueOf(id_enseignant.getText())));
                    Aff();
                    RechercheAV();
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }

    }

    @FXML
     public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Matiere tab_Matiereselected = AffichageTabMatiere.getSelectionModel().getSelectedItem();
     tab_Matiereselected.setNom(bb.getNewValue().toString());
     SM.updatetab(tab_Matiereselected);
 }

    @FXML
    private void Change_Coeff(TableColumn.CellEditEvent bb) throws SQLException{
     Matiere tab_Matiereselected = AffichageTabMatiere.getSelectionModel().getSelectedItem();
     tab_Matiereselected.setCoeff(Integer.parseInt(bb.getNewValue().toString()));
     SM.updatetab(tab_Matiereselected);

    }

    @FXML
    private void Change_Nbh(TableColumn.CellEditEvent bb) throws SQLException{
     Matiere tab_Matiereselected = AffichageTabMatiere.getSelectionModel().getSelectedItem();
     tab_Matiereselected.setNbh(Integer.parseInt(bb.getNewValue().toString()));
     SM.updatetab(tab_Matiereselected);

    }

    @FXML
    private void Change_IdEns(TableColumn.CellEditEvent bb) throws SQLException{
     Matiere tab_Matiereselected = AffichageTabMatiere.getSelectionModel().getSelectedItem();
     tab_Matiereselected.setId_enseignant(Integer.parseInt(bb.getNewValue().toString()));
     SM.updatetab(tab_Matiereselected);

    }

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                         AffichageTabMatiere.setItems(data);

             ObservableList<Matiere> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabMatiere.getItems();
             SingleDemandes=AffichageTabMatiere.getSelectionModel().getSelectedItems();
             Matiere A = SingleDemandes.get(0);
             ServiceGroupe STD = new ServiceGroupe(); // STD = Service TAB DEMANDE
             SM.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
             RechercheAV();

    }

    @FXML
    private void AjouterGroupe(MouseEvent event) throws SQLException {
                                       con = DataBase.getInstance().getConnection();
             ste = con.createStatement();

                try {
                
                    ServiceMatiere ser =new ServiceMatiere();
                    ser.ajouter(new Matiere(nom.getText(),Integer.valueOf(coeff.getText()),Integer.valueOf(nbh.getText()),Integer.valueOf(id_enseignant.getText())));
                    Aff();
                    RechercheAV();
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
    }
    
}
