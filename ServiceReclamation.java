/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Service;

import java.sql.SQLException;
import java.util.List;
import pidev.Entite.Reclamation;
import pidev.IService.IService;
import pidev.DataBase.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceReclamation implements IService<Reclamation>{
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Reclamation a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `Jardin`.`Tab_Demande` (`Nom_Demande`,`Prenom_Demande`, `Cin_Demande`, `Num_Tel_Demande`, `Poste_Demande`) VALUES (?, ?, ?, ?, ?);");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getCin());
        PS.setInt(4,a.getNumtel());
        PS.setString(5, a.getPoste());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `Jardin`.`Tab_Demande` WHERE `Id_Demande`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Reclamation a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Tab_Demande` SET `Nom_Demande`=?,`Prenom_Demande`=? ,`Cin_Demande`=?,`Num_Tel_Demande`=?,`Poste_Demande`=? WHERE `Id_Demande`=?");
        PS.setString(1,a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getCin());
        PS.setInt(4,a.getNumtel());
        
        PS.setString(5, a.getPoste());
       // PS.setDate(9, a.getDateinter());
        PS.setInt(6,id);
        PS.executeUpdate();
    }
        public void updatetab(Reclamation a) throws SQLException {
            try {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Tab_Demande` SET `Nom_Demande`=?,`Prenom_Demande`=? ,`Cin_Demande`=?,`Num_Tel_Demande`=?,`Poste_Demande`=? WHERE `Id_Demande`=?");
        PS.setString(1,a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getCin());
        PS.setInt(4,a.getNumtel());
       
        PS.setString(5, a.getPoste());
       // PS.setDate(9, a.getDateinter());
        PS.setInt(6,a.getId());
        PS.executeUpdate();
            } catch (Exception e) {
                Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE,null,e);
            }

    }

    @Override
    public List<Reclamation> readAll() throws SQLException {
        List<Reclamation> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Tab_Demande");
        while (rs.next()) {
            String nom = rs.getString("Nom_Demande");
            String prenom = rs.getString("Prenom_Demande");
            int id = rs.getInt(3);
            String cin = rs.getString("Cin_Demande");
            int numtel = rs.getInt(5);
           // String cv = rs.getString("CV_Demande");
            //Date datenaiss = rs.getDate("Date_Naiss_Demande");
            //String etude = rs.getString("Etude_Demande");
            String poste = rs.getString("Poste_Demande");
            //Date dateinter = rs.getDate("Date_Inter_Demande");
            Reclamation a = new Reclamation(nom,prenom,id,cin,numtel,poste );
            AL.add(a);
        }
        return AL;
    }
    
    public List<Reclamation> getTrier() throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Tab_Demande ORDER BY Nom_Demande DESC");
        while (rs.next()) {
            String nom = rs.getString("Nom_Demande");
            String prenom = rs.getString("Prenom_Demande");
            int id = rs.getInt(3);
            String cin = rs.getString("Cin_Demande");
            int numtel = rs.getInt(4);
           // String cv = rs.getString("CV_Demande");
           // Date datenaiss = rs.getDate("Date_Naiss_Demande");
           // String etude = rs.getString("Etude_Demande");
            String poste = rs.getString("Poste_Demande");
            //Date dateinter = rs.getDate("Date_Inter_Demande");
            Reclamation a = new Reclamation(nom,prenom,id,cin,numtel,poste);
     arr.add(a);
     }
    return arr;
    }

 /*  public Reclamation getById(int id) {
          Reclamation a = null;
         String requete = " select* from Reclamation  where Id_Demande='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Reclamation(res.getString(1),res.getString(2),res.getInt(3),res.getString(4),res.getInt(5),res.getString(6),res.getDate(7),res.getString(8),res.getString(8),res.getDate(9));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Reclamation getByName(String n) {
          Reclamation a = null;
         String requete = " select* from Reclamation  where (Nom_Demande like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Reclamation(res.getString(1),res.getString(2),res.getInt(3),res.getString(4),res.getInt(5),res.getString(6),res.getDate(7),res.getString(8),res.getString(8),res.getDate(9));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }*/
   
}

