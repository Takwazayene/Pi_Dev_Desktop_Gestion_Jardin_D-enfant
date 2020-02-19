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
import entities.AbonneResto;
import java.sql.Statement;
import utils.ConnexionBD;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import entities.Enfant;
import service.serviceenfant;


/**
 *
 * @author ASUS
 */
public class ServiceAbonneResto {
    
    Connection c = ConnexionBD.getInstance().getCnx();
    
    
    public void ajouterAbonneResto(AbonneResto a){
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
String da = dateFormat.format(date) ;
java.sql.Date sqlDate = java.sql.Date.valueOf( da );
   
   
            
       
    String req = "INSERT INTO `abonneResto` (idAb,nom,typeAbo,typePension,etat,absence,dateAbo)" + " VALUES (?,?,?,?,?,?,?)";
    
    
        PreparedStatement pre = c.prepareStatement(req);
        pre.setInt(1,a.getIdAb());
        pre.setString(2, a.getNom());
        pre.setString(3, a.getTypeAbo());
        pre.setString(4, a.getTypePension());
        pre.setInt(5, a.getEtat());
        pre.setInt(6, a.getAbsence());
         pre.setDate(7, sqlDate);
       
        pre.executeUpdate();
       String typePension =a.getTypePension();
      ServiceResto srv = new ServiceResto();
        srv.incrementerEffectif(typePension) ;
     }
      catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
     
       }
    }
     
      


       public void modifierAbonneResto(AbonneResto a,String typeAbo) {
           try{
        PreparedStatement pt=c.prepareStatement("update abonneresto set typeAbo= ? where nom = ?");
        pt.setString(1,typeAbo);
        pt.setString(2,a.getNom());
        pt.executeUpdate() ;
           }
             catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
           
        
       }
       
    
       
       
           public void updateAbonneResto(AbonneResto a ,int id) {
               
               
               
               
        String req = "UPDATE `abonneresto` SET  `nom`= ?, `typeAbo` = ?,`typePension`= ?, `absence`= ?  WHERE idAb="+id;
  /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();
Date d= a.getDateAbo();
String da = dateFormat.format(d) ;
java.sql.Date sqlDate = java.sql.Date.valueOf( da );*/
        try {
            PreparedStatement st = c.prepareStatement(req);
            st.setString(1, a.getNom());
            st.setString(2, a.getTypeAbo());
            st.setString(3, a.getTypePension());
            st.setInt(4, a.getAbsence());
          //  st.setDate(5, sqlDate);
          //  st.setInt(6, a.getIdAb());

 

            st.executeUpdate();

        } catch (SQLException ex) {
          Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }

    }
       
       
       
       
     public void afficherAbonnesResto(){
         try{
           PreparedStatement pt=c.prepareStatement("select * from abonneresto");
           ResultSet rs=pt.executeQuery();
         
             
             while(rs.next()) { //identifier de id=1
                   System.out.println("AbonneResto [ idAb : "+rs.getInt(1)+ " nom : " +rs.getString(2) + " typeAbonne : " + rs.getString(3) + " typePension : " + rs.getString(4) + " etat : " + rs.getInt(5) +  " absence : " + rs.getInt(6) + " dateAbo : " + rs.getString(7) +" ]");
             }
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
             
         
     }
     
      public List<AbonneResto> getAll() {

        String requete = "Select * from abonneresto";
        List<AbonneResto> listAbonne = new ArrayList<>();
        try {
           Statement stmt = c.createStatement();
          ResultSet  rs = stmt.executeQuery(requete);
            while (rs.next()) {
                AbonneResto a = new AbonneResto(
                        rs.getInt("idAb"),
                        rs.getString("nom"),
                        rs.getString("typeAbo"),
                        rs.getString("typePension"),
                        rs.getInt("etat"),
                         rs.getInt("absence"),
                        rs.getDate("dateAbo"));

                listAbonne.add(a);

            }

        } catch (SQLException ex) {
           Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
        return listAbonne;

    }
     
     public void supprimerAbonneResto(AbonneResto a){
         try{
         PreparedStatement pt= c.prepareStatement("delete from abonneresto where idAb =? ") ;
         pt.setInt(1,a.getIdAb());
         pt.executeUpdate();
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
         
         
     }
     
     public void TrieAbsence()  {
        
        
     
      try{
           PreparedStatement pt=c.prepareStatement("SELECT * FROM abonneResto r ORDER BY absence");
           ResultSet rs=pt.executeQuery();
         
             
             while(rs.next()) { //identifier de id=1
                System.out.println("AbonneResto [ idAb : "+rs.getInt(1)+ " nom : " +rs.getString(2) + " typeAbonne : " + rs.getString(3) + " typePension : " + rs.getString(4) + " etat : " + rs.getInt(5) +  " absence : " + rs.getInt(6) + " dateAbo : " + rs.getDate(7) +" ]");
             }
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
             
     }
     
     
     
        public void findAboById(int idAb)
    {  
       int count = 0 ; 
        
         try{
           PreparedStatement pt=c.prepareStatement("select * from abonneResto where idAb="+idAb);
           ResultSet rs=pt.executeQuery();
         
             
             while(rs.next()) { //identifier de id=1
                  System.out.println("AbonneResto [ idAb : "+rs.getInt(1)+ " nom : " +rs.getString(2) + " typeAbonne : " + rs.getString(3) + " typePension : " + rs.getString(4) + " etat : " + rs.getInt(5) +  " absence : " + rs.getInt(6) + " dateAbo : " + rs.getDate(7) +" ]");
               count++;
             }
             
         }
           catch (SQLException ex){
        Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
    }
             
        
     
   }
        
        
        
        
        
        
          public AbonneResto chercherAbonneByLogin(String login) throws SQLException {
           //   try{
     AbonneResto a=new AbonneResto();
   String req = "select * from abonneresto where nom ='"+login+"';";
    //PreparedStatement pt=c.prepareStatement("select * from abonneresto where nom="+login);
         //  ResultSet res=pt.executeQuery();
        Statement ste = c.createStatement();
      ResultSet res= ste.executeQuery(req);
       while (res.next()) {
           if(res.getString("nom").equals(login)){
             
                
                
                       a.setIdAb(res.getInt("idAb"));
                       a.setNom(res.getString("nom"));
                       a.setTypeAbo(res.getString("typeAbo"));
                       a.setTypePension(res.getString("typePension"));
                       a.setEtat(res.getInt("etat"));
                        a.setAbsence(res.getInt("absence"));
                        a.setDateAbo(res.getDate("dateAbo"));
                
           }
       
       }
           //   }
              // catch (SQLException ex){
       // Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
   // }
        
        return a;   
          }
        
        
        
        
        
        
        

        
        
public List<Enfant>  DetailsAbonne( int id)
{
    
       String requete = "Select * from enfant where id="+id;
             List<Enfant> listEnfant = new ArrayList<>();
        try {
           Statement stmt = c.createStatement();
          ResultSet  rs = stmt.executeQuery(requete);
            while (rs.next()) {
                Enfant e = new Enfant(
                        rs.getInt("id"),
                        rs.getInt("IdParent"),
                        rs.getString("nomE"),
                        rs.getString("prenomE"),
                        rs.getInt("age"),
                         rs.getInt("IdGroupe"));
              

                listEnfant.add(e);

            }

        } catch (SQLException ex) {
           Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE,null,ex);
        }
        return listEnfant;
     

    
    
    
    
    
}
}
    

