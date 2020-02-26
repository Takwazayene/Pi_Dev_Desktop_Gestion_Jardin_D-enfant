/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import services.ServiceLogin;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginFXMLController implements Initializable {
 
    private MediaPlayer mp;
     private Media me;
     
     
    @FXML
    private Button login;
   
    @FXML
    private TextField username;
    @FXML
    private TextField password;
        static String usernameautho;
        static int idLogin ;
        
         @FXML
   private Label alert;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
     /* String path=new File("C:\\Users\\ASUS\\Desktop\\3a3\\semestre2\\PI\\resto\\GResto\\src\\utils\\music.mp3").getAbsolutePath();
        me=new Media(new File(path).toURI().toString());
        mp=new MediaPlayer(me);
//        mediaView.setMediaPlayer(mp);
        mp.setAutoPlay(true);*/
    /*   final DoubleProperty width = mediaView.fitWidthProperty();
    final DoubleProperty height = mediaView.fitHeightProperty();
    
    width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
    height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));*/
        // TODO
    }    
    
    
       @FXML
    private void btnLoginOnAction(ActionEvent event) throws SQLException, IOException
    {
            // client =new Client();
//             ConnexionService connexionservice=new ConnexionService();
//             Connexion con=new Connexion();
//             connexionservice.supprimer(con) ;
//              Connexion conn=new Connexion(username.getText(),password.getText());
//            ConnexionService cs=new ConnexionService();
//             cs.inserer(conn);
//   
              usernameautho=username.getText();
    
        
   ServiceLogin as=new ServiceLogin();
     String role;
     
       
          role = as.verifierUser(username.getText(),  password.getText());
         
     
    idLogin=as.finId(username.getText(),password.getText()) ;
   
        System.out.println(role);
      if (role.equals("admin")){
          System.out.println("hello");
    Parent root = FXMLLoader.load(getClass().getResource("GererDemandeInscri.fxml"));
    Scene newScene= new Scene(root);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(newScene);
       window.show();}
       else if(role.equals("client"))
            { 

                      //  c=ServiceLogin.rechercherUserByLogin(usernameautho);
             //   System.out.println(c.getEtatBlocage());
         // if(c.getEtatBlocage().equals("nonbloque"))  {   
             //     System.out.println("non bloque");
    Parent root = FXMLLoader.load(getClass().getResource("payement.fxml"));
    Scene newScene= new Scene(root);
    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
       window.setScene(newScene);
 window.show();
          }
       else if (role.equals("false"))
       {
          
           alert.setText("login ou mdp incorrect!!") ;
               
               }
    }
      
  

   
    
    }
    

