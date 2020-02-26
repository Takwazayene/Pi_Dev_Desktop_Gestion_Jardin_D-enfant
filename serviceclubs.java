/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.clubs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author ASUS
 */
public class serviceclubs {
    
    Connection c= ConnexionBD.getInstance().getCnx();
    public void ajouterclubs (clubs cl)
    {
        try {
            Statement st =c.createStatement();
         String req="insert into club(`idClub`,`nomClub`,`activiteClub`,`effectif`) values(null,'"+cl.getNom()+"','"+cl.getActivite()+"','"+cl.getEffectif()+"')";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierNom(clubs cl, String nom)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update club set nomClub=? where idClub=?");
            pt.setString(1,nom);
            pt.setInt(2,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public void modifierNbr(clubs cl, int nbr)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update club set effectif=? where idClub=?");
            pt.setInt(1,nbr);
            pt.setInt(2,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
     public void modifierNbr1(clubs cl)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update club set effectif=? where idClub=?");
            pt.setInt(1,cl.getEffectif()+1);
            pt.setInt(2,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public void modifierActivite(clubs cl, String activite)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update club set activiteClub=? where idClub=?");
            pt.setString(1,activite);
            pt.setInt(2,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
  /*  public void afficherclubs()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from club");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("club [ id: " +rs.getInt(1) + " nom " + rs.getString(2) + " activite : " + rs.getString(3) + " Effectif:  " + rs.getInt(4)+ "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    
      public List<clubs> readAll() throws SQLException {
            List<clubs> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from club");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(2);
               String activite=rs.getString(3);
               int effectif=rs.getInt(4);
               
               
               clubs c=new clubs(id,effectif,nom,activite);
     arr.add(c);
     }
    return arr;
    }
    
    
    
    
    
    
    
    
    
    public void supprimerclubs (clubs cl)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from `club` where idClub=?");
            pt.setInt(1,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public List<clubs> getTrier()  {
          List<clubs> arr1=new ArrayList<>();
        try {
   
    List<clubs> arr=new ArrayList<>();
    Statement st =c.createStatement();
    ResultSet rs=st.executeQuery("select * from club ORDER BY nomClub DESC");
     while (rs.next()) {                
              
              int id=rs.getInt(1);
              String nom=rs.getString(2);
              String activite=rs.getString(3);
              int effectif=rs.getInt(4);
               clubs p=new clubs(nom,activite,effectif);
               arr.add(p);
     }  
       
        return arr;

    }
    catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return arr1;
    }
    
    
    public clubs rechercheByName(String nomClub) {
          clubs a = null;
         String requete = " select* from club  where (nomClub like '"+nomClub+"%')" ;
        try {
           
             Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
            if (rs.next())
            {a=new clubs(rs.getString(2),rs.getString(3),rs.getInt(4));
            System.out.println(a.getNom());}
            else System.out.println("n'existe pas");
            
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
 
    
    
    
    
    
     public List<clubs> RechercherClubs(String nom) throws SQLException{

     List<clubs> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM club WHERE nomClub='"+nom+"'";
            Statement stm=c.createStatement();
            ResultSet rs=stm.executeQuery(req);
            while(rs.next())
            {    
            clubs a = new clubs();
            a.setId(rs.getInt("idClub"));
            a.setNom(rs.getString("nomClub"));
            a.setActivite(rs.getString("activiteClub"));
            a.setEffectif(rs.getInt("effectif"));
                   
                listrecherche.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceclubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }
     
     
     
     
     
     
     
     
     
     
          public void updatetab(clubs a) throws SQLException {
        
            PreparedStatement PS=c.prepareStatement("update `club` SET `nomClub`=? ,`activiteClub`=? ,`effectif`=? WHERE `idClub`=?");
            PS.setString(1,a.getNom());
             PS.setString(2, a.getActivite());
            PS.setInt(3, a.getEffectif());
           
            PS.setInt(4,a.getId());
            PS.executeUpdate();
       
    }
      
     

}
