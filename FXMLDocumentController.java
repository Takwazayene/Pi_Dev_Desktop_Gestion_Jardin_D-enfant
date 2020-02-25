/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package pidev.Test.Demande;

import com.twilio.rest.api.v2010.account.message.Media;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import org.junit.FixMethodOrder;

/**
 * FXML Controller class
 *
 * @author Hp
 */
/*public class FXMLDocumentController implements Initializable {
    
@FXML
private MediaView mv;
    
@FXML
private Button btn_play;
   
@FXML
private Button btn_stop;





    /**
     * Initializes the controller class.
     * @param url
     */
  /*  @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        
        String VUrl = "file:C:/Users/Hp/Desktop/vid/SCRUMFinale.mov";
        
        Media media = new Media(VUrl);
        MediaPlayer mediapalyer = new MediaPlayer(media);
        mv.setFitHeight(800);
        mv.setFitWidth(600);
        
        
        mv.setMediaPlayer(mediapalyer);
        
        //Mdeia
        
       
    // TODO
    }    
    @FXML
    private void onClick_btn_stop() {
      MediaPlayer.stop();  
      
       
    }
        
    @FXML 
    private void onClick_btn_play() {
        
        if (mediaplayer.getStatus()==PLAYING)
            
           mediaplayer.stop();
            mediaplayer.play();
    }
    else{
    mediaplayer.play();
}}
     //MediaPlayer.play();   
     
      
    
   
    
    

