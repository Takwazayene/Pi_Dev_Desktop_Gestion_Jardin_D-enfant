/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import services.ServicesCategorie;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author Melliti
 */
public class CategoriesController implements Initializable {

    @FXML
    private TextField label;
    @FXML
    private Button saveCategories;
    @FXML
    private TableView<Categorie> afficheCategories;
    @FXML
    private TableColumn<Categorie, Integer> afficheCategoriesID;
    @FXML
    private TableColumn<Categorie, String> afficheCategoriesLabel;
    @FXML
    private Button supprimer;
    @FXML
    private BarChart<String, Integer> diagrammeCategories;

    ServicesCategorie u = new ServicesCategorie();
    @FXML
    private Button modifier;
    @FXML
    private Label verif;
    @FXML
    private Button goToProduits;
    @FXML
    private ImageView goToCommands;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        preparerStat();
        ObservableList<Categorie> listu = FXCollections.observableArrayList();
        ServicesCategorie service = new ServicesCategorie();
        try {
            List<Categorie> ls = service.readAll();
            ls.stream().forEach(listu::add);

            afficheCategories.setItems(listu);
            afficheCategoriesID.setCellValueFactory(new PropertyValueFactory<>("IdCategorie"));
            afficheCategoriesLabel.setCellValueFactory(new PropertyValueFactory<>("Label"));

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouter(ActionEvent event) {
        String nom = label.getText().toString();

        ServicesCategorie srv1 = new ServicesCategorie();
        Categorie c = new Categorie(1, nom);
        srv1.ajouter(c);

        ObservableList<Categorie> listu = FXCollections.observableArrayList();
        ServicesCategorie service = new ServicesCategorie();
        try {
            List<Categorie> ls = service.readAll();
            ls.stream().forEach(listu::add);

            /* try {
            for(Categorie bb: u.readAll())
                listu.add(bb);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            //mettre les données dans la table view:  
            // idclub.setCellValueFactory(new PropertyValueFactory<>("id"));
            afficheCategories.setItems(listu);
            afficheCategoriesID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            afficheCategoriesLabel.setCellValueFactory(new PropertyValueFactory<>("Label"));

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supprimerAction(ActionEvent event) {
    
     ObservableList<Categorie> SelectedRows, allpeople;
     
     allpeople = afficheCategories.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =afficheCategories.getSelectionModel().getSelectedItems();
     ServicesCategorie ser = new ServicesCategorie();
     for(Categorie gg:SelectedRows){
       allpeople.remove(gg);
       ser.supprimer(gg);
     }
    }

    
    public void preparerStat() {
        Connection c = ConnexionBD.getInstance().getCnx();
        
        String req1  = " SELECT * from Categories ";
        String req2  = " SELECT count(*) from produits where idCategorie =  ";
        ResultSet rs2;
        
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        
        try {
            PreparedStatement ste =  (PreparedStatement) c.prepareStatement(req1);

            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                
                req2+=rs.getInt(1);
                PreparedStatement ste2 = (PreparedStatement) c.prepareStatement(req2);
                rs2 = ste2.executeQuery();
                series.getData().add(new XYChart.Data<>(rs.getString(2), rs.getInt(1)));
            }
            diagrammeCategories.getData().add(series);

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void verifAction(MouseEvent event) {
    }
    /*******************************/

 
    

    @FXML
    private void goToComAction(MouseEvent event) throws IOException {
         Parent root;
        root = FXMLLoader.load(getClass().getResource("Commandes.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    
    }

    @FXML
    private void goToProdAction(MouseEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Produits.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    
}
/**
 * ****************************************************
 *
 */
