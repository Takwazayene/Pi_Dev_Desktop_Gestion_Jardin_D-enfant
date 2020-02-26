/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Parent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.ConnexionBD;
/**
 *
 * 
 */
public class serviceParent {
    Connection c= ConnexionBD.getInstance().getCnx();
    public int ajouter(Parent c1) throws SQLException {
       
       Statement st = c.createStatement();
       String requeteInsert = "INSERT INTO parent (`idP`, `nomP`, `prenomP`, `numP`, `adresP`, `mailP`) VALUES ('"+ c1.getId() + "', '" + c1.getNom() + "', '" + c1.getPrenom() + "', '" + c1.getNum()+ "', '" + c1.getAdres()+ "', '" + c1.getMail() + "');";
        int a=0 ;
        String requete = "Select Last_insert_id()" ;
       
       try {
            st=c.createStatement();
            Statement st1 =c.createStatement();
            st.executeUpdate(requeteInsert);
            ResultSet rs=st1.executeQuery(requete);
            if (rs.next()){
            String chaine = String.valueOf(rs.getString(1));
            a=Integer.parseInt(chaine);
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(serviceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return a;
    }
    
    public void afficherParent()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from parent");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("parent [ id: " +rs.getInt(1)  + " nom : " + rs.getString(2) + " prenom:  " + rs.getString(3)+ " numero " + rs.getInt(4)+ " address:  " + rs.getString(5)+ " mai:  " + rs.getString(6)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierNom(Parent cl, String nom,int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update Parent set nomP=? where idP='"+id+"'");
            pt.setString(1,nom);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    public void supprimerParent (Parent cl,int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from parent where idP='"+id+"'");
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceParent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 public void calculer() {
        int a ;
        String requete = "SELECT COUNT(*) FROM parent" ;
        try {
           
           Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          int l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
    }
       public int rechercheByName(String mail) {
           int id=0;
           Parent a = null;
         String requete = " select* from parent  where (mailP like '"+mail+"%')" ;
        try {
           
             Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
            if (rs.next())
            {
            String chaine = String.valueOf(rs.getString(1));
            int l=Integer.parseInt(chaine);
            String chaine1 = String.valueOf(rs.getString(4));
            int l1=Integer.parseInt(chaine);
            a=new Parent(l,rs.getString(2),rs.getString(3),l1,rs.getString(5),rs.getString(6));
            id=a.getId();
            System.out.println(id);}
            else System.out.println("n'existe pas");
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id ;
        
    }
 public List<Parent> readAll() throws SQLException {
            List<Parent> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from parent");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nomP=rs.getString(2);
               String prenomP=rs.getString(3);
               int numP=rs.getInt(4);
               String Add=rs.getString(5);
               String mailP=rs.getString(6);
               
               Parent D=new Parent(id,nomP, prenomP, numP,Add,mailP);
     arr.add(D);
     }
    return arr;
    }
}


