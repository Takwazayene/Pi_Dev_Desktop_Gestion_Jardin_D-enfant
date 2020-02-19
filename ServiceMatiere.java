/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Service;

import java.sql.SQLException;
import java.util.List;
import pidev.Entite.Matiere;
import pidev.IService.IService;
import pidev.DataBase.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceMatiere implements IService<Matiere> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceMatiere() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Matiere a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `Jardin`.`Matiere` (`Nom_Matiere`, `Coef_Matiere`, `Nbre_Heure_Matiere`, `Id_Enseignant`) VALUES (?, ?, ?, ?);");
        PS.setString(1, a.getNom());
        PS.setInt(2, a.getCoeff());
        PS.setInt(3, a.getNbh());
        PS.setInt(4, a.getId_enseignant());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `Jardin`.`Matiere` WHERE `Id_Matiere`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Matiere a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Matiere` SET `Nom_Matiere`=?,`Coef_Matiere`=? ,`Nbre_Heure_Matiere`=?,`Id_Enseignant`=? WHERE `Id_Matiere`=?");
        PS.setString(1,a.getNom());
        PS.setInt(2, a.getCoeff());
        PS.setInt(3,a.getNbh());
        PS.setInt(4,a.getId_enseignant());
        PS.setInt(5,id);
        PS.executeUpdate();
    }

        public void updatetab(Matiere a) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Matiere` SET `Nom_Matiere`=?,`Coef_Matiere`=? ,`Nbre_Heure_Matiere`=?,`Id_Enseignant`=? WHERE `Id_Matiere`=?");
        PS.setString(1,a.getNom());
        PS.setInt(2, a.getCoeff());
        PS.setInt(3,a.getNbh());
        PS.setInt(4,a.getId_enseignant());
        PS.setInt(5,a.getId());
        PS.executeUpdate();
    }

    @Override
    public List<Matiere> readAll() throws SQLException {
        List<Matiere> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Matiere");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("Nom_Matiere");
            int coeff = rs.getInt(3);
            int nbh = rs.getInt(4);
            int id_enseignant = rs.getInt(5);
            Matiere a = new Matiere(id, nom,coeff,nbh, id_enseignant);
            AL.add(a);
        }
        return AL;
    }
    public List<Matiere> getTrier() throws SQLException {
    List<Matiere> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Matiere ORDER BY Nom_Matiere DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("Nom_Matiere");
            int coeff = rs.getInt(3);
            int nbh = rs.getInt(4);
            int id_enseignant = rs.getInt(5);
            Matiere a = new Matiere(id, nom,coeff,nbh, id_enseignant);
     arr.add(a);
     }
    return arr;
    }
  public Matiere getById(int id) {
          Matiere a = null;
         String requete = " select* from Matiere where Id_Matiere='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Matiere(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getInt(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Matiere getByName(String n) {
          Matiere a = null;
         String requete = " select* from Matiere  where (Nom_Matiere like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Matiere(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getInt(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
}
