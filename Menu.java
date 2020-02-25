/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Test.Demande;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import functions.SendMail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.swing.JOptionPane;
import pidev.Entite.Tab_Reclamation;
import pidev.Service.ServiceTab_Reclamation;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author hp
 */
public class Menu extends Application {
    
    
    
    
    @Override
    public void start(Stage stage) throws IOException {
     //FXMLLoader Loader =  new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
      FXMLLoader Loader =  new FXMLLoader(getClass().getResource("Menu.fxml"));
       Parent root= Loader.load();
        Scene scene = new Scene(root);
       
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
         // SendMail.sendMail("wajih.mejri@esprit.tn", "hi","hi"); 
         
           

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      
        launch(args);
        PlayMusic("C:\\Users\\Hp\\Desktop\\eclamationjava - Copiefih table reclamation\\Jardin\\src\\pidev\\Test\\Demande\\son.mp3");
    }
    
    public static void PlayMusic(String filepath) {
        
        InputStream music; 
        try {
            music = new FileInputStream (new File(filepath));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);
            //AudioPlayer.player.start(audios);
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erreur");
        }
        
    }
    
}
