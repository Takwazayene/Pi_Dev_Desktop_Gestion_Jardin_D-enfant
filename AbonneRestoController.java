/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import entities.AbonneResto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceAbonneResto;
import javafx.scene.control.*;
import entities.Enfant;
import service.serviceenfant;
//import javafx.scene.control.Alert;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AbonneRestoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   @FXML
   private TextField idAb ;
    @FXML
   private TextField nom;
    @FXML
    private TextField absence;
    @FXML
    private TextField search;
    @FXML
    private Button ajouterAbo;
    @FXML
    private Button detail;
       @FXML
    private ComboBox<String> typeAbo;
    @FXML
    private ComboBox<String> typePension;
   private Label alert;
    
    
         
    @FXML
    private TableView<AbonneResto> table;
     @FXML
    private TableView<Enfant> table2;
    @FXML
    private TableColumn<AbonneResto, Integer> CidAb;
    @FXML
    private TableColumn<AbonneResto, String> Cnom;
    @FXML
    private TableColumn<AbonneResto, String> CtypeAbo;
    @FXML
      private TableColumn<AbonneResto, String> CtypePension;
    @FXML
    private TableColumn<AbonneResto, Integer> Cetat;
    @FXML
    private TableColumn<AbonneResto, Integer> Cabsence;
    @FXML
    private TableColumn<AbonneResto, DatePicker > CdateAbo;
    
    
    
       @FXML
    private TableColumn<Enfant, Integer> id;
    @FXML
      private TableColumn<Enfant, Integer> idParent;
    @FXML
    private TableColumn<Enfant, String> nomE;
    @FXML
    private TableColumn<Enfant, String> prenomE;
    @FXML
    private TableColumn<Enfant, Integer > age;
     @FXML
    private TableColumn<Enfant, Integer > idGroupe;
    
    
      private ObservableList<AbonneResto> abonne;
        private ObservableList<Enfant> enfant;
    @FXML
    private Button modifierAbo;
    @FXML
    private Button supprimerAbo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             typeAbo.getItems().addAll("annuel","mensionnel","semaine");
                typeAbo.setValue("annuel");
                
                typePension.getItems().addAll("complete","Demi p1","Demi p2");
                typePension.setValue("complete");
               afficher();
            loadData();
         //   recupererLesValeurs();
                setCellTable(); 
                search();
        // TODO
    }    


 private void afficher(){

        ServiceAbonneResto service= new ServiceAbonneResto();
        abonne = FXCollections.observableArrayList();
        
        List<AbonneResto> ls = service.getAll();
        ls.stream().forEach(abonne::add);
        
             /*   fieldcategorie.getItems().addAll("clothes","accessories","baskets");
                fieldcategorie.setValue("clothes");
                
                fieldmarque.getItems().addAll("Adidas","Nike");
                fieldmarque.setValue("Adidas");*/
                
                
            


          ajouterAbo.setVisible(true);

        table.setItems(abonne);
        CidAb.setCellValueFactory(new PropertyValueFactory<>("idAb")); 
        Cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CtypeAbo.setCellValueFactory(new PropertyValueFactory<>("typeAbo"));
        CtypePension.setCellValueFactory(new PropertyValueFactory<>("typePension"));
        Cetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Cabsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
        CdateAbo.setCellValueFactory(new PropertyValueFactory<>("dateAbo"));
      recupererLesValeurs();
        loadData();
        
    }  
 
    public void recupererLesValeurs(){
        table.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
            
        
            
                 AbonneResto a=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                idAb.setText(Integer.toString(a.getIdAb()));
                
                 nom.setText(a.getNom());
                 
                  typeAbo.setValue(a.getTypeAbo());
                  typePension.setValue(a.getTypePension());
      
                absence.setText(Integer.toString(a.getAbsence()));
                 
                 

//String output = typeAbo.getSelectionModel().getSelectedItem().toString();

                   }
            
        });
    }
    
    private void loadData(){
       ServiceAbonneResto ps= new  ServiceAbonneResto();
        ArrayList p= (ArrayList) ps.getAll();
        ObservableList matchObservable;
        matchObservable = FXCollections.observableArrayList(p);
        table.setItems(matchObservable);   
        CidAb.setCellValueFactory(new PropertyValueFactory<>("idAb")); 
        Cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CtypeAbo.setCellValueFactory(new PropertyValueFactory<>("typeAbo"));
        CtypePension.setCellValueFactory(new PropertyValueFactory<>("typePension"));
        Cetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Cabsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
        CdateAbo.setCellValueFactory(new PropertyValueFactory<>("dateAbo"));
        
       // afficher();
    }
    
      private void setCellTable(){
          CidAb.setCellValueFactory(new PropertyValueFactory<>("idAb")); 
        Cnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CtypeAbo.setCellValueFactory(new PropertyValueFactory<>("typeAbo"));
        CtypePension.setCellValueFactory(new PropertyValueFactory<>("typePension"));
        Cetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        Cabsence.setCellValueFactory(new PropertyValueFactory<>("absence"));
        CdateAbo.setCellValueFactory(new PropertyValueFactory<>("dateAbo"));
          
          
        
    }
    
    
    
    
       private void clearTextfField(){
        idAb.clear();
        nom.clear();
        absence.clear();
     
        
        afficher();



    }
    
    @FXML
        private void Update(ActionEvent event) {
           
                 /*   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      
        alert.setTitle("Modification");
        alert.setHeaderText("Voulez-vous vraiment modifier cette Cateorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){*/
           // Product pre = new Product(nomProduit, 0, 0, brochure)
       //     Product pre = new Product(Float.parseFloat(fieldprix.getText()), Integer.parseInt(fieldstock.getText()),fieldnom.getText(), fieldimg.getText());
       
         //AbonneResto a =new Product(Cnom.getText(),CtypeAbo.getText(),Integer.parseInt(fieldstock.getText()),fieldimg.getText());
         AbonneResto a= new AbonneResto(nom.getText(),typeAbo.getValue(),typePension.getValue(),Integer.parseInt(absence.getText()));
         

          AbonneResto aa= (AbonneResto)table.getSelectionModel().getSelectedItem();
           
            ServiceAbonneResto ps=new ServiceAbonneResto();
            
          
            ps.updateAbonneResto(a,aa.getIdAb());
            loadData();
           clearTextfField();
           
           
           
           
        }
        
        @FXML
    private void Delete(ActionEvent event) {
        ServiceAbonneResto ps =new ServiceAbonneResto();
        AbonneResto a=(AbonneResto)table.getSelectionModel().getSelectedItem();
          /*  if (p == null) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Vous n'avez pas préciser la categorie à supprimer !");
            alert.setContentText("Veuillez Sélectionner une categorie");
            alert.showAndWait();
    }*/
           // else {
            //Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //alert.initOwner(table.getScene().getWindow());
            //alert.setTitle("Confirmation");
            //alert.setHeaderText("Suppression categorie");
            //alert.setContentText("Voulez vous vraiment supprimer cette categorie");
            //alert.showAndWait().ifPresent(response -> {
              //  if (response == ButtonType.OK) {

                 
                    ps.supprimerAbonneResto(a);
                   loadData();
                    clearTextfField();

               // }
           // }
//);
         // }
    }
    
    
    private void search(){
         ServiceAbonneResto cs= new ServiceAbonneResto();
        ArrayList match= (ArrayList) cs.getAll();
        ObservableList abonneObservable;
        abonneObservable = FXCollections.observableArrayList(match);
 
       FilteredList<AbonneResto> filteredData = new FilteredList<>(abonneObservable, p -> true);

// 2. Set the filter Predicate whenever the filter changes.
search.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredData.setPredicate(AbonneResto -> {
        // If filter text is empty, display all persons.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        // Compare first name and last name of every person with filter text.
        String lowerCaseFilter = newValue.toLowerCase();

        if (String.valueOf(AbonneResto.getTypeAbo()).toLowerCase().contains(lowerCaseFilter)) {
            return true;
             // Filter matches first name.

        } else if (String.valueOf(AbonneResto.getTypePension()).toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        }
        else if (String.valueOf(AbonneResto.getIdAb()).toLowerCase().contains(lowerCaseFilter)) {                                  
            return true; // Filter matches last name.
        }
         else if (String.valueOf(AbonneResto.getNom()).toLowerCase().contains(lowerCaseFilter)) {                                  
            return true; // Filter matches last name.
        }
     else if (String.valueOf(AbonneResto.getDateAbo()).toLowerCase().contains(lowerCaseFilter)) {                                  
            return true; // Filter matches last name.
        }
      else if (String.valueOf(AbonneResto.getAbsence()).toLowerCase().contains(lowerCaseFilter)) {                                  
            return true; // Filter matches last name.
        }

        return false; // Does not match.
    });



      // 3. Wrap the FilteredList in a SortedList. 
      SortedList<AbonneResto> sortedData = new SortedList<>(filteredData);    

      // 4. Bind the SortedList comparator to the TableView comparator.
  sortedData.comparatorProperty().bind(table.comparatorProperty());
     // 5. Add sorted (and filtered) data to the table.
     table.setItems(sortedData);
     // the function 
     setCellTable();
     

});
    }
    
    
         private boolean testAbsence(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
     }
    
    
    @FXML
     private void ajouter(ActionEvent event) {
        /*   Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
         String date1 = "22/06/2022";
         try{
          date = simpleDateFormat.parse(date1);
         
         } 
         catch (ParseException e) {
      e.printStackTrace();
    }*/

       
         if (nom.getText().equals("") || typeAbo.getValue().equals("")||  typePension.getValue().equals("") || testAbsence(absence.getText()) == false )
             {
              /*   Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("!!!!!!!");
            alert.setHeaderText("Check that all fields are correctly filled");
            alert.setContentText("Please Retape");
          alert.showAndWait(); 
                 sa.ajouterAbonneResto(a);*/
        alert.setText("Veuillez remplir tous les champs!!!") ;

        }  
         else{   
    
    
           AbonneResto a= new AbonneResto(nom.getText(),typeAbo.getValue(),typePension.getValue(),0,Integer.parseInt(absence.getText()));
            ServiceAbonneResto sa= new  ServiceAbonneResto();   
            
                 try {
                    
        
            Parent root= FXMLLoader.load(getClass().getResource("abonneResto.fxml"));
            ajouterAbo.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RestoFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
             sa.ajouterAbonneResto(a);
            
   

              
            
                
              
    
         }
         setCellTable();
        loadData();
   
        clearTextfField();
}
     
     
     @FXML
     private void Details()
     {
         AbonneResto aa= (AbonneResto)table.getSelectionModel().getSelectedItem();
           
           
       
     
             ServiceAbonneResto service= new ServiceAbonneResto();
        enfant = FXCollections.observableArrayList();
        
        List<Enfant> ls = service.DetailsAbonne(aa.getIdAb());
        ls.stream().forEach(enfant::add);
        
             /*   fieldcategorie.getItems().addAll("clothes","accessories","baskets");
                fieldcategorie.setValue("clothes");
                
                fieldmarque.getItems().addAll("Adidas","Nike");
                fieldmarque.setValue("Adidas");*/
                
             


          detail.setVisible(true);

        table2.setItems(enfant);
       id.setCellValueFactory(new PropertyValueFactory<>("id")); 
        idParent.setCellValueFactory(new PropertyValueFactory<>("idParent"));
        nomE.setCellValueFactory(new PropertyValueFactory<>("nomE"));
        prenomE.setCellValueFactory(new PropertyValueFactory<>("prenomE"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        idGroupe.setCellValueFactory(new PropertyValueFactory<>("idGroupe"));
      
    //  recupererLesValeurs();
           
    
        
    }

    @FXML
    private void onabonneclicked(ActionEvent event) {
    }

    @FXML
    private void oncarteclicked(ActionEvent event) {
    }
                
}


