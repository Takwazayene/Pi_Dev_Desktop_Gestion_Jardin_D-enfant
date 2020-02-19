/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Service;

import java.sql.SQLException;
import java.util.List;
import pidev.Entite.Tab_Demande;
import pidev.IService.IService;
import pidev.DataBase.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceTab_Demande implements IService<Tab_Demande>{
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceTab_Demande() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Tab_Demande a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `Jardin`.`Tab_Demande` (`Nom_Demande`,`Prenom_Demande`, `Cin_Demande`, `Num_Tel_Demande`, `CV_Demande`, `Date_Naiss_Demande`, `Etude_Demande`, `Poste_Demande`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getCin());
        PS.setInt(4,a.getNumtel());
        PS.setString(5, a.getCv());
        PS.setDate(6, a.getDatenaiss());
        PS.setString(7, a.getEtude());
        PS.setString(8, a.getPoste());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `Jardin`.`Tab_Demande` WHERE `Id_Demande`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Tab_Demande a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Tab_Demande` SET `Nom_Demande`=?,`Prenom_Demande`=? ,`Cin_Demande`=?,`Num_Tel_Demande`=?,`CV_Demande`=?,`Date_Naiss_Demande`=?,`Etude_Demande`=?,`Poste_Demande`=?,`Date_Inter_Demande`=? WHERE `Id_Demande`=?");
        PS.setString(1,a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getCin());
        PS.setInt(4,a.getNumtel());
        PS.setString(5, a.getCv());
        PS.setDate(6, a.getDatenaiss());
        PS.setString(7, a.getEtude());
        PS.setString(8, a.getPoste());
        PS.setDate(9, a.getDateinter());
        PS.setInt(10,id);
        PS.executeUpdate();
    }
        public void updatetab(Tab_Demande a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Tab_Demande` SET `Nom_Demande`=?,`Prenom_Demande`=? ,`Cin_Demande`=?,`Num_Tel_Demande`=?,`CV_Demande`=?,`Date_Naiss_Demande`=?,`Etude_Demande`=?,`Poste_Demande`=?,`Date_Inter_Demande`=? WHERE `Id_Demande`=?");
        PS.setString(1,a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getCin());
        PS.setInt(4,a.getNumtel());
        PS.setString(5, a.getCv());
        PS.setDate(6, a.getDatenaiss());
        PS.setString(7, a.getEtude());
        PS.setString(8, a.getPoste());
        PS.setDate(9, a.getDateinter());
        PS.setInt(10,a.getId());
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ServiceTab_Demande.class.getName()).log(Level.SEVERE,null,e);
            }

    }

    @Override
    public List<Tab_Demande> readAll() throws SQLException {
        List<Tab_Demande> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Tab_Demande");
        while (rs.next()) {
            String nom = rs.getString("Nom_Demande");
            String prenom = rs.getString("Prenom_Demande");
            int id = rs.getInt(3);
            String cin = rs.getString("Cin_Demande");
            int numtel = rs.getInt(5);
            String cv = rs.getString("CV_Demande");
            Date datenaiss = rs.getDate("Date_Naiss_Demande");
            String etude = rs.getString("Etude_Demande");
            String poste = rs.getString("Poste_Demande");
            Date dateinter = rs.getDate("Date_Inter_Demande");
            Tab_Demande a = new Tab_Demande(nom,prenom,id,cin,numtel,cv,datenaiss,etude,poste,dateinter );
            AL.add(a);
        }
        return AL;
    }
    
    public List<Tab_Demande> getTrier() throws SQLException {
    List<Tab_Demande> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Tab_Demande ORDER BY Nom_Demande DESC");
        while (rs.next()) {
            String nom = rs.getString("Nom_Demande");
            String prenom = rs.getString("Prenom_Demande");
            int id = rs.getInt(3);
            String cin = rs.getString("Cin_Demande");
            int numtel = rs.getInt(5);
            String cv = rs.getString("CV_Demande");
            Date datenaiss = rs.getDate("Date_Naiss_Demande");
            String etude = rs.getString("Etude_Demande");
            String poste = rs.getString("Poste_Demande");
            Date dateinter = rs.getDate("Date_Inter_Demande");
            Tab_Demande a = new Tab_Demande(nom,prenom,id,cin,numtel,cv,datenaiss,etude,poste,dateinter );
     arr.add(a);
     }
    return arr;
    }

   public Tab_Demande getById(int id) {
          Tab_Demande a = null;
         String requete = " select* from Tab_Demande  where Id_Demande='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Tab_Demande(res.getString(1),res.getString(2),res.getInt(3),res.getString(4),res.getInt(5),res.getString(6),res.getDate(7),res.getString(8),res.getString(8),res.getDate(9));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTab_Demande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Tab_Demande getByName(String n) {
          Tab_Demande a = null;
         String requete = " select* from Tab_Demande  where (Nom_Demande like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Tab_Demande(res.getString(1),res.getString(2),res.getInt(3),res.getString(4),res.getInt(5),res.getString(6),res.getDate(7),res.getString(8),res.getString(8),res.getDate(9));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTab_Demande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
   
}

