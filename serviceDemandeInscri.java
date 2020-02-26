
package services;

import entities.DemandeInscri;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.ConnexionBD;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hiba
 */
public class serviceDemandeInscri {
   Connection c= ConnexionBD.getInstance().getCnx();
   Date dateCourante = new Date();
        DateFormat formatJJMMAAAA = new SimpleDateFormat("dd/MM/yyyy");
        String a=formatJJMMAAAA.format(dateCourante);
   public void ajouterDemandeInscri(DemandeInscri c1) throws SQLException {
       Statement st = c.createStatement();
       String requeteInsert = "INSERT INTO demandeinscri (`idDE`, `nomp`, `prenomp`, `nump`, `address`, `mail`, `nome`, `prenomE`, `age`, `cin`,`dateInscri`) VALUES (NULL, '" + c1.getNomP() + "', '" + c1.getPrenomE() + "', '" + c1.getNum() + "', '" + c1.getAdres() + "', '" + c1.getEmail() +  "', '" + c1.getNomE() + "', '" + c1.getPrenomE() + "', '" + c1.getAgeE() + "', '" + c1.getCin()+"', '" + a + "');";
        try {
            st=c.createStatement();
            st.executeUpdate(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public List<DemandeInscri> readAll() throws SQLException {
            List<DemandeInscri> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from demandeinscri");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nomP=rs.getString(2);
               String prenomP=rs.getString(3);
               int numP=rs.getInt(4);
               String Add=rs.getString(5);
               String mailP=rs.getString(6);
               String nomE=rs.getString(7);
               String prenomE=rs.getString(8);
               int age=rs.getInt(9);
               String cinp=rs.getString(10);
               DemandeInscri D=new DemandeInscri(id,nomP, prenomP, numP,Add,mailP,nomE,prenomE,age,cinp);
     arr.add(D);
     }
    return arr;
    }
    //readall de nouveaux champ etats
     
    public List<DemandeInscri> readAll2() throws SQLException {
            List<DemandeInscri> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from demandeinscri");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nomP=rs.getString(2);
               String prenomP=rs.getString(3);
               int numP=rs.getInt(4);
               String Add=rs.getString(5);
               String mailP=rs.getString(6);
               String nomE=rs.getString(7);
               String prenomE=rs.getString(8);
               int age=rs.getInt(9);
               String cinp=rs.getString(10);
               String date=rs.getString(11);
               int etat_trait=rs.getInt(12);
               int etat_payment=rs.getInt(13);
               DemandeInscri D=new DemandeInscri(id,nomP, prenomP, numP,Add,mailP,nomE,prenomE,age,cinp,date,etat_trait,etat_payment);
     arr.add(D);
     }
    return arr;
    }
    public List<DemandeInscri> getAllInscri(String login) throws SQLException{

     
         String requete = "SELECT * FROM demandeinscri where mail='" +login+"';";
         
        List<DemandeInscri> listInscri = new ArrayList<>();
        try {
           Statement stmt = c.createStatement();
          ResultSet  rs = stmt.executeQuery(requete);
            while (rs.next()) {
               int id=rs.getInt(1);
               String nomP=rs.getString(2);
               String prenomP=rs.getString(3);
               int numP=rs.getInt(4);
               String Add=rs.getString(5);
               String mailP=rs.getString(6);
               String nomE=rs.getString(7);
               String prenomE=rs.getString(8);
               int age=rs.getInt(9);
               String cinp=rs.getString(10);
               String date=rs.getString(11);
               int etat_trait=rs.getInt(12);
               int etat_payment=rs.getInt(13); 
               DemandeInscri D=new DemandeInscri(id,nomP, prenomP, numP,Add,mailP,nomE,prenomE,age,cinp,date,etat_trait,etat_payment);
                       

                listInscri.add(D);

            }

        } catch (SQLException ex) {
           Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
        return listInscri;

    }
    
    public void updateEtat(DemandeInscri cl,int E)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update demandeinscri set etatT=? where idDE=?");
            pt.setInt(1,E);
            pt.setInt(2,cl.getIdDI());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
     public void updateEtatP(DemandeInscri cl,int E)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update demandeinscri set etatP=? where idDE=?");
            pt.setInt(1,E);
            pt.setInt(2,cl.getIdDI());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceDemandeInscri.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public int calculer_nbE(String login) {
        int l=0 ;
        String requete = "SELECT COUNT(*) FROM demandeinscri where mail='"+login+"';";
        try {
           
           Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          l=Integer.parseInt(chaine);
            return l;}
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l;
    }
    public int calculer_nbUser(String login) {
        int l=0 ;
        String requete ="SELECT COUNT(*) FROM user where username='"+login+"';";
        try {
           
           Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          l=Integer.parseInt(chaine);
            return l;}
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l;
    }
    public void calculer() {
       
        String requete = "SELECT COUNT(`idDE`), `dateInscri` FROM demandeinscri GROUP BY `dateInscri`" ;
        try {
           
           Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
            
           
           int colonne1;
           String colonne2;
             
            
             while(rs.next()){
                colonne1=rs.getInt(1);
                colonne2=rs.getString(2);
                 System.out.println("Colonne1= "+colonne1 + "et Colonne2= "+colonne2);
             }
                     

            /*if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          int l=Integer.parseInt(chaine);
            System.out.println(l);}*/
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }
}
