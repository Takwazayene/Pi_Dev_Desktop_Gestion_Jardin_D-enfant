/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import entities.clubs;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.serviceclubs;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author amal
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<String, Integer> idstat;
   /* @FXML
    private NumberAxis ide;
    @FXML
    private CategoryAxis idc;*/

    
     Connection c = ConnexionBD.getInstance().getCnx();
      private serviceclubs serv = new serviceclubs();
    @FXML
    private Button btnRetour;
    /**
     * Initializes the controller class.
     */
              @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    
        String req =" SELECT nomClub,effectif from club ";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
            PreparedStatement ste = (PreparedStatement) c.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            idstat.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
         Parent tableview = FXMLLoader.load(getClass().getResource("AfficherClubs1.fxml"));
    
    
   
       Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
       
    



    
