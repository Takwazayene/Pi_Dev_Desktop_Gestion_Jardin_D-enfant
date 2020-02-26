/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;
import Utils.ConnexionBD;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.mailing;
import entities.Enfant;
import services.serviceParent;
import entities.DemandeInscri;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import services.serviceDemandeInscri;
import services.serviceParent;
import entities.Parent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.serviceenfant;


/**
 * FXML Controller class
 *
 * @author Hiba
 */
public class GererDemandeInscriController implements Initializable {
    int refus=0;
    @FXML
    private TableView<DemandeInscri> DemandesInscris;
    @FXML
    private TableColumn<DemandeInscri, String > idDemande;
    @FXML
    private TableColumn<DemandeInscri, String> nomP;
    @FXML
    private TableColumn<DemandeInscri, String> prenomP;
    @FXML
    private TableColumn<DemandeInscri, String> numP;
    @FXML
    private TableColumn<DemandeInscri, String> adres;
    @FXML
    private TableColumn<DemandeInscri, String> mail;
    @FXML
    private TableColumn<DemandeInscri, String> nomE;
    @FXML
    private TableColumn<DemandeInscri, String> prenomE;
    @FXML
    private TableColumn<DemandeInscri, String> ageE;
    @FXML
    private TableColumn<DemandeInscri,String > etatTaraitement;
    serviceDemandeInscri sv = new serviceDemandeInscri();
    serviceParent s = new serviceParent();
    serviceenfant svr= new serviceenfant();
    @FXML
    private Button Accept;
    @FXML
    private Button refuser;
    @FXML
    private BarChart<String, Integer> stat;

    Connection c= ConnexionBD.getInstance().getCnx();
    @FXML
    private TableView<Parent> tabParent;
    @FXML
    private TableColumn<Parent, String> nomP1;
    @FXML
    private TableColumn<Parent, String> prenomP1;
    @FXML
    private TableColumn<Parent, String> numP1;
    @FXML
    private TableColumn<Parent, String> adres1;
    @FXML
    private TableColumn<Parent, String> mail1;
    @FXML
    private TableColumn<Parent, String> IdParent;
    @FXML
    private TableView<Enfant> tabEnfant;
    @FXML
    private TableColumn<Enfant, String> idp;
    @FXML
    private TableColumn<Enfant, String> nomE1;
    @FXML
    private TableColumn<Enfant, String> prenomE1;
    @FXML
    private TableColumn<Enfant, String> ageE1;
    @FXML
    private TableColumn<Enfant, String> GR;
    @FXML
    private TableColumn<Enfant, String> IdEnfant;
    @FXML
    private Button Supprimer;
    @FXML
    private Button supprimerE;
    
    ObservableList<Enfant> listE  = FXCollections.observableArrayList();
    ObservableList<Parent> listP = FXCollections.observableArrayList();
    
   public void Afficher(){
         try {
        
         
         for(Enfant bb: svr.readAll())
             listE.add(bb);
         
        for(Parent cc: s.readAll())
             listP.add(cc);
     
     } catch (SQLException ex) {
         Logger.getLogger(serviceParent.class.getName()).log(Level.SEVERE, null, ex);
         Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);

     }

         //mettre les données dans la table view:  
         //Parent
         IdParent.setCellValueFactory(new PropertyValueFactory<>("id"));
         nomP1.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenomP1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         numP1.setCellValueFactory(new PropertyValueFactory<>("num"));
         adres1.setCellValueFactory(new PropertyValueFactory<>("adres"));
         mail1.setCellValueFactory(new PropertyValueFactory<>("mail"));
        
        
         //enfant 
         IdEnfant.setCellValueFactory(new PropertyValueFactory<>("id"));
         idp.setCellValueFactory(new PropertyValueFactory<>("IdParent"));
         nomE1.setCellValueFactory(new PropertyValueFactory<>("nomE"));
         prenomE1.setCellValueFactory(new PropertyValueFactory<>("prenomE"));
         ageE1.setCellValueFactory(new PropertyValueFactory<>("age"));
         GR.setCellValueFactory(new PropertyValueFactory<>("IdGroupe"));

