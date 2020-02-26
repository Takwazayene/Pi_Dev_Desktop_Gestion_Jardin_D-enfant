/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.LigneCommande;
import services.ServicesLigneCommandes;
import services.SerivcesCommande;
import entities.Commande;
import entities.Produit;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServicesCategorie;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author Melliti
 */
public class CommandesController implements Initializable {

    ObservableList<Commande> listu = FXCollections.observableArrayList();
    @FXML
    private TableView<Commande> listeCommandes;
    @FXML
    private Button suppCommandes;
    @FXML
    private JFXListView<Label> telleCommande;
    @FXML
    private TableColumn<Commande, Integer> id;
    @FXML
    private TableColumn<Commande, Boolean> etat;
    @FXML
    private TableColumn<Commande, Integer> client;
    @FXML
    private TableColumn<Commande, String> datecmd;
    @FXML
    private TableColumn<Commande, String> datelivraison;
    @FXML
    private TableColumn<Commande, Double> prixtotale;
    @FXML
    private Button afficher;
    @FXML
    private Button goTocateg;
    @FXML
    private Button goToProd;

    /**
     * Initializes the controller class.
     */
    private void loadData() {
        listu.clear();
        services.SerivcesCommande service = new SerivcesCommande();

        try {
            List<Commande> ls = service.readAll();
            ls.stream().forEach(listu::add);

            etat.setCellValueFactory(new PropertyValueFactory<>("etatCommande"));
            client.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            datecmd.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            datelivraison.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
            id.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
            prixtotale.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));

            listeCommandes.setItems(listu);

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //************************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadData();
    }
    //************************************/

    @FXML
    private void afficherCommadeAction(ActionEvent event) throws SQLException {

        /**
         * *******************
         */
        ObservableList<Commande> SelectedRows, allcommandes;

        Connection c = ConnexionBD.getInstance().getCnx();

        allcommandes = listeCommandes.getItems();

        telleCommande.getItems().clear();
        telleCommande.setFixedCellSize(200);
        telleCommande.setMinSize(200, 220);

        SelectedRows = listeCommandes.getSelectionModel().getSelectedItems();
        SerivcesCommande ser = new SerivcesCommande();

        int idcommande = 0;
        int idproduit = 0;
        for (Commande gg : SelectedRows) {

            String cm = "SELECT * from ligneCommandes l inner join commandes c  on l.idCommande= c.idCommande where c.idCommande=" + gg.getIdCommande();
            PreparedStatement pt1 = c.prepareStatement(cm);
            ResultSet rs = pt1.executeQuery();
            //idcommande = gg.getIdCommande();
            while (rs.next()) {
                idproduit = rs.getInt(3);
                String cm2 = "SELECT * from produits where idProduit = " + idproduit;
                PreparedStatement pt2 = c.prepareStatement(cm2);
                ResultSet rs2 = pt2.executeQuery();
                while (rs2.next()) {
                    Label l = new Label("Item  : " +rs2.getString(3)+"\n"+"Quantité : " + rs.getInt(3)+"\n"+"Prix : "+rs.getInt(5)+" dt" );
                    telleCommande.getItems().add(l);
                }
            }

        }

        /**
         * ******************
         */
       
        

    }

    @FXML
    private void goToCategAction(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("Categories.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }

    @FXML
    private void goToprodAction(ActionEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("Produits.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

}
