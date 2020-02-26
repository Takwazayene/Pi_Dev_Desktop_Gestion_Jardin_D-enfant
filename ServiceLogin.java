/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Utils.ConnexionBD;

/**
 *
 * @author ASUS
 */
public class ServiceLogin {
    
   

   Connection c = ConnexionBD.getInstance().getCnx();
    
    public String verifierUser(String login,String mdp) throws SQLException
    {   String req = "select * from user ";
       
       Statement ste = c.createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("username").equals(login)&&res.getString("password").equals(mdp))
         {
            return res.getString("role");
         }   
        }
        
        
        
        
        return "false";
    }
    
    
     public int finId (String login,String mdp) throws SQLException
    {   String req = "select * from user ";
       
       Statement ste = c.createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("username").equals(login)&&res.getString("password").equals(mdp))
         {
            return res.getInt("id");
         }   
        }
        
        
        
        
        return 0 ;
    }
   
    public int idUserFind(String login) throws SQLException
    {   String req = "select * from user ";
       
       Statement ste = c.createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("username").equals(login))
         {
            return res.getInt("id");
         }   
        }
        
        
        
        return -1;
    }   

    public int idUser(String login) throws SQLException
    {   String req = "select * from user";
       
       Statement ste = c.createStatement();
       ResultSet res= ste.executeQuery(req);
        while (res.next()) {
         if(res.getString("username").equals(login))
        
            return res.getInt("id");
       
         
         
           
        }
        return -1;
}
}
       
   
    
    
          
    



