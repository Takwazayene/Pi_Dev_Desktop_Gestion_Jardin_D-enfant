/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pidev.Entite.Tab_Demande;
import pidev.Service.ServiceTab_Demande;

/**
 *
 * @author wajih
 */
public class AjouterDemande extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
   ComboBox<String> comboBox;
   
       FXMLLoader Loader =  new FXMLLoader(getClass().getResource("AjouterDemande.fxml"));
       Parent root= Loader.load();
        Scene scene = new Scene(root);
        Button btn= (Button) scene.lookup("#Ajouter");
       
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                 TextField tmp=(TextField) scene.lookup("#nom");
                 String nom= (String) tmp.getText();
                 tmp=(TextField) scene.lookup("#prenom");
                 String prenom= (String) tmp.getText();
                 tmp=(TextField) scene.lookup("#cin");
                 String cin= (String) tmp.getText();
                 tmp=(TextField) scene.lookup("#numtel");
                 int numtel= (int) Integer.valueOf(tmp.getText());
                 tmp=(TextField) scene.lookup("#cv");
                 String cv= (String) tmp.getText();
                 DatePicker tmpdate=(DatePicker) scene.lookup("#date_naiss");
                 String date= (String) tmpdate.getValue().toString();
                 tmp=(TextField) scene.lookup("#etude");
                 String etude= (String) tmp.getText();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                System.out.println(date);
                java.util.Date myDate = new java.util.Date(date);
                System.out.println(myDate);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                System.out.println(sqlDate);
 

                ComboBox tmpcmb = (ComboBox) scene.lookup("#comboBox");
                String poste = tmpcmb.getValue().toString();
                         Tab_Demande c1 = new Tab_Demande(nom, prenom,cin,numtel,cv,sqlDate,etude,poste);

                                  ServiceTab_Demande ser = new ServiceTab_Demande();

                    ser.ajouter(c1);
                    List<Tab_Demande> list = ser.readAll();
                    System.out.println("\nALL");
                    System.out.println(list);
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
            }
        });
        stage.setTitle("Ajouter Utilisateur");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
