/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.AbonneResto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import entities.CarteFidelite;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.ServiceCarteFidelite;




/**
 *
 * @author ASUS
 */
public class ServiceCarteFidelite {
    
    
      Connection c = ConnexionBD.getInstance().getCnx();
    
    
    public void ajouterCarteFidelite(CarteFidelite cf){

   
    try{   
    String req = "INSERT INTO `cartefidelite` (idC,idAb,nbpoint,credit,benefice,status)" + " VALUES (?,?,?,?,?,?)";
    
    
        PreparedStatement pre = c.prepareStatement(req);
        pre.setInt(1,cf.getIdC());
        pre.setInt(2, cf.getIdAb());
        pre.setInt(3, cf.getNbPoint());
        pre.setFloat(4, cf.getCredit());
        pre.setFloat(5, cf.getBenefice());
        pre.setInt(6, cf.getStatus());

       
        pre.executeUpdate();
     
     }
      catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
     
       }
    }
    
    
     public void updateCarteFidelite(CarteFidelite cf ) {
               
               
               
               
        //String req = "UPDATE `abonneresto` SET  `nom`= ?, `typeAbo` = ?,`typePension`= ?, `absence`= ?  WHERE idAb="+id;
  String req=  "UPDATE `cartefidelite` SET `nbpoint` = '" +cf.getNbPoint()+"', `credit` = '" +cf.getCredit()+"', `benefice` = '"+cf.getBenefice()+"'  WHERE `idC` = "+cf.getIdC()+";";
        try {
            PreparedStatement st = c.prepareStatement(req);
        

            st.executeUpdate();

        } catch (SQLException ex) {
          Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
    
        public void supprimerCarteFidelite(CarteFidelite cf){
         try{
         PreparedStatement pt= c.prepareStatement("delete from cartefidelite where idC =? ") ;
         pt.setInt(1,cf.getIdC());
         pt.executeUpdate();
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
         
         
     }
        
        
        
           

            
        
         public List<CarteFidelite> getAll() {

        String requete = "Select * from cartefidelite";
        List<CarteFidelite> listCarte = new ArrayList<>();
        try {
           Statement stmt = c.createStatement();
          ResultSet  rs = stmt.executeQuery(requete);
            while (rs.next()) {
                CarteFidelite cf = new CarteFidelite(
                        rs.getInt("idC"),
                        rs.getInt("idAb"),
                        rs.getInt("nbpoint"),
                        rs.getFloat("credit"),
                        rs.getFloat("benefice"),
                         rs.getInt("status")) ;
                       

                listCarte.add(cf);

            }

        } catch (SQLException ex) {
           Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
        return listCarte;

    }
         
         
         public ObservableList<CarteFidelite> getAll(int idUser) throws SQLException {
     ObservableList<CarteFidelite> cartes = FXCollections.observableArrayList();
   //  ClientService cs =new ClientService();
     //ServiceEnseigne en=new ServiceEnseigne();
       
        String req = "SELECT * FROM `cartefidelite` where idAb='" +idUser+"';";
        
       try {
            Statement ste = c.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while (rs.next()) {
              
                CarteFidelite carte = new CarteFidelite(
                     rs.getInt("idC"),
                        rs.getInt("idAb"),
                        rs.getInt("nbPoint"),
                        rs.getFloat("credit"),
                        rs.getFloat("benefice"),
                         rs.getInt("status")) ;
                cartes.add(carte);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cartes;   
    }
         
         
         
          public boolean resetcarte(CarteFidelite carte) throws SQLException {
        
      
    
              
               String req2=  "UPDATE `cartefidelite` SET `nbpoint` = '0' WHERE `idC` = "+carte.getIdC()+";";
               Statement ste2 = c.createStatement();
               int x = ste2.executeUpdate(req2);
   
        return true;
    }
          
          
          public boolean bloquercarte(CarteFidelite carte) throws SQLException {
        
      
    
              
               String req2=  "UPDATE `cartefidelite` SET `status` = '0' WHERE `idC` = "+carte.getIdC()+";";
               Statement ste2 = c.createStatement();
               int x = ste2.executeUpdate(req2);
   
        return true;
    }
          
          
          
                  public int getIdCByLogin(int idUser) throws SQLException {
     ObservableList<CarteFidelite> cartes = FXCollections.observableArrayList();
   //  ClientService cs =new ClientService();
     //ServiceEnseigne en=new ServiceEnseigne();
       
        String req = "SELECT * FROM `cartefidelite` where idAb='" +idUser+"';";
        
 
            Statement ste = c.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while (rs.next()) {
              
                return rs.getInt("idC");
                
                
            
        
    
            }
               return -1 ;
                  }
               
                  
                  
         public float getCreditByIdC(int idC) throws SQLException {
     ObservableList<CarteFidelite> cartes = FXCollections.observableArrayList();
   //  ClientService cs =new ClientService();
     //ServiceEnseigne en=new ServiceEnseigne();
       
        String req = "SELECT * FROM `cartefidelite` where idC='" +idC+"';";
        
 
            Statement ste = c.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while (rs.next()) {
              
                return rs.getFloat("credit");
                
                
            
        
    
            }
               return -1 ;
                  }
         
         
           public int getPointByIdC(int idC) throws SQLException {
     ObservableList<CarteFidelite> cartes = FXCollections.observableArrayList();
   //  ClientService cs =new ClientService();
     //ServiceEnseigne en=new ServiceEnseigne();
       
        String req = "SELECT * FROM `cartefidelite` where idC='" +idC+"';";
        
 
            Statement ste = c.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while (rs.next()) {
              
                return rs.getInt("nbpoint");
                
                
            
        
    
            }
               return -1 ;
                  }
         
         
         
         
         
         
           public CarteFidelite chercherParIdC(int id) {
      CarteFidelite carte = null;
      
        String req = "select * from cartefidelite where idC=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = c.prepareStatement(req);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                  carte = new CarteFidelite(
                   
                  
                    rs.getInt("idC"),
                        rs.getInt("idAb"),
                        rs.getInt("nbPoint"),
                        rs.getFloat("credit"),
                        rs.getFloat("benefice"),
                         rs.getInt("status")) ;
                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return carte;   
    }

}

