/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Service;

import java.sql.SQLException;
import java.util.List;
import pidev.Entite.Groupe;
import pidev.IService.IService;
import pidev.DataBase.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceGroupe implements IService<Groupe> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceGroupe() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Groupe a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `Jardin`.`Groupe` (`Nom_Groupe`, `Nbr_Enfant_Groupe`, `Id_Enseignant_Groupe`, `Age_Groupe`) VALUES (?, ?, ?, ?);");
        PS.setString(1, a.getNom());
        PS.setInt(2, a.getNbr_enfant());
        PS.setInt(3, a.getId_enseignant());
        PS.setInt(4, a.getAge());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `Jardin`.`Groupe` WHERE `Id_Groupe`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Groupe a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Groupe` SET `Nom_Groupe`=?,`Nbr_Enfant_Groupe`=? ,`Id_Enseignant_Groupe`=?,`Age_Groupe`=? WHERE `Id_Groupe`=?");
        PS.setString(1,a.getNom());
        PS.setInt(2, a.getNbr_enfant());
        PS.setInt(3,a.getId_enseignant());
        PS.setInt(4,a.getAge());
        PS.setInt(5,id);
        PS.executeUpdate();
    }

        public void updatetab(Groupe a) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `Jardin`.`Groupe` SET `Nom_Groupe`=?,`Nbr_Enfant_Groupe`=? ,`Id_Enseignant_Groupe`=?,`Age_Groupe`=? WHERE `Id_Groupe`=?");
        PS.setString(1,a.getNom());
        PS.setInt(2, a.getNbr_enfant());
        PS.setInt(3,a.getId_enseignant());
        PS.setInt(4,a.getAge());
        PS.setInt(5,a.getId());
        PS.executeUpdate();
    }

    @Override
    public List<Groupe> readAll() throws SQLException {
        List<Groupe> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Groupe");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("Nom_Groupe");
            int nbr_enfant = rs.getInt(3);
            int id_enseignant = rs.getInt(4);
            int age = rs.getInt(5);
            Groupe a = new Groupe(id, nom,nbr_enfant , id_enseignant,age );
            AL.add(a);
        }
        return AL;
    }
    public List<Groupe> getTrier() throws SQLException {
    List<Groupe> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Groupe ORDER BY Nom_Groupe DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("Nom_Groupe");
            int nbr_enfant = rs.getInt(3);
            int id_enseignant = rs.getInt(4);
            int age = rs.getInt(5);
            Groupe a = new Groupe(id, nom,nbr_enfant , id_enseignant,age );
     arr.add(a);
     }
    return arr;
    }
  public Groupe getById(int id) {
          Groupe a = null;
         String requete = " select* from Groupe where Id_Groupe='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Groupe(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getInt(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Groupe getByName(String n) {
          Groupe a = null;
         String requete = " select* from Groupe  where (Nom_Groupe like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Groupe(res.getInt(1),res.getString(2),res.getInt(3),res.getInt(4),res.getInt(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
}
