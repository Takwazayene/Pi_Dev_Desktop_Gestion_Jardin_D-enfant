/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.LigneCommande;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ServicesLigneCommandes;
import services.ServicesProduit;

/**
 * FXML Controller class
 *
 * @author Melliti
 */
public class PanierController implements Initializable {

    @FXML
    private Pane barreCateg;
    @FXML
    private ButtonBar barreCatalog;
    @FXML
    private TableView<LigneCommande> tablepanier;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label taxLabel;
    @FXML
    private Label shippingLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private TableColumn<LigneCommande, Integer> idCol;
    @FXML
    private TableColumn<LigneCommande, String> prodCol;
    @FXML
    private TableColumn<LigneCommande, Integer> quantiteCol;
    @FXML
    private Button supprimerLigne;
    @FXML
    private Button payment;

    int lastCommande3andi;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FXMLLoader inst = new FXMLLoader(getClass().getResource("ShoppingCart.fxml"));
        ShoppingController sch = new ShoppingController();
        int a = sch.Last_IdCommande;
        lastCommande3andi = a;

        ServicesLigneCommandes service = new ServicesLigneCommandes();
        ServicesProduit sp = new ServicesProduit();

        ObservableList<LigneCommande> listu = FXCollections.observableArrayList();

        try {

            List<LigneCommande> ls = service.readAllByCommandID(a);
            ls.stream().forEach(listu::add);

            idCol.setCellValueFactory(new PropertyValueFactory<>("idLigneCommandes"));

            //prodCol.getCellData();
            prodCol.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
            quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));

            tablepanier.setItems(listu);

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        ServicesLigneCommandes srv = new ServicesLigneCommandes() ; 
        try {
            srv.updatePrixLigne();
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            CalculTotalCommande();
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *******************************************
     */
    @FXML
    private void backButton(ActionEvent event) throws IOException {
          Parent root;
        root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    /**
     * *******************************************
     */
    @FXML
    private void CheckoutButton(ActionEvent event) throws IOException, SQLException {

       
            TableColumn<LigneCommande, Integer> column , idl; // column you want
            column =quantiteCol;
            idl = idCol;
            ServicesProduit ser = new ServicesProduit();
            
            for (int i = 0; i < tablepanier.getItems().size(); i++) {
                ser.DecrementerNbPieces(column.getCellData(i), ser.idProduitFromIdLigne(idl.getCellData(i)));
              }
            
            System.out.println(idl.getCellData(0));
            

        

        Parent root;
        root = FXMLLoader.load(getClass().getResource("CreditCardTransaction.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    /**
     * ***************************
     */
    @FXML
    private void removeButton(ActionEvent event) throws SQLException {
        ObservableList<LigneCommande> SelectedRows, allpeople;

        allpeople = tablepanier.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = tablepanier.getSelectionModel().getSelectedItems();
        ServicesLigneCommandes ser = new ServicesLigneCommandes();
        for (LigneCommande gg : SelectedRows) {
            allpeople.remove(gg);
            ser.supprimer(gg.getIdLigneCommandes());
        }
     CalculTotalCommande();
    }
/**
     * @return *************************************/
    public void CalculTotalCommande() throws SQLException
    {
        double getTotal = 0;
        services.SerivcesCommande ser = new services.SerivcesCommande();
        getTotal = ser.prixMte3Commande(lastCommande3andi);
        totalLabel.setText(""+getTotal + "DT");
        
        
    }
/******************************************/
}
