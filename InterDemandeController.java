/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev.DataBase.DataBase;
import pidev.Entite.SendMail;
import pidev.Entite.Tab_Demande;
import pidev.Service.ServiceTab_Demande;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class InterDemandeController implements Initializable {
    ServiceTab_Demande stb = new ServiceTab_Demande();  
    private Statement ste;
    private Connection con;
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
    @FXML
    private TableColumn<Tab_Demande, String> etattab;
    private final ObservableList<Tab_Demande> data = FXCollections.observableArrayList();

    @FXML
    private Button Supprimer;
    @FXML
    private TextField recherche;
    @FXML
    private Button approuve;
        ObservableList<Tab_Demande> cls = FXCollections.observableArrayList();

    @FXML
    private BarChart<String, Number> barChart;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
        ServiceTab_Demande ser = new ServiceTab_Demande();
        List<Tab_Demande> lista = ser.readAll();
        for (Tab_Demande aux : lista)
        {
          cls.add(new Tab_Demande(aux.getNom(),aux.getPrenom(), aux.getId(), aux.getCin(),aux.getNumtel(),aux.getCv(),aux.getDateNaissance(),aux.getEtude(),aux.getPoste(),aux.getEtat()));  
                    AffichageTabDemande.setItems(cls);

        }
        
        nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        cintab.setCellValueFactory(new PropertyValueFactory<>("cin"));
        numteltab.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        cvtab.setCellValueFactory(new PropertyValueFactory<>("cv"));
        datenaisstab.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        etudetab.setCellValueFactory(new PropertyValueFactory<>("etude"));
        postetab.setCellValueFactory(new PropertyValueFactory<>("poste"));
        etattab.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        AffichageTabDemande.setItems(cls);
ServiceTab_Demande serr = new ServiceTab_Demande();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Demandes");
        series.getData().add(new XYChart.Data<>("Non Approuvé", serr.calculer()));
        series.getData().add(new XYChart.Data<>("Approuvé", serr.calculerapp()));
        barChart.getData().addAll(series);
}
        catch (SQLException ex) {
                    System.out.println(ex);
                 }
        RechercheAV();


    }
        public void Aff() throws SQLException{
            
            cls.clear();
        ServiceTab_Demande ser = new ServiceTab_Demande();
        List<Tab_Demande> list = ser.readAll();
        for (Tab_Demande aux : list)
        {
          cls.add(new Tab_Demande(aux.getNom(),aux.getPrenom(), aux.getId(), aux.getCin(),aux.getNumtel(),aux.getCv(),aux.getDateNaissance(),aux.getEtude(),aux.getPoste(),aux.getEtat()));  
        }
        
        
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cintab.setCellValueFactory(new PropertyValueFactory<>("cin"));
        numteltab.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        cvtab.setCellValueFactory(new PropertyValueFactory<>("cv"));
        datenaisstab.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        etudetab.setCellValueFactory(new PropertyValueFactory<>("etude"));
        postetab.setCellValueFactory(new PropertyValueFactory<>("poste"));
        etattab.setCellValueFactory(new PropertyValueFactory<>("etat"));

        AffichageTabDemande.setItems(cls);


ServiceTab_Demande serr = new ServiceTab_Demande();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Demandes");
        series.getData().add(new XYChart.Data<>("Non Approuvé", serr.calculer()));
        series.getData().add(new XYChart.Data<>("Approuvé", serr.calculerapp()));
    }

    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tab_Demande> filteredData = new FilteredList<>(cls, b -> true);
		
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
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                               		
             AffichageTabDemande.setItems(cls);

             ObservableList<Tab_Demande> allDemandes,SingleDemandes ;
             allDemandes=AffichageTabDemande.getItems();
             SingleDemandes=AffichageTabDemande.getSelectionModel().getSelectedItems();
             Tab_Demande A = SingleDemandes.get(0);
             ServiceTab_Demande STD = new ServiceTab_Demande(); // STD = Service TAB DEMANDE
             STD.delete(A.getId());
             SingleDemandes.forEach(allDemandes::remove);
             Aff();
             RechercheAV();

    }



    @FXML
 public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
 
    @FXML
  public void Change_Prenom(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setPrenom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
  
    @FXML
   public void Change_Numtel(TableColumn.CellEditEvent bb) throws SQLException{
     Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNumtel(Integer.parseInt(bb.getNewValue().toString()));
     stb.updatetab(tab_Demandeselected);
 }

    @FXML
    private void ApprouverDemande(MouseEvent event) throws SQLException {
                               con = DataBase.getInstance().getConnection();
             ste = con.createStatement();

                try {
                    
                    
                 ServiceTab_Demande ser = new ServiceTab_Demande();
                 Tab_Demande tmp2=AffichageTabDemande.getSelectionModel().getSelectedItem();
                 Tab_Demande C1 = new Tab_Demande(tmp2.getNom(),tmp2.getPrenom(), tmp2.getCin(),tmp2.getNumtel(),tmp2.getCv(),tmp2.getDateNaissance(),tmp2.getEtude());
                 Tab_Demande tab_Demandeselected = AffichageTabDemande.getSelectionModel().getSelectedItem();
                 tab_Demandeselected.setEtat("Approuvé");
                  stb.updateapp(tab_Demandeselected);
                    ser.ajouterens(C1);
                    Aff();
                    RechercheAV();
                    SendMail.sendMail("ichrak.salhi@esprit.tn", "Demande Approuvé", tab_Demandeselected.getNom()+" "+tab_Demandeselected.getPrenom()+" à etait approuvée");
                }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
                
        
    }
}
