/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Produit;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServicesCategorie;
import services.ServicesProduit;

/**
 * FXML Controller class
 *
 * @author Melliti
 */
public class ProduitsController implements Initializable {

    @FXML
    private TextField libelle;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Produit> affichage;
    @FXML
    private TableColumn<Produit, Integer> affid;
    @FXML
    private TableColumn<Produit, String> afflibelle;
    @FXML
    private TableColumn<Produit, String> affcategorie;
    @FXML
    private TableColumn<Produit, Double> affprix;
    @FXML
    private TableColumn<Produit, Integer> affquantite;
    @FXML
    private TableColumn<Produit, String> affdesc;
    @FXML
    private TextField prix;
    @FXML
    private TextArea desc;
    @FXML
    private ChoiceBox<Integer> quantite;
    @FXML
    private ChoiceBox<String> categorie;
    @FXML
    private Button img;
    @FXML
    private Button supprimer;
    @FXML
    private Label labforpath;

    String mine;
    @FXML
    private Label verifprix;
    @FXML
    private Label veriflibelle;
    @FXML
    private Label verifdesc;
    @FXML
    private Button goToCateg;
    @FXML
    private Button goToCom;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */

    /**
     * ******************************
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affichage.setEditable(true);
        ServicesProduit service = new ServicesProduit();
        ServicesCategorie service2 = new ServicesCategorie();

        quantite.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        List<String> Ensemble = null;
        try {
            Ensemble = service2.preparerListeCategories();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        categorie.getItems().addAll(Ensemble);

        ObservableList<Produit> listu = FXCollections.observableArrayList();

        try {
            List<Produit> ls = service.readAll();
            ls.stream().forEach(listu::add);

            affid.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
            afflibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            affcategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
            affprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            affquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            affdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
            affichage.setItems(listu);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouterAction(ActionEvent event) throws SQLException {

        ServicesCategorie service2 = new ServicesCategorie();

        String lib = libelle.getText().toString();
        int quant = quantite.getValue();
        String pri = prix.getText();

        double prx = Double.parseDouble(pri);

        String categ = categorie.getValue().toString();
        int idCategorie = 5;

        String des = desc.getText();
        String img = mine;

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        ServicesProduit srv1 = new ServicesProduit();
        Produit c = new Produit(0, quant, lib, des, img, prx, date, 5);
        srv1.ajouter(c);

        /**
         * ****************************************
         */
        ObservableList<Produit> listu = FXCollections.observableArrayList();

        List<Produit> ls = srv1.readAll();

        ls.stream().forEach(listu::add);
        affid.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        afflibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        affcategorie.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        affprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        affquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        affdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        affichage.setItems(listu);
        /**
         * *********************************************
         */

    }

    @FXML
    private void supprimerAction(ActionEvent event) {
       
     ObservableList<Produit> SelectedRows, allpeople;
     
     allpeople = affichage.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =affichage.getSelectionModel().getSelectedItems();
     ServicesProduit ser = new ServicesProduit();
     for(Produit gg:SelectedRows){
       allpeople.remove(gg);
       ser.supprimer(gg);
     }
    }

    @FXML
    private void uploadimg(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("images", "*.jpg"));
        File f = fc.showOpenDialog(null);

        if (f != null) {
            labforpath.setText("L'image Sélectionné : " + f.getAbsolutePath());
        }

        mine = f.getAbsolutePath();

    }
/*************************/
    @FXML
    private void goToCategAction(ActionEvent event) throws IOException {
          Parent root;
        root = FXMLLoader.load(getClass().getResource("Categories.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
/**************************/
    @FXML
    private void goToComAction(ActionEvent event) throws IOException {
          Parent root;
        root = FXMLLoader.load(getClass().getResource("Commandes.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
/**************************/
    @FXML
    private void modifierAction(ActionEvent event) {
    }

}
