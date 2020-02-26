
package services;

import entities.Event;
import entities.clubs;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionBD;


public class serviceevent {
    
    Connection c= ConnexionBD.getInstance().getCnx();
    public void ajouterevent (Event e1)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into event(`idEvent`,`nomEvent`,`categorieEvent`,`nbrPlaceDispo`,`dateEvent`,`description`) values("+e1.getIdEvent()+",'"+e1.getNomEvent()+"','"+e1.getCategorieEvent()+"',"+e1.getNbrPlaceDispo()+",'"+e1.getDateEvent()+"','"+e1.getDescription()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierNom(Event el, String nomEvent)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update event set nomEvent=? where idEvent=?");
            pt.setString(1,nomEvent);
            pt.setInt(2,el.getIdEvent());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public void modifierNbr(Event el)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update Event set nbrPlaceDispo=? where idEvent=?");
            pt.setInt(1,el.getNbrPlaceDispo()-1);
            pt.setInt(2,el.getIdEvent());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
   /*  public void afficherevent()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from event");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("event [ id: " +rs.getInt(1) + " nom: " + rs.getString(2) + " categorie : " + rs.getString(3) + " nbrplace:  " + rs.getInt(4)+ "date:" +rs.getString(5)+"affiche:"+rs.getString(6)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    public List<Event> readAll() throws SQLException {
            List<Event> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString(2);
               String categorie=rs.getString(3);
               int nbrplace=rs.getInt(4);
               Date date=rs.getDate(5);
               String description=rs.getString(6);
               //int idClub=rs.getInt(7);
               Event e=new Event(id,nom, categorie, nbrplace,date,description);
     arr.add(e);
     }
    return arr;
    }
    ////////////////////
    public List<Event> AfficheAll() throws SQLException {
            List<Event> arr = new ArrayList<>();
    Statement ste=c.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     while (rs.next()) {                
               String nom=rs.getString(1);
               
               String categorie=rs.getString(2);
               int nbrplace=rs.getInt(5);
               Date date=rs.getDate(3);
               String description=rs.getString(4);
               String nomClub=rs.getString(6);
               Event e=new Event(nom,categorie,date, description, nbrplace,nomClub);
     arr.add(e);
     }
    return arr;
    }
    ///////////////////
   
    
      
    
    
    
    
    
 
    
    public void supprimerevent (Event el)
    {
        try {
            String querry="DELETE FROM `event` WHERE idEvent="+el.getIdEvent();
            
            Statement stm=c.createStatement();
            stm.executeUpdate(querry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  }
    }

    public void modifierNbr1(Event p, int parseInt) {
        try {
            PreparedStatement pt = c.prepareStatement("update Event set nbrPlaceDispo=? where idEvent=?");
            pt.setInt(1,parseInt);
            pt.setInt(2,p.getIdEvent());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceevent.class.getName()).log(Level.SEVERE, null, ex);
        }}
    
 
    
    
    
    
    
    
    
    
    
          public void updatetab(Event a) throws SQLException {
        
            PreparedStatement PS=c.prepareStatement("update `event` SET `nomEvent`=? ,`categorieEvent`=? ,`nbrPlaceDispo`=? ,`dateEvent`=? ,`description`=? WHERE `idEvent`=?");
            PS.setString(1,a.getNomEvent());
             PS.setString(2, a.getCategorieEvent());
            PS.setInt(3,a.getNbrPlaceDispo());
           PS.setDate(4,a.getDateEvent());
            PS.setString(5,a.getDescription());
            PS.setInt(6,a.getIdEvent());
            PS.executeUpdate();
       
    }

}

