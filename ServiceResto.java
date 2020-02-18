/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Statement;
import IServices.IService;
import entities.Resto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import utils.DataBase;
/**
 *
 * @author ASUS
 */

public class ServiceResto implements IService<Resto> {
    private Connection con;
    private Statement ste;
    
        public ServiceResto() {
        con = DataBase.getInstance().getConnection();
        

    }


    @Override
    public void ajouter(Resto t) throws SQLException 
     {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `3a3`.`resto` ( `idR`, `nom`, `effectif`, `responsableResto`, `etat`) VALUES ( ?, ?, ?, ?, ?);");
    pre.setInt(1, t.getIdR());
    pre.setString(2, t.getNom());
    pre.setInt(3, t.getEffectif());
    pre.setInt(4, t.getResponsableResto());
    pre.setBoolean(5, t.getEtat());
    pre.executeUpdate();
  
    }

    @Override
    public boolean update(Resto t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Resto t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Resto> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    
    
}
