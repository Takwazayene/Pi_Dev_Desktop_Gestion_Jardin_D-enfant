/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Resto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import entities.ProduitResto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Instant;

/**
 *
 * @author ASUS
 */
public class ServiceProduitResto {
        Connection c = ConnexionBD.getInstance().getCnx();
    
    
    public void ajouterProduitResto(ProduitResto p){
    /* try{
       Statement st = c.createStatement();
        String req="insert into personne values("'+p.getId()+' ","'+p.getNom()+'","'+p.getPrenom()+'")" ;
        
        st.executeUpdate(req);
     }
          catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }*/
     try{
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
Date d= p.getDelaiExp();
String da = dateFormat.format(d) ;
java.sql.Date sqlDate = java.sql.Date.valueOf( da );

    String req = "INSERT INTO `produitresto` (idP,nomP,typeP,qte,delaiExp)" + " VALUES (?,?,?,?,?)";
        PreparedStatement pre = c.prepareStatement(req);
        pre.setInt(1,p.getIdP() );
        pre.setString(2, p.getNomP());
        pre.setString(3, p.getTypeP());
        pre.setInt(4, p.getQte());
        pre.setDate(5, sqlDate);
       
        pre.executeUpdate();
     }
      catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
     
       }
    }
     
      

      
 
       
       public void modifierProduitResto(ProduitResto p,int qte) {
           try{
        PreparedStatement pt=c.prepareStatement("update produitresto set qte= ? where idP = ?");
        pt.setInt(1,qte);
        pt.setInt(2,p.getIdP());
        pt.executeUpdate() ;
           }
             catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
        
       }
       
    /*   public void incrementerEffectif(String typer)
       {
           try{
              PreparedStatement pt=c.prepareStatement("select effectif from  resto where typeA = '" + typer + "';"  );
                ResultSet rs=pt.executeQuery();

                
               
             
            while(rs.next()) { //identifier de id=1
                 System.out.println("Resto [ effectif : "+rs.getInt(1) + " ]");
                 int effectif= rs.getInt(1) +1 ;
        PreparedStatement ptt=c.prepareStatement("update resto set effectif= ? where  typeA = '" + typer + "';");
        ptt.setInt(1,effectif);
        ptt.executeUpdate() ;
             }
              
           }
           
               catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
          
              
       }*/
     public void afficherProduitRestos(){
         try{
           PreparedStatement pt=c.prepareStatement("select * from produitresto");
           ResultSet rs=pt.executeQuery();
         
             
             while(rs.next()) { //identifier de id=1
                 System.out.println("Produit Resto [ idP : "+rs.getInt(1)+ " nom produit : " +rs.getString(2) + " type produit : " + rs.getString(3) +  " quantite : " + rs.getInt(4) +  " delai Exp : " + rs.getDate(5) + " ]");
             }
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
             
         
     }
     
     public void supprimerProduitResto(ProduitResto p){
         try{
         PreparedStatement pt= c.prepareStatement("delete from produitresto where idP =? ") ;
         pt.setInt(1,p.getIdP());
         pt.executeUpdate();
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
         
         
     }
    
}
