/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.AbonneResto;
import entities.CarteFidelite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import entities.Paiement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServicePaiement {
    
     Connection c = ConnexionBD.getInstance().getCnx();
    
    
     public void payer(Paiement p){

 
    try{
           
 
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
String da = dateFormat.format(date) ;
java.sql.Date sqlDate = java.sql.Date.valueOf( da );
   
   
            
       
    String req = "INSERT INTO `paiement` (idP,idC,type,total,date)" + " VALUES (?,?,?,?,?)";
    
    
        PreparedStatement pre = c.prepareStatement(req);
        pre.setInt(1,p.getIdP());
        pre.setInt(2, p.getIdC());
        pre.setString(3, p.getType());
        pre.setFloat(4, p.getTotal());
         pre.setDate(5, sqlDate);
       
        pre.executeUpdate();
      
     }
      catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
     
       }
    }
     
     
        public ObservableList<Paiement> getAll(int idC)  {
     ObservableList<Paiement> paiements= FXCollections.observableArrayList();
   //  ClientService cs =new ClientService();
     //ServiceEnseigne en=new ServiceEnseigne();
       
        String req = "SELECT * FROM `paiement` where idC='" +idC+"';";
        
       try {
            Statement ste = c.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while (rs.next()) {
              
                Paiement paiement = new Paiement(
                     rs.getInt("idP"),
                        rs.getInt("idC"),
                        rs.getString("type"),
                        rs.getFloat("total"),
                        rs.getDate("date"));
                    
                paiements.add(paiement);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return paiements;   
    }
        
        
             public List<Paiement> getAll() {

        String requete = "Select * from paiement";
        List<Paiement> listPaiement = new ArrayList<>();
        try {
           Statement stmt = c.createStatement();
          ResultSet  rs = stmt.executeQuery(requete);
            while (rs.next()) {
             Paiement paiement = new Paiement(
                     rs.getInt("idP"),
                        rs.getInt("idC"),
                        rs.getString("type"),
                        rs.getFloat("total"),
                        rs.getDate("date"));
                    
                listPaiement.add(paiement);

            }

        } catch (SQLException ex) {
           Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
        return listPaiement;

    }
             
             
                  public float CalculTotalCaisse() {
         float caisse = 0 ; 
        String requete = "Select * from paiement";
        List<Paiement> listPaiement = new ArrayList<>();
        try {
           Statement stmt = c.createStatement();
          ResultSet  rs = stmt.executeQuery(requete);
            while (rs.next()) {
            
                    
                caisse = caisse + rs.getFloat("total") ;

            }

        } catch (SQLException ex) {
           Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
        return caisse;

    }
    
}
