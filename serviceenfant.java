/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Enfant;
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
public class serviceenfant {
    
    Connection c= ConnexionBD.getInstance().getCnx();
    
    /*public void ajouter(Enfant c1) throws SQLException {
       Statement st = c.createStatement();
       String requeteInsert = "INSERT INTO enfant (`idE`, `idP`, `nom`, `prenom`, `age`, `idG`) VALUES ('" + c1.getId()+ "', '" + c1.getIdParent() + "', '" + c1.getNomE() + "', '" + c1.getPrenomE() + "', '" +c1.getAge() + "', '" + c1.getIdGroupe()+"');";
        try {
            st=c.createStatement();
            st.executeUpdate(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public void ajouterEnfant (Enfant e)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into enfant values('" +e.getId()+ "', '" + e.getIdParent() + "', '" + e.getNomE() + "', '" + e.getPrenomE() + "', '" +e.getAge() + "', '" + e.getIdGroupe()+"');";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
      

      
    public void modifierNom(Enfant cl, String nom)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update enfant set nom=? where idE=?");
            pt.setString(1,nom);
            pt.setInt(2,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
 
   /*public void modifierPreom(Enfant cl, String prenom)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update enfant set prenom=? where idE=?");
            pt.setString(1,prenom);
            pt.setInt(2,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    */
    
    public void afficherEnfant()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from enfant");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("enfant [ id: " +rs.getInt(1) + " idparent" + rs.getInt(2) + " nom : " + rs.getString(3) + " prenom:  " + rs.getString(4)+ " age" + rs.getInt(5)+" idgroupe" + rs.getInt(6)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerEnfant (Enfant cl)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from enfant where idE=?");
            pt.setInt(1,cl.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Enfant> getTrier()  {
          List<Enfant> arr1=new ArrayList<>();
        try {
   
    List<Enfant> arr=new ArrayList<>();
    Statement st =c.createStatement();
    ResultSet rs=st.executeQuery("select * from enfant ORDER BY nom DESC");
     while (rs.next()) {                
              
              int id=rs.getInt(1);
              int idpa=rs.getInt(2);
              String nom=rs.getString(3);
              String prenom=rs.getString(4);
              int age=rs.getInt(5);
              int idGr=rs.getInt(6);
               Enfant p=new Enfant(id,idpa,nom,prenom,age,idGr);
               arr.add(p);
     }  
       
        return arr;

    }
    catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return arr1;
    }
    public Enfant rechercheByName(String nomEn) {
          Enfant a = null;
         String requete = " select* from enfant  where (nom like '"+nomEn+"%')" ;
        try {
           
             Statement st =c.createStatement();
           ResultSet rs=st.executeQuery(requete);
            if (rs.next())
            {a=new Enfant(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
            System.out.println(a.getNomE());}
            else System.out.println("n'existe pas");
            
        } catch (SQLException ex) {
            Logger.getLogger(serviceenfant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
    
}