        //load dummy data
        tabEnfant.setItems(listE);
        tabParent.setItems(listP);
         
     }
    
    
   public void afficher_demande(){
       
       ObservableList<DemandeInscri> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(DemandeInscri bb: sv.readAll2())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
     }

         //mettre les données dans la table view:    
         idDemande.setCellValueFactory(new PropertyValueFactory<>("IdDI"));
         nomP.setCellValueFactory(new PropertyValueFactory<>("nomP"));
         prenomP.setCellValueFactory(new PropertyValueFactory<>("prenomP"));
         numP.setCellValueFactory(new PropertyValueFactory<>("Num"));
         adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
         mail.setCellValueFactory(new PropertyValueFactory<>("Email"));
         nomE.setCellValueFactory(new PropertyValueFactory<>("nomE"));
         prenomE.setCellValueFactory(new PropertyValueFactory<>("prenomE"));
         ageE.setCellValueFactory(new PropertyValueFactory<>("ageE"));
         etatTaraitement.setCellValueFactory(new PropertyValueFactory<>("ETATt"));
       
        //load dummy data
        DemandesInscris.setItems(listu);
       
   }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // récuperer les données a partir de  la base 
          ObservableList<DemandeInscri> listu  = FXCollections.observableArrayList();
     try {
        
         
         for(DemandeInscri bb: sv.readAll2())
             listu.add(bb);
            
     
     } catch (SQLException ex) {
         Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
     }

         //mettre les données dans la table view:    
         idDemande.setCellValueFactory(new PropertyValueFactory<>("IdDI"));
         nomP.setCellValueFactory(new PropertyValueFactory<>("nomP"));
         prenomP.setCellValueFactory(new PropertyValueFactory<>("prenomP"));
         numP.setCellValueFactory(new PropertyValueFactory<>("Num"));
         adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
         mail.setCellValueFactory(new PropertyValueFactory<>("Email"));
         nomE.setCellValueFactory(new PropertyValueFactory<>("nomE"));
         prenomE.setCellValueFactory(new PropertyValueFactory<>("prenomE"));
         ageE.setCellValueFactory(new PropertyValueFactory<>("ageE"));
         etatTaraitement.setCellValueFactory(new PropertyValueFactory<>("ETATt"));
       
        //load dummy data
        DemandesInscris.setItems(listu);
        Afficher();
        
        //Statistique
        String req =" SELECT COUNT(`idDE`), `dateInscri` FROM demandeinscri GROUP BY `dateInscri`";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
            Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(req);
           int colonne1;
           String colonne2;
            while (rs.next()){
                colonne1=rs.getInt(1);
                colonne2=rs.getString(2);
                series.getData().add(new XYChart.Data<>(colonne2, colonne1));
            }
            stat.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(GererDemandeInscriController.class.getName()).log(Level.SEVERE, null, ex);
        }
       //FIN Statistique  
    }    

    @FXML
    private void handleAcceptButtonAction(ActionEvent event)throws SQLException {
     DemandeInscri Dselectionner = DemandesInscris.getSelectionModel().getSelectedItem();
     int iduser=Dselectionner.getIdDI();
     String nom =Dselectionner.getNomP();
     String prenom =Dselectionner.getPrenomP();
     int num =Dselectionner.getNum();
     String adress =Dselectionner.getAdres();
     String mail1 =Dselectionner.getEmail();
     String CIN =Dselectionner.getCin();
     String nomF =Dselectionner.getNomE();
     String prenomF =Dselectionner.getPrenomE();
     int age =Dselectionner.getAgeE();
      System.out.println("heloo");
     int e=Dselectionner.getETATt();
       
     System.out.println(e);
     
     if (e==0){
         
         
         System.out.println(sv.calculer_nbUser(mail1));
         //idha awel mara test yajouty il user mara wa7da w parent mara wa7da w yab3eth lil parent username et le mot de pass 
    if(sv.calculer_nbUser(mail1)==0){
   //ajout de user et parent m3aom enfant
   String R="client";
   Statement st =c.createStatement();
   String req = "INSERT INTO user (`id`,`username`, `password`, `role`) VALUES ('"+ iduser+"', '"+ mail1 + "', '" + CIN + "', '" + R + "');";
   st.executeUpdate(req);
   Parent p1=new Parent(iduser,nom,prenom,num,adress,mail1);
   int aman=s.ajouter(p1);
   Enfant e1=new Enfant(iduser,nomF,prenomF,age,1);
   System.out.println(e1.toString());
   svr.ajouterEnfant(e1);
   try {
            mailing.sendMail(p1.getMail(),"Bonjour, \n Nous somme ravis de vous annoncer que votre incription a été accepter \n votre username est: "+mail1+" \n votre password est :"+CIN+"\n Cordialement.");
        } catch (Exception ex) {
            Logger.getLogger(GererDemandeInscriController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    //idha parent eli houwa user existe et l'etat =0 ma3neha houwa bidou 3mal inscri il wlad a5er nemchi na5ou l'id mta3
    //eli login mt3aou egale mail wela il parent w n7ot.ha id mta3 il enfant fil ajout w haka najouty ken enfant jdid 
    else {
   //yapdity  
   int idPar=s.rechercheByName(mail1);
   Enfant e2=new Enfant(idPar,nomF,prenomF,age,1);
   System.out.println("hmd");
   svr.ajouterEnfant(e2);
   sv.updateEtat(Dselectionner,1);
   afficher_demande();
   try {
            mailing.sendMail(mail1,"Bonjour, \n Nous somme ravis de vous annoncer que votre incription a été accepter \n Cordialement.");
        } catch (Exception ex) {
            Logger.getLogger(GererDemandeInscriController.class.getName()).log(Level.SEVERE, null, ex);
        }}
   /*
    
     Enfant e=new Enfant(aman,nomF,prenomF,age,1);
     System.out.println(e.toString());
     svr.ajouterEnfant(e);
     System.out.println("ajouter avec suucées");
     
     
       try {
            mailing.sendMail(p1.getMail(),"Bonjour, \n Nous somme ravis de vous annoncer que votre incription a été accepter \n Cordialement.");
        } catch (Exception ex) {
            Logger.getLogger(GererDemandeInscriController.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
     }
     
     else {
         
         JOptionPane.showMessageDialog(null, "Demande d'incription déja traité");
     }
  
    }

    @FXML
    private void handleRefuserButtonAction(ActionEvent event) {
       
      DemandeInscri Dselectionner = DemandesInscris.getSelectionModel().getSelectedItem();
     String nom =Dselectionner.getNomP();
     String prenom =Dselectionner.getPrenomP();
     int num =Dselectionner.getNum();
     String adress =Dselectionner.getAdres();
     String mail1 =Dselectionner.getEmail();
     String CIN =Dselectionner.getCin();
     String nomF =Dselectionner.getNomE();
     String prenomF =Dselectionner.getPrenomE();
     int age =Dselectionner.getAgeE();
      System.out.println("heloo");
     int e=Dselectionner.getETATt();
       
     System.out.println(e);
     
     if (e==0){
         
     sv.updateEtat(Dselectionner,3);
     afficher_demande();
   
   
     }
     
     else {
         
         JOptionPane.showMessageDialog(null, "Demande d'incription déja traité");
     }
  
   
    /*  try {
            mailing.sendMail(gg.getEmail(),"Bonjour, \n  Votre incription a été refusé \n Cordialement.");
        } catch (Exception ex) {
            Logger.getLogger(GererDemandeInscriController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
     }*/
  
    }

    @FXML
    private void Supprimer(ActionEvent event) {
         ObservableList<Parent> SelectedRows, allpeople;
     
     allpeople = tabParent.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tabParent.getSelectionModel().getSelectedItems();
     
     for(Parent gg:SelectedRows){
       allpeople.remove(gg);
       s.supprimerParent(gg,gg.getId());
     }
    }

    @FXML
    private void SupprimerEnfant(ActionEvent event) {
        ObservableList<Enfant> SelectedRows, allpeople;
     
     allpeople = tabEnfant.getItems();
     // il donne les ligne qui vous avez déja séléctionner
     SelectedRows =tabEnfant.getSelectionModel().getSelectedItems();
     
     for(Enfant gg:SelectedRows){
       allpeople.remove(gg);
       svr.supprimerEnfant(gg,gg.getId());
     }
    }

    
    

    

    
        
    }
    

