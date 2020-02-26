/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_pi.userservices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import project_pi.userentites.Utilisateur;
import project_pi.userinterface.IServiceUtilisateur;
import project_pi.utile.Connection;

/**
 *
 * @author USER
 */
public class ServiceUser implements IServiceUtilisateur {


   
    private Connection cnx;
     @Override
    public List<Utilisateur> getUtilisateurs()  throws SQLException {
  
        List<Utilisateur> listu = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM user";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Utilisateur a = new Utilisateur();
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));         
                listu.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listu;
    }

  
   
   public Utilisateur getById(int id) throws SQLException{
        Utilisateur a=new Utilisateur();
        try {
            String req="SELECT * FROM user where id='"+id+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
 
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));                       
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
      public Utilisateur getByMail(String Email) throws SQLException{
        Utilisateur a=new Utilisateur();
        try {
            String req="SELECT * FROM user where email='"+Email+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {
 
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));                       
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
   

    @Override
   public void deleteUtilisateur(Utilisateur p) throws SQLException{
        try {
            String req="DELETE FROM user WHERE id=?";
            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
            ps.setInt(1,p.getId_user());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUtilisateur_ById(int id) throws SQLException {
             try {
            String req="DELETE FROM user WHERE id=?";
            PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    

    @Override
      public void updateUtilisateur(Utilisateur p) throws SQLException{
 
         try {

            String req="UPDATE user SET  nom = ?, prenom = ?, email = ?, age = ?, telephone = ?, password = ?, role = ?  WHERE user.`id` = ?";
           PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);     
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getEmail());
            ps.setInt(4,p.getAge());
            ps.setInt(5,p.getTelephone());
            ps.setString(6,p.getPassword());
            ps.setString(7,p.getRole());
            ps.setInt(8,p.getId_user());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
      
      
     
      
     
    

    @Override
    public void addUtilisateur(Utilisateur p) throws SQLException {       
        try {
            
       
            String req="INSERT INTO user (nom, prenom, email, age, telephone, role, password) VALUES (?,?,?,?,?,?,?)";
             PreparedStatement ps=Connection.getInstance().getConnection().prepareStatement(req);
   
            ps.setString(1,p.getNom());
            ps.setString(2,p.getPrenom());
            ps.setString(3,p.getEmail());
            ps.setInt(4,p.getAge());
            ps.setInt(5,p.getTelephone());
            ps.setString(6,p.getRole());
            ps.setString(7,p.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
     public List<Utilisateur> RechercherUtilisateur(String nom) throws SQLException{

     List<Utilisateur> listrecherche = new ArrayList<>();
       // UserServices uu=new UserServices();
       
        try {
            String req="SELECT * FROM user WHERE nom='"+nom+"'";
            Statement s=Connection.getInstance().getConnection().createStatement();
            ResultSet rs=s.executeQuery(req);
            while(rs.next())
            {    
            Utilisateur a = new Utilisateur();
            a.setId_user(rs.getInt("id"));
            a.setNom(rs.getString("nom"));
            a.setPrenom(rs.getString("prenom"));
            a.setTelephone(rs.getInt("telephone"));
            a.setEmail(rs.getString("email"));
            a.setRole(rs.getString("role"));
            a.setAge(rs.getInt("age"));
            a.setPassword(rs.getString("password"));         
                listrecherche.add(a);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listrecherche;
    }
     
      public int calculerTotal() {
          
         String requete = "SELECT COUNT(*) FROM user" ;
        int a = 0;
         try {
           
         //  Statement st =cnx.createStatement();
           Statement s=Connection.getInstance().getConnection().createStatement();
           ResultSet rs=s.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          a=Integer.parseInt(chaine);
            System.out.println(a);
           return a;
           }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return a;
    } 
     
     }


         
 
