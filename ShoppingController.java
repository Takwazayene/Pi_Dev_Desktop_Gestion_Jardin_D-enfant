package gui;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.gtranslate.Translator;
import com.gtranslate.URLCONSTANTS;
import com.gtranslate.text.TextTranslate;

import com.gtranslate.parsing.ParseTextTranslate;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import entities.Commande;
import entities.LigneCommande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Produit;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import services.SerivcesCommande;
import services.ServicesLigneCommandes;
import services.ServicesProduit;
import utils.ConnexionBD;

public class ShoppingController implements Initializable {

    @FXML
    private TableView<Produit> tableprod;

    @FXML
    private TableColumn<Produit, String> prodCol;

    @FXML
    private TableColumn<Produit, Integer> quantiteCol;

    @FXML
    private TableColumn<Produit, Integer> idCol;

    @FXML
    private Label imglabel;

    @FXML
    private Label prodlabel;

    @FXML
    private Label quantitelabel;

    @FXML
    private Label prixlabel;

    @FXML
    private Label descLabel;

    @FXML
    private Button viewButton;

    @FXML
    private Button addButton;

    @FXML
    private Button plusinfo;

    public static int Last_IdCommande;
    @FXML
    private Button commencerShopping;
    @FXML
    private Button descread;
    @FXML
    private Button descTranslate;
    @FXML
    private JFXComboBox<Integer> quantite;

    @FXML
    void ShoppingCartButton(ActionEvent event) throws IOException, SQLException {
        services.SerivcesCommande scmd = new SerivcesCommande();
        Last_IdCommande = scmd.LastID();

        System.err.println(Last_IdCommande);
        Parent root;
        root = FXMLLoader.load(getClass().getResource("ShoppingCart.fxml"));
        Scene newScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();

    }
/**************************************************/
    @FXML
    void addToCartButton(ActionEvent event) throws SQLException {
        if (tableprod.getSelectionModel().getSelectedItem() != null) {
            Produit selectedBook = tableprod.getSelectionModel().getSelectedItem();
            ////////////////////////////////////

            services.SerivcesCommande scmd = new SerivcesCommande();

            ////////////////////////////////////
            int lastID = scmd.LastID();

            
            LigneCommande lc = new LigneCommande(0, lastID, selectedBook.getIdProduit(), quantite.getValue());

            if (saveToCart(lc)) {
                Alert alert;
                System.out.println("last id :" + lastID);

            } else {
               
               //lert.showAndWait();
                return;
            }
        } else {
            Alert alert = new Alert(AlertType.NONE, "Please Select Book To Add!", javafx.scene.control.ButtonType.OK);
            alert.showAndWait();
            return;
        }
    }

    /**
     * ******************************************
     */
    private boolean saveToCart(LigneCommande cart) {

        services.ServicesLigneCommandes srlc = new ServicesLigneCommandes();
        srlc.ajouter(cart);

        return true;
    }

    /**
     * *******************************************
     */
    void mapButtonAction(ActionEvent event) {

    }

    /**
     * ******************************************
     */
    void sellButton(ActionEvent event) {

    }

    /**
     * *************************************
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ServicesProduit service = new ServicesProduit();
        ObservableList<Produit> listu = FXCollections.observableArrayList();

        try {
            List<Produit> ls = service.readAll();
            ls.stream().forEach(listu::add);

            idCol.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
            prodCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            quantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));

            tableprod.setItems(listu);

        } catch (SQLException ex) {
            Logger.getLogger(CategoriesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        

    }

    /**
     * *************************************
     */
    @FXML
    private void infoProduitAction(ActionEvent event) throws SQLException {
        PreparedStatement pt = null;
        ServicesProduit service = new ServicesProduit();
        ObservableList<Produit> SelectedRows, allpeople;

        allpeople = tableprod.getItems();
        // il donne les ligne qui vous avez déja séléctionner
        SelectedRows = tableprod.getSelectionModel().getSelectedItems();
        ServicesProduit ser = new ServicesProduit();
        for (Produit gg : SelectedRows) {
            pt = service.Rechercher(gg.getIdProduit());

        }

        ResultSet rs = pt.executeQuery();
        while (rs.next()) {
            prodlabel.setText(rs.getString(3));
            quantitelabel.setText(rs.getInt(2) + " PIECES DISPONIBLE .");
            quantite.getItems().addAll(generator(rs.getInt(2)+1));
            prixlabel.setText(rs.getInt(6) + " Dinar Tunisien .");
            descLabel.setText(rs.getString(4));
        }
        
    }

    /**
     * ************************************
     */
    @FXML
    private void createCommande(ActionEvent event) {

        services.SerivcesCommande scmd = new SerivcesCommande();
        Commande cmd;
        Date dateCommande = null, dateLivraison = null;
        cmd = new Commande(0, 0, 0, dateCommande, dateLivraison, 0, true);
        scmd.ajouter(cmd);
    }

    /**
     * ************************************
     */
    @FXML
    private void LireDescription(ActionEvent event) throws JavaLayerException {
        Audio a = Audio.getInstance();
        InputStream sound = null;
        try {
            System.out.println("Hello World!");
            Audio audio = Audio.getInstance();
            sound = audio.getAudio("Hello World", Language.ENGLISH);
            audio.play(sound);
        } catch (IOException | JavaLayerException ex) {
            Logger.getLogger(JavaLayerException.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sound.close();
            } catch (IOException ex) {
                Logger.getLogger(JavaLayerException.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * *************************************
     */
    @FXML
    private void TranslaterDescription(ActionEvent event) {

        Translator translate = Translator.getInstance();
        Audio a;
        //String text = translate.translate(descLabel.getText(), Language.FRENCH, Language.ARABIC);
        String prefix = translate.detect(descLabel.getText());
        //descLabel.setText(text);
        System.out.println(prefix);

    }

    /**
     * ************************************
     */
    private Integer[] generator(int num) {
        int size = (int) num; 
        Integer[] result = new Integer[size];

        for (int i = 0; i < num; i++) {
            result[i] = i ; 

        }
        return result;
    }

/*********************************************/
    public static void alert (String title,String content,AlertType alertType )
    {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();

    }
}
