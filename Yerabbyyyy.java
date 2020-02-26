/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yerabbyyyy;

import entities.Event;
import entities.clubs;
import java.sql.Date;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.serviceclubs;
import services.serviceevent;

/**
 *
 * @author ASUS
 */
public class Yerabbyyyy extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherEvent1.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Event e=new Event(1, 1, "ktlrt", "kh", 133, Date.valueOf(LocalDate.MAX), "ltler") ;
//      int nb=133;
// serviceevent srv1 = new serviceevent();
//       srv1.modifierNbr(el, 0);
        launch(args);
    }

}
